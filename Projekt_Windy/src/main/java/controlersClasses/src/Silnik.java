package controlersClasses.src;

public class Silnik extends Urzadzenie {
    private double obroty_pods;
    private double obroty_obc;
    private boolean aktywnosc;

    public double poruszaj(boolean kierunek, int aktualna_waga) throws MassException, ReadynessException {
        if(this.getObciazenie_max()<this.getObciazenie())
        {
            throw new MassException();
        }
        else if(!aktywnosc) {
            throw new ReadynessException();
        }
        obroty_obc = kierunek ? obroty_pods/Math.log10(aktualna_waga) : -obroty_pods/Math.log10(aktualna_waga);
        return obroty_obc;
    }
    public void zatrzymaj()
    {
        aktywnosc = false;
    }
    public void uruchom()
    {
        aktywnosc = true;
    }

    @Override
    public String toString()
    {
        return getNazwa();
    }

    public Silnik(int waga_pod, int obciazenie_max, String nazwa, double obroty_pods) {
        super(waga_pod, obciazenie_max, nazwa);
        this.obroty_pods = obroty_pods;
        this.obroty_obc = 0;
        this.uruchom();
    }

    public Silnik Copy()
    {
        return new Silnik(this.getWaga_pod(),this.getObciazenie_max(),this.getNazwa(),this.obroty_pods);
    }
}
