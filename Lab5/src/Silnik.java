public class Silnik extends Komponent {
    private int obroty;
    private int maxobroty;

    public boolean uruchom(boolean stanWlaczenia) {
        if(stanWlaczenia == false) {
            return true;
        }
        else
        {
            return false;
        }

    }

    public boolean zatrzymaj(boolean stanWlaczenia) {
        if(stanWlaczenia == true) {
            return true;
        }
        else
        {
            return false;
        }

    }

    public void zwiekrzObroty(int zwiekrzenie)
    {
        if (zwiekrzenie + obroty > maxobroty) {
            obroty = maxobroty;
        }
        else  {
            obroty += zwiekrzenie;
        }
    }

    public void zmniejszObroty(int zmniejszenie)
    {
        if (-zmniejszenie + obroty < 0) {
            obroty = 0;
        }
        else  {
            obroty -= zmniejszenie;
        }
    }
    public int getObroty() {
        return obroty;
    }
    public int getMaxObroty() {
        return maxobroty;
    }
    Silnik(int waga, int cena, String nazwa, int maxobroty)
    {
        this.waga = waga;
        this.cena = cena;
        this.nazwa = nazwa;
        this.maxobroty = maxobroty;
        this.obroty = 0;
    }

}
