package controlersClasses.src;

import java.util.ArrayList;

public class Pietro extends Urzadzenie implements Runnable {
    private boolean[] oczekiwanie;
    private int towar;
    private int numer;
    private ArrayList<Winda> windy = new ArrayList<>();

    public void addWinda(Winda w) {
        windy.add(w);
    }

    public boolean getOczekiwanie(boolean kierunek) {
        if (kierunek) {
            return this.oczekiwanie[1];
        }
        else  {
            return this.oczekiwanie[0];
        }
    }

    public void setOczekiwanie(boolean oczekiwanie, boolean kierunek) {
        if (kierunek) {
            this.oczekiwanie[1] = oczekiwanie;
        }
        else {
            this.oczekiwanie[0] = oczekiwanie;
        }
    }

    public void ZmianaTowaru(int zmiana) {
        this.towar = zmiana;
    }

    public int PrzeniesienieTowaru() {
        int przeniesiony = towar;
        towar = 0;
        return przeniesiony;
    }

    public void PrzywolajWinde(boolean kierunek) {
        if (kierunek && !this.oczekiwanie[1]) {
            setOczekiwanie(true , true);
            for (Winda w : windy) {
                w.action(1,this.numer);
            }
        }
        else if (!kierunek && !this.oczekiwanie[0]) {
            setOczekiwanie(true , false);
            for (Winda w : windy) {
                w.action(-1,this.numer);
            }
        }


    }
    public void inform()
    {

    }

    public Pietro(String nazwa, int numer) {
        super(nazwa);
        this.oczekiwanie = new boolean[]{false, false};
        this.numer = numer;
    }
    @Override
    public void run() {

    }


}
