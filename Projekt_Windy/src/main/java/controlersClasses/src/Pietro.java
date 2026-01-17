package controlersClasses.src;

import java.util.ArrayList;

public class Pietro extends Urzadzenie implements Runnable {
    private int czasoczekiwania;
    private boolean[] oczekiwanie;
    private int towar;
    private int towardoprzeniesienia;
    private int numer;
    private int idZaparkowanejWindy;
    private boolean edytowanie;
    private boolean kierunekzaladunku;
    private Budynek budynek;
    private ArrayList<Listener> kontrolery = new ArrayList<>();

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

    public void setIdZaparkowanejWindy(int idZaparkowanejWindy) {
        this.idZaparkowanejWindy = idZaparkowanejWindy;
    }

    public void addListener(Listener l) {
        kontrolery.add(l);
    }


    public void ZaladunekTowaru(boolean kierunek,int id, int towar) throws NumberFormatException {
        if (towar<0)
        {
            throw new NumberFormatException();
        }
        else if (towar==0)
        {
            return;
        }
        if (kierunek) {
            if (this.towar - towar <= 0) {
                budynek.dodajObciarzenie(this.towar, id);
                this.towar = 0;

            }
            else  {
                this.towar -= towar;
                budynek.dodajObciarzenie(id, towar);
            }
        }
        else {
            if (budynek.czyWindaPusta(id, towar)) {
                this.towar += budynek.podajRoznice(id);
                budynek.dodajObciarzenie(0, id);
            }
            else {
                this.towar += towar;
                budynek.dodajObciarzenie(-towar, id);
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

    public void resume() throws IndexOutOfBoundsException
    {
        if (idZaparkowanejWindy == -1)
        {
            throw new IndexOutOfBoundsException();
        }
        budynek.uruchomWinde(idZaparkowanejWindy);
        czasoczekiwania = 0;
        idZaparkowanejWindy = -1;
    }

    public void interupt()
    {
        if (idZaparkowanejWindy != -1) {
            czasoczekiwania = 0;
        }
    }


    public void PrzywolajWinde(boolean kierunek) {
        if (kierunek && !this.oczekiwanie[1]) {
            setOczekiwanie(true , true);
            budynek.InformElevators((byte)1,this.numer);
        }
        else if (!kierunek && !this.oczekiwanie[0]) {
            setOczekiwanie(true , false);
            budynek.InformElevators((byte)-1,this.numer);
        }


    }
    public void inform()
    {
        for (Listener l : kontrolery)
        {
            l.action();
        }
    }

    public Pietro(String nazwa, int numer, Budynek budynek) {
        super(nazwa);
        this.oczekiwanie = new boolean[]{false, false};
        this.numer = numer;
        this.kierunekzaladunku = true;
        this.edytowanie = false;
        this.idZaparkowanejWindy = -1;
        this.czasoczekiwania = 0;
        this.budynek = budynek;
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
            if (idZaparkowanejWindy != -1) {
                if (czasoczekiwania > 10)
                {
                    resume();
                }
                else
                {
                    czasoczekiwania += 1;
                }
            }


        }

    }


}
