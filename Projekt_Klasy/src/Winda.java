

public class Winda extends Urzadzenie {
    private double predkosc;
    private double wyskosc;
    private Silnik silnik;

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
        }
        else if (predkosc < 0 && wyskosc + predkosc * odstep < destynacja) {
            wyskosc += predkosc * odstep;
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

    public Winda(int waga_pod, int obciazenie_max, String nazwa, Silnik silnik) {
        super(waga_pod, obciazenie_max, nazwa);
        this.silnik = silnik;
    }

}