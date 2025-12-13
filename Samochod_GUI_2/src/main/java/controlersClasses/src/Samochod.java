package controlersClasses.src;

public class Samochod {
    private boolean stanWlacznia;
    private String nrRejestru;
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
    public Silnik getSilnik()
    {
        return silnik;
    }
    public SkrzyniaBiegow getSkrzyniaBiegow()
    {
        return skrzyniaBiegow;
    }
    public void DodajGazu()
    {
        silnik.zwiekrzObroty(400);

    }
    public void  UpuscGazu()
    {
        silnik.zmniejszObroty(400);
    }

    public Samochod(int waga, String model, Pozycja pozycja, Silnik silnik, SkrzyniaBiegow skrzyniaBiegow, String nrRejestru)
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
    @Override
    public String toString() {
        // This is what the ComboBox will display by default now
        return model + " ($" + waga + ")";
    }

}
