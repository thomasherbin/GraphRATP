package graphDraw;

import metroGraph.DirectEdge;
import metroGraph.Station;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DirectEdgeDraw extends JPanel implements DrawSettings {
    private int x1;
    private int y1;
    private int x2;
    private int y2;


    public DirectEdgeDraw(DirectEdge directEdge) {
        Station A = directEdge.getA();
        Station B = directEdge.getB();
        this.x1 = posLon(A.getLon());
        this.y1 = posLat(A.getLat());
        this.x2 = posLon(B.getLon());
        this.y2 = posLat(B.getLat());
        System.out.println(this.toString());
    }




    public int posLat(double pos) {
        return posCalculator(minLat, maxLat, doublePosToInt(pos), frameHeight);
    }

    public int posLon(double pos) {
        return posCalculator(minLon, maxLon, doublePosToInt(pos), frameWidth);
    }

    private int posCalculator(int min, int max, int pos, int scale) {
        /*System.out.println(min);
        System.out.println(max);
        System.out.println(pos);
        System.out.println(scale);
        */
        return (int) (((double)(pos - min) /(double) (max - min))*scale);
    }

    private int doublePosToInt(double pos) {
        DecimalFormat decimalFormat = new DecimalFormat("#.000000");
        String floatAsString = decimalFormat.format(pos);
        floatAsString = floatAsString.split(",")[1];
        return Integer.parseInt(floatAsString);
    }

    /*
    public void paintComponent(Graphics g) {
        System.out.println("Je suis exécutée !");
        g.fillOval(x1, y1, stationWidth, stationHeight);
        //g.fillOval(x2, y2, stationWidth, stationHeight);
        //g.drawLine(x1, y1,x2,y2);
    }

     */

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    @Override
    public String toString() {
        return "DirectEdgeDraw{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                '}';
    }
}
