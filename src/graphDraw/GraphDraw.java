package graphDraw;

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

        this.metroGraph = new MetroGraph();
        directEdgeDrawBuild();
    }

    public GraphDraw(MetroGraph metroGraph) {
        StdDraw.setCanvasSize(frameWidth, frameHeight);
        StdDraw.setXscale(0, frameWidth);
        StdDraw.setYscale(0, frameHeight);

        this.metroGraph = metroGraph;
        directEdgeDrawBuild();
    }

    private void directEdgeDrawBuild() {
        for (ArrayList<DirectEdge> stations : metroGraph.getMetroGraph()) {
            for (DirectEdge directEdge : stations) {
                //System.out.println(directEdge.toString());
                boolean AisStationMultyLine = isStationMultyLine(directEdge.getA());
                boolean BisStationMultyLine = isStationMultyLine(directEdge.getB());
                new DirectEdgeDraw(directEdge, AisStationMultyLine, BisStationMultyLine);
            }
        }
    }

    private boolean isStationMultyLine(Station station) {
        ArrayList<DirectEdge> stations = metroGraph.stationDirectEdge(station);
        if (stations != null) {
            for (DirectEdge directEdge :stations) {
                if (directEdge.getA().getId() == station.getId()) {
                    return  (numberLigneStation(stations) > 1);
                }
            }
        }
        return false;
    }

    private int numberLigneStation(ArrayList<DirectEdge> stations) {
        ArrayList<String> lignes = new ArrayList<>();
        for (DirectEdge directEdge : stations) {
            if (!lignes.contains(directEdge.getMetroLine())) {
                lignes.add(directEdge.getMetroLine());
            }
        }
        return lignes.size();
    }


}




