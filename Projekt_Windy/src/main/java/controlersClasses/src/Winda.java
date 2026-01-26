package controlersClasses.src;

import com.example.projekt_windy.MenuWindyKontroler;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Winda extends Urzadzenie implements Runnable {
    static private ArrayList<Double> przyjeteZadaniaOd = new ArrayList<>();
    static private ArrayList<Integer> przyjeteZadaniaWindy = new ArrayList<>();
    static private int freeId = 0;

    private double predkosc;
    private double wyskosc;
    private Silnik silnik;
    private byte kierunek;
    private int id;
    private int potencjalneZadanie;
    private List<Boolean> zadania = new ArrayList<>();
    private List<Boolean> cele = new ArrayList();
    private List<Listener> kontrolery = new ArrayList<>();
    private MenuWindyKontroler windyKontroler;
    private Budynek budynek;
    private boolean otwarteDrzwi;

    public double getWyskosc() {
        return wyskosc;
    }

    public int getId() {
        return id;
    }

    public double getPredkosc() {
        return predkosc;
    }

    public boolean isOtwarteDrzwi() {
        return otwarteDrzwi;
    }

   public void setMenuWindyKontroler(MenuWindyKontroler windyKontroler) {
        this.windyKontroler = windyKontroler;
    }

    public void setOtwarteDrzwi(boolean otwarteDrzwi) {
        this.otwarteDrzwi = otwarteDrzwi;
    }

    public void setBudynek(Budynek budynek) {
        this.budynek = budynek;
    }

    public void jedz(boolean kierunek,double destynacja, double odstep) throws MassException, ReadynessException {
        if (this.getObciazenie() > this.getObciazenie_max()) {
            throw new MassException();
        }
        predkosc = silnik.poruszaj(kierunek, this.getObciazenie());
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
            zatrzymaj();
            this.predkosc = 0;
            this.PodfierdzWykonanie();
            this.setNewTarget((int)(this.wyskosc/5),false);

        }

    }
    public synchronized void PodfierdzWykonanie() {
        if (przyjeteZadaniaOd.get((int)(wyskosc/5)) != 99 || wyskosc/5==0 || (int)(wyskosc/5) == przyjeteZadaniaOd.size()-1) {
            budynek.przekazPotwierdzenieDojazdu(this.id, wyskosc);
            przyjeteZadaniaWindy.set((int) (wyskosc / 5), -1);
            przyjeteZadaniaOd.set((int) (wyskosc / 5), 0d);
        }
    }

    public void uruchom()
    {
        this.silnik.uruchom();
        if (windyKontroler != null) {
            Platform.runLater(()->{windyKontroler.zmienObraz(new Image(windyKontroler.getClass().getResource("ZamknieteDrzwi.png").toExternalForm()));});
           otwarteDrzwi = false;
        }
    }
    public void zatrzymaj()
    {
        this.silnik.zatrzymaj();
        if (windyKontroler != null) {
            Platform.runLater(()->{windyKontroler.zmienObraz(new Image(windyKontroler.getClass().getResource("OtwarteDrzwi.png").toExternalForm()));});
            otwarteDrzwi = true;
        }
    }

    public static synchronized void anulujZadanie(int pietro)
    {
        przyjeteZadaniaWindy.set(pietro,-1);
        przyjeteZadaniaOd.set(pietro,0d);
    }

    public synchronized void elekcja(int pietro, byte kierunek) {
        double odleglosc;
        if (cele.get(pietro)) {
            if (this.kierunek * (pietro - wyskosc / 5) >= 0) {
                odleglosc = 0.1D;
            }
            else {
                odleglosc = 99;
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
    public synchronized void reElekcja(byte kierunek)
    {
        for (int i = 0; i < przyjeteZadaniaWindy.size(); i++) {
            if (przyjeteZadaniaWindy.get(i) != -1) {
                elekcja(i, kierunek);
            }
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
    {   if (!this.cele.get(pietro)) {
        this.cele.set(pietro, typ);
        }
        else  {
            this.cele.set(pietro, false);
        }
    }


    public void run() {
        while (true) {
            try {
                Thread.sleep(500L*10L/budynek.getSzybkoscSymulacji());
            } catch (InterruptedException e) {}
            this.refreshOwnTasks();
            boolean isQuestSelected = false;
            potencjalneZadanie = -1;
            for (int i = (int)(wyskosc/5)+(kierunek+1)/2 ;i < this.zadania.size() && i>=0; i += kierunek) {
                if (zadania.get(i)) {
                    try {
                        isQuestSelected = true;
                        if (przyjeteZadaniaOd.get(i) == 99 && cele.get(i) == false) {
                            potencjalneZadanie = i;
                        }
                        else {
                            jedz((kierunek > 0), i * 5, 0.1);
                            potencjalneZadanie = -1;
                            break;
                        }

                    }
                    catch (MassException | ReadynessException e) {
                        predkosc = 0;
                    }
                }
            }
            if (potencjalneZadanie !=-1) {
                try {
                    jedz((kierunek > 0), potencjalneZadanie * 5, 0.1);
                }
                catch (MassException | ReadynessException e) {
                    predkosc = 0;
                }
            }
            if (!isQuestSelected) {
                        kierunek = (byte) (-kierunek);
                        reElekcja((this.kierunek));
                for (int i = (int)(wyskosc/5) ;i < this.zadania.size() && i>=0; i -= kierunek) {
                    if (zadania.get(i)) {
                        try {
                            isQuestSelected = true;
                            jedz((kierunek > 0), i * 5, 0.1);
                            break;
                        }
                        catch (MassException | ReadynessException e) {
                            predkosc = 0;
                            System.out.println("za ciezko");
                        }
                    }
                }
            }
            budynek.informKontrolers();

        }
    }


    public Winda(int waga_pod, int obciazenie_max, String nazwa, Silnik silnik) {
        super(waga_pod, obciazenie_max, nazwa);
        this.silnik = silnik;
        this.budynek = budynek;
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