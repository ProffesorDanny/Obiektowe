
import java.lang.foreign.SymbolLookup;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
public class Pozycja {
    private double x;
    private double y;
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void Przemiesc(Pozycja cel,double v, double t) {
        if (sqrt(pow(this.x-cel.getX(),2)+pow(this.y-cel.getY(),2))>t*v)
        {
            double xst = this.x;
            this.x+=t*v*(cel.getX()-this.x)/sqrt(pow(this.x-cel.getX(),2)+pow(this.y-cel.getY(),2));
            this.y+=t*v*(cel.getY()-this.y)/sqrt(pow(xst-cel.getX(),2)+pow(this.y-cel.getY(),2));
        }
        else
        {
            this.x=cel.getX();
            this.y=cel.getY();
        }

    }
    Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
