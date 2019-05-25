package graphDraw;

import edu.princeton.cs.introcs.StdDraw;
import metroGraph.DirectEdge;
import metroGraph.Station;

import java.awt.*;
import java.text.DecimalFormat;

public class DirectEdgeDraw  implements DrawSettings {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private String color;
    private boolean AisStationMultyLine;
    private boolean BisStationMultyLine;


    public DirectEdgeDraw(DirectEdge directEdge, boolean AisStationMultyLine, boolean BisStationMultyLine) {
        Station A = directEdge.getA();
        Station B = directEdge.getB();
        this.x1 = posLon(A.getLon());
        this.y1 = posLat(A.getLat());
        this.x2 = posLon(B.getLon());
        this.y2 = posLat(B.getLat());
        this.color = findColor(directEdge.getMetroLine());
        this.AisStationMultyLine = AisStationMultyLine;
        this.BisStationMultyLine = BisStationMultyLine;
        paintComponent();
        //System.out.println(this.toString());
    }


    private String findColor(String metroNumLine) {
        for (String[] metroLine : colorLigne) {
            if (metroLine[0].equals(metroNumLine)) {
                return metroLine[1];
            }
        }
        return "#ffffff";
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


    public void paintComponent() {
        StdDraw.setPenColor(Color.decode(color));
        StdDraw.line(x1,y1,x2,y2);
        if (AisStationMultyLine) {
            StdDraw.setPenColor(Color.white);
            StdDraw.filledCircle(x1, y1, stationRadius);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.circle(x1, y1, stationRadius);
        } else {
            StdDraw.setPenColor(Color.decode(color));
            StdDraw.filledCircle(x1, y1, stationRadius);
        }
        if (BisStationMultyLine) {
            StdDraw.setPenColor(Color.white);
            StdDraw.filledCircle(x2, y2, stationRadius);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.circle(x2, y2, stationRadius);
        } else {
            StdDraw.setPenColor(Color.decode(color));
            StdDraw.filledCircle(x2, y2, stationRadius);
        }
    }



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
