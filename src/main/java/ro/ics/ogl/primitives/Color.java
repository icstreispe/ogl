package ro.ics.ogl.primitives;

/**
 * Created by X13 on 31.12.2016.
 */
public class Color {

    public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f, 0f);
    public static final Color DARK_GREEN = new Color(0.0f, 0.5f, 0.0f, 0f);
    public static final Color RED = new Color(1.0f, 0.0f, 0.0f, 0f);
    public static final Color MAROON = new Color(0.5f, 0.0f, 0.0f, 0f);
    public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f, 0f);
    public static final Color YELLOW = new Color(1.0f, 1.0f, 0.0f, 0f);
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f, 0f);
    public static final Color ORANGE = new Color(1.0f, 0.5f, 0.0f, 0f);
    public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f, 0f);
    public static final Color MAGENTA = new Color(1.0f, 0.0f, 1.0f, 0f);



    private float r, g, b, alfa;

    public Color (float r, float g, float b, float alfa){
        this.r = r;
        this.g = g;
        this.b = b;
        this.alfa = alfa;
    }

    public float[] toArray(){
        return new float[] {r, g, b, 0.1f};
    }   //for transparency but its not working

    /*
    public static final float [] GREEN_V = GREEN.toArray();
    public static final float [] RED_V = RED.toArray();
    public static final float [] BLUE_V = BLUE.toArray();
    public static final float [] YELLOW_V = YELLOW.toArray();
    public static final float [] WHITE_V = WHITE.toArray();
    public static final float [] ORANGE_V = ORANGE.toArray();
    public static final float [] BLACK_V = BLACK.toArray();
    public static final float [] MAGENTA_V = MAGENTA.toArray();
*/
}
