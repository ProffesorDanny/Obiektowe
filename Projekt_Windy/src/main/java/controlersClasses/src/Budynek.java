package controlersClasses.src;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Budynek {
    private ArrayList<Winda> windy = new ArrayList<>();
    private ArrayList<Pietro> pietra = new ArrayList<>();

    public void InformElevators(byte kierunek,int id)
    {
        for (Winda w : windy) {
            w.action(kierunek,id);
        }
    }
    public void dodajObciarzenie(int obciazenie,int id)
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
       return windy.get(id).getObciazenie()-windy.get(id).getWaga_pod();
    }

    public void uruchomWinde(int id)
    {
        windy.get(id-1).uruchom();
    }

    public boolean czyWindaPusta(int obciazenie,int id)
    {
        return windy.get(id).getObciazenie() - obciazenie < windy.get(id).getWaga_pod();
    }

    public Budynek(int iloscWind, int iloscPieter){
            for(int i = 0; i < iloscWind; i++){
                Silnik silnik = new Silnik(80, 1000, "Silneks", 10);
                windy.add(new Winda(200,600,"Windeks", silnik));
            }
            for(int i = 0; i < iloscPieter; i++){
                pietra.add(new Pietro("Pietro"+ String.valueOf(i),i,this));
            }
    }
}
