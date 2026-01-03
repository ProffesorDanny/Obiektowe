package controlersClasses.src;

public abstract class Urzadzenie {
    private int waga_pod;
    private int obciazenie;
    private int obciazenie_max;
    private String nazwa;
    private boolean aktywacja;
    public int getWaga_pod() {
        return waga_pod;
    }
    public int getObciazenie() {
        return obciazenie;
    }
    public String getNazwa() {
        return nazwa;
    }

    public int getObciazenie_max() {
        return obciazenie_max;
    }

    public boolean isAktywacja() {
        return aktywacja;
    }
    public void setObciazenie(int obciazenie) {
        this.obciazenie = obciazenie;
    }
    public void setAktywacja(boolean aktywacja) {
        this.aktywacja = aktywacja;
    }
    public Urzadzenie(int waga_pod, int obciazenie_max, String nazwa) {
        this.waga_pod = waga_pod;
        this.obciazenie_max = obciazenie_max;
        this.nazwa = nazwa;
        this.aktywacja  = false;
        this.obciazenie = waga_pod;
    }
    public Urzadzenie( String nazwa) {
        this.nazwa = nazwa;
        this.aktywacja = true;
    }
}
