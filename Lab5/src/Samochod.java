public class Samochod {
    private boolean stanWlacznia;
    private int nrRejestru;
    private String model;
    private int predkoscMax;
    private SkrzyniaBiegow skrzyniaBiegow;
    private Silnik silnik;
    private int waga;
    private Pozycja pozycja;

    public void wlacz()
    {
        stanWlacznia = silnik.uruchom(stanWlacznia);
    }

    public void wylacz()
    {
        stanWlacznia = silnik.zatrzymaj(stanWlacznia);
    }
    public boolean getStanWlacznia()
    {
        return stanWlacznia;
    }
    public int getAktpredkosc()
    {
        return silnik.getObroty()*skrzyniaBiegow.getAktualnePrzelorzenie();
    }
    public Pozycja getPozycja()
    {
        return pozycja;
    }

    Samochod(int waga, String model, Pozycja pozycja, Silnik silnik, SkrzyniaBiegow skrzyniaBiegow, int nrRejestru)
        {
            this.waga = waga;
            this.model = model;
            this.pozycja = pozycja;
            this.silnik = silnik;
            this.skrzyniaBiegow = skrzyniaBiegow;
            this.nrRejestru = nrRejestru;
            stanWlacznia = false;
            predkoscMax = silnik.getMaxObroty()*2^skrzyniaBiegow.getIloscBiegow();


        }

}
