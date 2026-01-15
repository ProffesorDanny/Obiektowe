package controlersClasses.src;

import java.util.ArrayList;

public class Pietro extends Urzadzenie implements Runnable {
    private boolean[] oczekiwanie;
    private int towar;
    private int towardoprzeniesienia;
    private int numer;
    private int idZaparkowanejWindy;
    private boolean edytowanie;
    private boolean kierunekzaladunku;
    private ArrayList<Winda> windy = new ArrayList<>();
    private ArrayList<Listener> kontrolery = new ArrayList<>();

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

    public int getTowardoprzeniesienia() {
        return towardoprzeniesienia;
    }

    public int getTowar() {
        return towar;
    }
    public boolean getEdytowanie() {
        return edytowanie;
    }
    public void setEdytowanie(boolean edytowanie) {
        this.edytowanie = edytowanie;
    }

    public void setOczekiwanie(boolean oczekiwanie, boolean kierunek) {
        if (kierunek) {
            this.oczekiwanie[1] = oczekiwanie;
        }
        else {
            this.oczekiwanie[0] = oczekiwanie;
        }
    }

    public void setKierunekZaladunku(boolean kierunek) {
        this.kierunekzaladunku = kierunek;
    }

    public void setTowardoprzeniesienia(int towardoprzeniesienia) {
        this.towardoprzeniesienia = towardoprzeniesienia;
    }

    public void addListener(Listener l) {
        kontrolery.add(l);
    }


    public void ZaladunekTowaru(boolean kierunek,int id, int towar) throws NumberFormatException {
        if (towar<0)
        {
            throw new NumberFormatException();
        }
        if (kierunek) {
            if (this.towar - towar <= 0) {
                windy.get(id).setObciazenie(windy.get(id).getObciazenie()+this.towar);
                this.towar = 0;

            }
            else  {
                this.towar -= towar;
                windy.get(id).setObciazenie(windy.get(id).getObciazenie()+towar);
            }
        }
        else {
            if (windy.get(id).getObciazenie() - towar < windy.get(id).getWaga_pod()) {
                this.towar += windy.get(id).getObciazenie()-windy.get(id).getWaga_pod();
                windy.get(id).setObciazenie(getWaga_pod());
            }
            else {
                this.towar += towar;
                windy.get(id).setObciazenie(windy.get(id).getObciazenie()-towar);
            }

        }
        inform();
    }
    public void PrzeniesienieTowaru()
    {
        if (kierunekzaladunku) {
            if (this.towardoprzeniesienia - 5 <= 0) {
                towar += this.towardoprzeniesienia;
                this.towardoprzeniesienia = 0;
            }
            else {
                towar += 5;
                towardoprzeniesienia -= 5;
            }
        }
        else  {
            if (towar - 5 <= 0) {
                this.towardoprzeniesienia += towar;
                this.towar = 0;
            }
            else  {
                this.towardoprzeniesienia += 5;
                this.towar -= 5;
            }
        }
        inform();
    }

    public void PodfierdzDojazd(boolean kierunek,int id)
    {
        if (kierunek && !this.oczekiwanie[1]) {
            setOczekiwanie(false , true);

        }
        else if (!kierunek && !this.oczekiwanie[0]) {
            setOczekiwanie(false , false);
        }
        idZaparkowanejWindy = id;
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
        for (Listener l : kontrolery)
        {
            l.action();
        }
    }

    public Pietro(String nazwa, int numer) {
        super(nazwa);
        this.oczekiwanie = new boolean[]{false, false};
        this.numer = numer;
        this.kierunekzaladunku = true;
        this.edytowanie = false;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
            }
            this.PrzeniesienieTowaru();

        }

    }


}
