package controlersClasses.src;

import java.util.ArrayList;
import java.util.List;

public class Winda extends Urzadzenie implements Listener, Runnable {
    static private ArrayList<Double> przyjeteZadaniaOd = new ArrayList<>();
    static private ArrayList<Integer> przyjeteZadaniaWindy = new ArrayList<>();
    static private int freeId = 0;

    private double predkosc;
    private double wyskosc;
    private Silnik silnik;
    private byte kierunek;
    private int id;
    private List<Boolean> zadania = new ArrayList<>();
    private List<Boolean> cele = new ArrayList();
    private List<Listener> kontrolery = new ArrayList<>();
    private volatile Thread t;

    public double getWyskosc() {
        return wyskosc;
    }

    public int getId() {
        return id;
    }

    public void interuption() {
        t.interrupt();
    }

    public void setThread(Thread t) {
        this.t = t;
    }

    public void jedz(boolean kierunek,double destynacja, double odstep) throws Exception {
        if (this.getObciazenie() > this.getObciazenie_max()) {
            throw new Exception();
        }
        try {
            predkosc = silnik.poruszaj(kierunek, this.getObciazenie());
        } catch (Exception e) {
            throw new Exception();
        }
        if (predkosc > 0 && wyskosc + predkosc * odstep < destynacja) {
            wyskosc += predkosc * odstep;
            this.kierunek = 1;
        }
        else if (predkosc < 0 && wyskosc + predkosc * odstep > destynacja) {
            wyskosc += predkosc * odstep;
            this.kierunek = -1;
        }
        else {
            wyskosc = destynacja;
            this.silnik.zatrzymaj();
            this.PodfierdzWykonanie();
            this.setNewTarget((int)(this.wyskosc/5),false);

        }

    }
    public synchronized void PodfierdzWykonanie() {
        przyjeteZadaniaWindy.set((int)(wyskosc/5),-1);
        przyjeteZadaniaOd.set((int)(wyskosc/5),0d);
    }


    public void zaladuj(int ladunek)
    {
        this.setObciazenie(ladunek);
    }
    public void uruchom()
    {
        this.silnik.uruchom();
    }

    public void action(Object... args) {
        int pietro = ((Number) args[1]).intValue();
        byte kierunek = ((Number) args[0]).byteValue();
        this.elekcja(pietro,kierunek);
    }

    public synchronized void elekcja(int pietro, byte kierunek) {
        double odleglosc;
        if (cele.get(pietro)) {
            if (this.kierunek * (pietro - wyskosc / 5) >= 0) {
                odleglosc = 0;
            }
            else {
                odleglosc = (double) this.cele.size() /1.5;
            }
        }
        else if (this.kierunek*(pietro-wyskosc/5)>=0 && (kierunek == this.kierunek || this.kierunek == 0)) {
            odleglosc = kierunek*(pietro-wyskosc/5);
        }
        else {
            odleglosc = 99;
        }
        if (odleglosc < przyjeteZadaniaOd.get(pietro) || przyjeteZadaniaWindy.get(pietro)==-1) {
            przyjeteZadaniaOd.set(pietro,odleglosc);
            przyjeteZadaniaWindy.set(pietro,this.id);
        }
    }
    public synchronized void reElekcja(int pietro, byte kierunek)
    {
        for (int i = 0; i < przyjeteZadaniaWindy.size(); i++) {
            elekcja(i,kierunek); //problem braku zajomości kierunku przywołania, do rozwiązania w przyszłości
        }
    }

    public synchronized void refreshOwnTasks() {
        for (int i = 0; i < przyjeteZadaniaWindy.size(); i++) {
            if (przyjeteZadaniaWindy.get(i) == this.id || cele.get(i)) {
                this.zadania.set(i, true);
            }
            else  {
                this.zadania.set(i, false);
            }
        }
    }

    public void addListener(Listener l) {
        kontrolery.add(l);
    }
    public void removeListener(Listener l) {
        kontrolery.remove(l);
    }

    public void setNewTarget(int pietro, boolean typ)
    {
        this.cele.set(pietro,typ);
    }


    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
            this.refreshOwnTasks();
            boolean isQuestSelected = false;
            for (int i = (int)(wyskosc/5) ;i < this.zadania.size() && i>=0; i += kierunek) {
                if (zadania.get(i)) {
                    try {
                        isQuestSelected = true;
                        jedz((kierunek > 0), i * 5, 0.1);
                    }
                    catch (Exception e) {
                        predkosc = 0;
                    }
                }
            }
            if (!isQuestSelected) {
                for (int i = 0; i < this.zadania.size(); i++) {
                    if (przyjeteZadaniaWindy.get(i) != -1) {
                        reElekcja(i, (byte) -this.kierunek);
                    }
                }
                for (int i = (int)(wyskosc/5) ;i < this.zadania.size() && i>=0; i -= kierunek) {
                    if (zadania.get(i)) {
                        try {
                            isQuestSelected = true;
                            kierunek = (byte) (-kierunek);
                            jedz((kierunek > 0), i * 5, 0.1);
                        }
                        catch (Exception e) {
                            predkosc = 0;
                        }
                    }
                }
            }

           // if (kierunek%5==0)
           // {
              //  try {
              //      Thread.sleep(10000);
              //  } catch (InterruptedException e) {}

           // }
            for (Listener k : kontrolery) {
                k.action();
            }

        }
    }


    public Winda(int waga_pod, int obciazenie_max, String nazwa, Silnik silnik) {
        super(waga_pod, obciazenie_max, nazwa);
        this.silnik = silnik;
        if(freeId == 0)
        {
            for (int i = 0; i < 4; i++) {
                Winda.przyjeteZadaniaOd.add(0d);
                Winda.przyjeteZadaniaWindy.add(-1);
            }
        }
        this.id = ++freeId;
        this.wyskosc = 0;
        this.kierunek = 1;
        for (int i = 0; i < 4; i++) {
            this.zadania.add(false);
        }
        for (int i = 0; i < 4; i++) {
            this.cele.add(false);
        }
    }

}