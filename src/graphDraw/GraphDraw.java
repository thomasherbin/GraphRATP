package graphDraw;

import metroGraph.DirectEdge;
import metroGraph.MetroGraph;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GraphDraw extends JFrame implements DrawSettings {
    MetroGraph metroGraph;

    public GraphDraw() throws Exception {
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        metroGraph = new MetroGraph();
        /*
        Container container = getContentPane();
        for (ArrayList<DirectEdge> stations : metroGraph.getMetroGraph()) {
            for (DirectEdge directEdge : stations) {
                container.add(new DirectEdgeDraw(directEdge));
                break;
            }
            break;
        }

         */



    }

    public void paint(Graphics g) {
        for (ArrayList<DirectEdge> stations : metroGraph.getMetroGraph()) {
            for (DirectEdge directEdge : stations) {
                DirectEdgeDraw directEdgeDraw = new DirectEdgeDraw(directEdge);
                g.fillOval(directEdgeDraw.getX1(), directEdgeDraw.getY1(), stationWidth, stationHeight);
                g.fillOval(directEdgeDraw.getX2(), directEdgeDraw.getY2(), stationWidth, stationHeight);
                g.drawLine(directEdgeDraw.getX1(), directEdgeDraw.getY1(),directEdgeDraw.getX2(),directEdgeDraw.getY2());
            }
        }
    }

}




