public class Pietro extends Urzadzenie {
    private boolean oczekiwanie;
    private int towar;

    public boolean getOczekiwanie() {
        return oczekiwanie;
    }

    public void setOczekiwanie(boolean oczekiwanie) {
        this.oczekiwanie = oczekiwanie;
    }

    public void ZmianaTowaru(int zmiana) {
        this.towar = zmiana;
    }

    public int PrzeniesienieTowaru() {
        int przeniesiony = towar;
        towar = 0;
        return przeniesiony;
    }

    public Pietro(String nazwa) {
        super(nazwa);
        this.oczekiwanie = false;
    }
}
