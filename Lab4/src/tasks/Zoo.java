
package tasks;
import animals.*;

import java.util.Random;


public class Zoo {
    Animal[] animals = new Animal[100];
    Random rand = new Random();

    {
        for(int i = 0; i < animals.length; i++) {
            if(rand.nextInt(100) < 33) {
                animals[i] = new Dog();
            }
            else if(rand.nextInt(100) < 66) {
                animals[i] = new Parot();
            }
            else {
                animals[i] = new Snake();
            }

            }
    }

    public int GetAllLegs()
    {
        int legs = 0;
        for(int i = 0; i < animals.length; i++) {
            legs += animals[i].legs;
        }
        return legs;
    }

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        for (int i = 0; i<zoo.animals.length; i++) {
            int legs = zoo.GetAllLegs();
            String desc = zoo.animals[i].getDescription();
            zoo.animals[i].getSound();
            System.out.println(desc);
            System.out.println(legs);
        }

    }
}



