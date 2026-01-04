import java.util.ArrayList;
import java.util.List;

public class Winda extends Urzadzenie implements Runnable, Listener {
    static private ArrayList<Double> przyjeteZadaniaOd = new ArrayList<>();
    static private ArrayList<Integer> przyjeteZadaniaWindy = new ArrayList<>();
    static private int freeId = 0;

    private double predkosc;
    private double wyskosc;
    private Silnik silnik;
    private byte kierunek;
    private int id;
    private List<Informer> pietra = new ArrayList<>();
    private List<Boolean> zadania = new ArrayList<>();

    public void jedz(boolean kierunek,double destynacja, double odstep) throws Exception {
        if (this.getObciazenie() > this.getObciazenie_max()) {
            throw new Exception();
        }
        try {
            predkosc = silnik.poruszaj(kierunek, this.getObciazenie());
        } catch (Exception e) {
            throw new Exception();
        }
        if (predkosc > 0 && wyskosc + predkosc * odstep > destynacja) {
            wyskosc += predkosc * odstep;
            this.kierunek = 1;
        }
        else if (predkosc < 0 && wyskosc + predkosc * odstep < destynacja) {
            wyskosc += predkosc * odstep;
            this.kierunek = -1;
        }
        else {
            wyskosc = destynacja;
            this.zatrzymaj();
            silnik.zatrzymaj();
        }

    }
    public void zatrzymaj()
    {
        predkosc = 0;
    }
    public void zaladuj(int ladunek)
    {
        this.setObciazenie(ladunek);
    }

    public void action(byte way, int pietro) {
        this.elekcja(pietro,way);
    }

    public synchronized void elekcja(int pietro, byte kierunek) {
        double odleglosc;
        if (this.kierunek*(wyskosc/5-pietro)>0 && kierunek == this.kierunek ) {
            odleglosc = kierunek*(wyskosc/5-pietro);
            if (odleglosc < przyjeteZadaniaOd.get(pietro)) {
                przyjeteZadaniaOd.set(pietro,odleglosc);
                przyjeteZadaniaWindy.set(pietro,this.id);
            }
        }
    }

    public void refreshOwnTasks() {
        for (int i = 0; i < przyjeteZadaniaWindy.size(); i++) {
            if (przyjeteZadaniaWindy.get(i) == this.id) {
                this.zadania.set(i, true);
            }
            else  {
                this.zadania.set(i, false);
            }
        }
    }


    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
            this.refreshOwnTasks();
            boolean isQuestSelected = false;
            for (int i = (int)(wyskosc/5) ;i < this.zadania.size() && i>0; i += kierunek) {
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
                for (int i = (int)(wyskosc/5) ;i < this.zadania.size() && i>0; i -= kierunek) {
                    if (zadania.get(i)) {
                        try {
                            isQuestSelected = true;
                            jedz((-kierunek > 0), i * 5, 0.1);
                        }
                        catch (Exception e) {
                            predkosc = 0;
                        }
                    }
                }
            }
            if (kierunek%5==0)
            {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {}
            }

        }
    }


    public Winda(int waga_pod, int obciazenie_max, String nazwa, Silnik silnik) {
        super(waga_pod, obciazenie_max, nazwa);
        this.silnik = silnik;

        this.id = ++freeId;
    }

}