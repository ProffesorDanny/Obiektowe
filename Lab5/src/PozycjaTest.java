import org.junit.Test;
import static org.junit.Assert.*;

public class PozycjaTest {

    @org.junit.Test
    public void przemiesc() {
        Pozycja test = new Pozycja(2,3);
        Pozycja test2 = new Pozycja(5,7);
        Pozycja test3 = new Pozycja(2.6,3.8);
        test.Przemiesc(test2,10,0.1);
        assertEquals(test3.getX(),test.getX(),0.000001);
        assertEquals(test3.getY(),test.getY(),0.001);
        test.Przemiesc(test2,10,20);
        assertEquals(5,test.getX(),0.000001);
        assertEquals(7,test.getY(),0.001);
    }
}