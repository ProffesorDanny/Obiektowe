public class SkrzyniaBiegow extends Komponent
{
    private int aktualnyBieg;
    private int iloscBiegow;
    private int aktualnePrzelorzenie;
    private Sprzeglo sprzeglo;

    public void zwiekrzBieg() {
        if (aktualnyBieg < iloscBiegow && sprzeglo.getStanSprzejla()) {
            aktualnyBieg += 1;
            aktualnePrzelorzenie *=2;
        }
    }

    public void zmniejszBieg() {
        if (aktualnyBieg > 1 && sprzeglo.getStanSprzejla()) {
            aktualnyBieg -= 1;
            aktualnePrzelorzenie /=2;
        }
    }

    public int getAktualnyBieg() {
        return aktualnyBieg;
    }
    public int getAktualnePrzelorzenie() {
        return aktualnePrzelorzenie;
    }
    public int getIloscBiegow() {
        return iloscBiegow;
    }

    SkrzyniaBiegow(int iloscBiegow, int waga, int cena, String nazwa , Sprzeglo sprzeglo)
    {
        this.iloscBiegow = iloscBiegow;
        this.waga = waga;
        this.cena = cena;
        this.nazwa = nazwa;
        this.sprzeglo = sprzeglo;
    }

}
