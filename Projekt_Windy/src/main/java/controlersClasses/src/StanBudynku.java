package controlersClasses.src;

import java.util.ArrayList;

public class StanBudynku {
    public ArrayList<Integer> towary;
    public ArrayList<Double> wysokosci;
    public ArrayList<Boolean> stany;

    public StanBudynku(ArrayList<Integer> towary,ArrayList<Double> wysokosci, ArrayList<Boolean> stany) {
        this.towary = towary;
        this.wysokosci = wysokosci;
        this.stany = stany;
    }
}
