package graphDraw;

import data.DataBuilder;
import edu.princeton.cs.introcs.StdDraw;
import metroGraph.DirectEdge;
import metroGraph.*;

import java.util.ArrayList;

public class GraphDraw  implements DrawSettings {
    private MetroGraph metroGraph;

    public GraphDraw() throws Exception {
        StdDraw.setCanvasSize(frameWidth, frameHeight);
        StdDraw.setXscale(0, frameWidth);
        StdDraw.setYscale(0, frameHeight);

        DataBuilder data = new DataBuilder();
        MetroGraph metroGraph = new MetroGraph(data.getData());

        for (ArrayList<DirectEdge> stations : metroGraph.getMetroGraph()) {
            for (DirectEdge directEdge : stations) {
                new DirectEdgeDraw(directEdge);
            }
        }




    }



}




