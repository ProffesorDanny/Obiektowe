import java.util.ArrayList;
import java.util.Random;

public class Lotto2_0 {

    public static void main(String[] args) {

            ArrayList<Integer> wyniki = new ArrayList<Integer>();
            ArrayList<Integer> dane = new ArrayList<Integer>();
            Random losowa = new Random();
            int temp;
            int ilosc = 0;
            long czass = System.currentTimeMillis();

            for (int j = 0; j < 6; j++) {
                dane.add(Integer.parseInt(args[j]));
            }
            while(wyniki.size() != dane.size()) {
                wyniki.clear();
                for (int i = 0; i < 6; i++) {
                    temp = losowa.nextInt(1,100);
                    if (wyniki.contains(temp) == false)
                    {
                        wyniki.add(temp);
                    }
                    else {
                        i--;
                    }
                }
                wyniki.retainAll(dane);
                ilosc++;
            }


            System.out.println(wyniki);
            System.out.println(dane);
            System.out.println(System.currentTimeMillis() - czass);
            System.out.println(ilosc);



    }

}