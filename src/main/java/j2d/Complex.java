package j2d;

/**
 * Created by X13 on 18.02.2017.
 */
public class Complex {

    double x, y;
    //x^x - y^2 +2*x*yi

    Complex ( double x, double y){
        this.x = x;
        this.y = y;
    }

    public Complex mul (Complex c){
        return new Complex (c.x*x-c.y*y, c.y*x+c.x*y);
    }

    public Complex add (Complex c){
        return new Complex (c.x+x, c.y+y);
    }

    public double abs (){
        return Math.sqrt(x*x+y*y);
    }
}
