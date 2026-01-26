package controlersClasses.src;

import com.example.projekt_windy.MenuWindyKontroler;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Budynek {
    private ArrayList<Winda> windy;
    private ArrayList<Pietro> pietra = new ArrayList<>();
    private ArrayList<Listener> kontrolery = new ArrayList<>();
    private int szybkoscSymulacji;

    public void InformElevators(byte kierunek,int id)
    {
        Winda.anulujZadanie(id);
        for (Winda w : windy) {
            w.elekcja(id,kierunek);
        }
    }
    public void InformElevators(int id)
    {
        Winda.anulujZadanie(id);
    }

    public void informKontrolers() {
        for (Listener k : kontrolery) {
            k.action();
        }
    }

    public void dodajObciarzenie(int id, int obciazenie)
    {   if(obciazenie==0)
        {
            windy.get(id).setObciazenie(windy.get(id).getWaga_pod());
        }
        else {
        windy.get(id).setObciazenie(windy.get(id).getObciazenie() + obciazenie);
        }
    }

    public int podajRoznice(int id)
    {
       return windy.get(id-1).getObciazenie()-windy.get(id-1).getWaga_pod();
    }

    public void uruchomWinde(int id)
    {
        windy.get(id-1).uruchom();
    }

    public void przekazPotwierdzenieDojazdu(int id, double wysokosc) {
        pietra.get((int)(wysokosc/5)).PodfierdzDojazd(true,id);
        pietra.get((int)(wysokosc/5)).PodfierdzDojazd(false,id);
        pietra.get((int)(wysokosc/5)).setIdZaparkowanejWindy(id);
    }

    public boolean czyWindaPusta(int id,int obciazenie)
    {
        return windy.get(id).getObciazenie() - obciazenie < windy.get(id).getWaga_pod();
    }

    public void przywolajWinde(boolean kierunek,int id)
    {
        pietra.get(id).PrzywolajWinde(kierunek);
    }

    public void setKierunekZaladunkuPietra(boolean kierunek, int id)
    {
        pietra.get(id).setKierunekZaladunku(kierunek);
    }

    public void setSzybkoscSymulacji(int szybkoscSymulacji)
    {
        this.szybkoscSymulacji = szybkoscSymulacji;
    }

    public void zmienEdytowaniePietra(boolean edycja, int id) {
        pietra.get(id).setEdytowanie(edycja);
    }

    public void setTowardoprzeniesieniaPietra(int towardoprzeniesienia, int id) {
        pietra.get(id).setTowardoprzeniesienia(towardoprzeniesienia);
    }

    public void zaladunek(int numer,boolean kierunek,int id, int towar) throws OpenException
    {
        if (!windy.get(id).isOtwarteDrzwi())
        {
            throw new OpenException();
        }
        pietra.get(numer).ZaladunekTowaru(kierunek,id,towar);
    }

    public int getTowarDoprzeniesieniaWindy(int id) {
        return pietra.get(id).getTowardoprzeniesienia();
    }

    public int getSzybkoscSymulacji() {
        return szybkoscSymulacji;
    }

    public void interuptionWindy(double wysokosc, int idwindy)
    {
        if (wysokosc%1 == 0) {
            pietra.get((int) (wysokosc / 5)).interupt(idwindy);
            windy.get(idwindy-1).zatrzymaj();
        }
    }

    public void resumeWindy(int id)
    {
        pietra.get(id).resume();
    }

    public void addListener(Listener listener)
    {
        this.kontrolery.add(listener);
    }

    public StanBudynku getAktualnyStan() {
        ArrayList<Integer> towary = new ArrayList<>();
        ArrayList<Double> windy = new ArrayList<>();
        ArrayList<Boolean> stany = new ArrayList<>();
        for (Winda w : this.windy) {
            windy.add(w.getWyskosc());
        }
        for (Pietro p : this.pietra) {
            towary.add(p.getTowar());
            stany.add(p.getEdytowanie());
        }
        return new StanBudynku(towary,windy,stany);
    }


    public void ustawWindeNaKontroler(int id, MenuWindyKontroler secondController) {
        secondController.setPrzypisanaWinda(windy.get(id-1));
        secondController.setElevatorNumber(windy.get(id-1).getId());
        secondController.zmienObraz(windy.get(id-1).getPredkosc() == 0);
    }

    public Budynek(ArrayList<Winda> windy, int iloscPieter, Listener kontroler, int szybkoscSymulacji){
        this.szybkoscSymulacji = szybkoscSymulacji;
        this.windy = windy;
        for(int i = 0; i < iloscPieter; i++){
            pietra.add(new Pietro("Pietro"+ String.valueOf(i),i,this));
            Thread t1 = new Thread(pietra.get(i));
            t1.start();
        }
        for (Winda winda : windy) {
            winda.setBudynek(this);
            winda.addListener(kontroler);
            Thread t = new Thread(winda);
            t.start();
        }

    }
}
