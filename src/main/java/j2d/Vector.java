package j2d;

/**
 * Created by X13 on 17.02.2017.
 */
public class Vector {
    double x, y;
    double alfa;


    public Vector translate (double lx, double ly){
        Vector p = new Vector();
        p.x = x + lx;
        p.y = y + ly;
        p.alfa = Math.atan(ly/lx);
        //System.out.println("translate xy: " + p.print());
        return p;
    }

    public Vector translate (double lz){
        Vector p = new Vector();
        p.x = x + lz*Math.cos(alfa);
        p.y = y + lz*Math.sin(alfa);
        p.alfa = alfa;
        //System.out.println("translate z: " + p.print());
        return p;
    }

    public Vector rotate (double alfa){
        Vector p = new Vector();
        p.x = x;
        p.y = y;
        p.alfa = this.alfa + alfa;
        //System.out.println("rotate: " + p.print());
        return p;
    }

    public String print (){
        return String.format("x: %.2f y: %.2f alfa: %.0f", x, y,  alfa*180/Math.PI);
    }
}
