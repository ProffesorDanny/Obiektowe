import org.junit.Test;

import static org.junit.Assert.*;

public class CodingBatTest {

    @org.junit.Test
    public void sleepIn() {
        assertEquals(true, new CodingBat().sleepIn(true,true));
        assertEquals(false, new CodingBat().sleepIn(true,false));
        assertEquals(true, new CodingBat().sleepIn(false,true));
        assertEquals(true, new CodingBat().sleepIn(false,false));
    }


    @Test
    public void or35() {
        CodingBat codingBat = new CodingBat();
        assertEquals(true, codingBat.or35(3) );
        assertEquals(true, codingBat.or35(10) );
        assertEquals(false, codingBat.or35(8) );
    }

    @Test
    public void lucky13() {
        CodingBat codingBat = new CodingBat();
        int[] n = {0,2,3};
        int[] n1 = {2,7,2,8};
        int[] n2 = {2,7,2,8,1};
        assertEquals(false,codingBat.lucky13(n));
        assertEquals(true,codingBat.lucky13(n1));
        assertEquals(false,codingBat.lucky13(n2));
    }

    @Test
    public void hasBad() {
        CodingBat codingBat = new CodingBat();
        assertEquals(true,codingBat.hasBad("badxx"));
        assertEquals(true,codingBat.hasBad("bad"));
        assertEquals(false,codingBat.hasBad("xxbadxx"));
        assertEquals(false,codingBat.hasBad("ba"));
    }
}