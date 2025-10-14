import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lotto {

    public static void main(String[] args) {
        ArrayList<Integer> wyniki = new ArrayList<Integer>();
        ArrayList<Integer> dane = new ArrayList<>();
        Scanner wejscie = new Scanner(System.in);
        Random losowa = new Random();
        for(int i = 0; i < 6; i++){
            wyniki.add(losowa.nextInt(1,100));
        }

        for(int j = 0; j < 6; j++){
            dane.add(wejscie.nextInt());
        }
        System.out.println(wyniki);
        System.out.println(dane);
        dane.retainAll(wyniki);
        System.out.println(dane.size());
    }
}

