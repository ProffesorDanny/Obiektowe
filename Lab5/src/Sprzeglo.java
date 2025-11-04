public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;

    public void wcisnij() {
        stanSprzegla = true;
    }

    public void zwolnj() {
        stanSprzegla = false;
    }
    public boolean getStanSprzejla()
    {
        return stanSprzegla;
    }

    Sprzeglo(int cena, int waga, String nazwa)
    {
        this.cena = cena;
        this.waga = waga;
        this.nazwa = nazwa;
        stanSprzegla = false;
    }

}
