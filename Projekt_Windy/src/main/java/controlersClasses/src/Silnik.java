package controlersClasses.src;

public class Silnik extends Urzadzenie {
    private double obroty_pods;
    private double obroty_obc;
    private boolean aktywnosc;

    public double poruszaj(boolean kierunek, int aktualna_waga) throws  Exception{
        if(this.getObciazenie_max()<this.getObciazenie() && !aktywnosc)
        {
            throw new Exception();
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

    public Silnik(int waga_pod, int obciazenie_max, String nazwa, double obroty_pods) {
        super(waga_pod, obciazenie_max, nazwa);
        this.obroty_pods = obroty_pods;
        this.obroty_obc = 0;
    }
}
