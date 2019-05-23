package metroGraph;

import data.Data;

import java.util.ArrayList;


public class MetroGraph {
    private ArrayList<ArrayList<DirectEdge>> metroGraph;
    private int stationNumber;
    private int directEdgeNumber;

    public MetroGraph(Data data) {
        this.metroGraph = new ArrayList<>();

        for (DirectEdge directEdge : data.getData()) {

            //Get nodes
            Station A =  directEdge.getA();
            Station B =  directEdge.getB();
            String metroLineNum = directEdge.getMetroLine();

            addDirectEdge(A, B, metroLineNum);
            addDirectEdge(B, A, metroLineNum);

        }
    }

    public void addDirectEdge(Station A, Station B, String metroLineNum) {
        boolean containsVertex = false;
        for (ArrayList<DirectEdge> directedEdges : metroGraph) {
            if (directedEdges.get(0).getA().equals(A)) {
                containsVertex = true;
                directedEdges.add(new DirectEdge(A, B,metroLineNum));
                directEdgeNumber++;
            }
        }
        if (!containsVertex) {
            metroGraph.add(new ArrayList<>());
            stationNumber++;
            metroGraph.get(metroGraph.size() - 1).add(new DirectEdge(A, B, metroLineNum));
            directEdgeNumber++;
        }
    }

    public ArrayList<DirectEdge> stationDirectEdge(Station station) {
        for (ArrayList<DirectEdge> stations : metroGraph) {
            if (stations.get(0).getA().getId() == station.getId()) {
                return stations;
            }
        }
        return null;
    }

    public int getPosition(Station station) {
        int pos = 0;
        for (ArrayList<DirectEdge> stations : metroGraph) {
            if (stations.get(0).getA().getId() == station.getId()) {
                return pos;
            }
            pos++;
        }
        return -1;
    }

    public Station getStation(int pos) {
        return metroGraph.get(pos).get(0).getA();
    }


    public ArrayList<Station> neighbors(Station station) {
        ArrayList<Station> neighbors = new ArrayList<>();
        for (DirectEdge directEdge : stationDirectEdge(station)) {
            neighbors.add(directEdge.getB());
        }
        return neighbors;
    }

    public double getWeightDirectEdge(Station v, Station w) {
        for (DirectEdge directEdge : stationDirectEdge(v)) {
            if (directEdge.getB().getId() == w.getId()) {
                return directEdge.getWeight();
            }
        }
        return -1;
    }

    public ArrayList<ArrayList<DirectEdge>> getMetroGraph() {
        return metroGraph;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public int getDirectEdgeNumber() {
        return directEdgeNumber;
    }

    @Override
    public String toString() {
        String str = "";
        for (ArrayList<DirectEdge> stations : metroGraph) {
            for (DirectEdge directEdge : stations) {
                str += directEdge.toString() + ",";
            }
            str += "\n";
        }
        return str;
    }
}
