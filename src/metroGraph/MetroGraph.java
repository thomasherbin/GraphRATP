package metroGraph;

import data.Data;
import data.DataBuilder;

import java.util.ArrayList;


public class MetroGraph {
    public ArrayList<ArrayList<DirectEdge>> metroGraph;
    public int stationNumber;
    public int directEdgeNumber;

    public MetroGraph() throws Exception {
        stationNumber = 0;
        directEdgeNumber = 0;
        this.metroGraph = new ArrayList<>();
        DataBuilder dataToBuild = new DataBuilder();
        Data data = dataToBuild.getData();

        for (DirectEdge directEdge : data.getData()) {

            //Get nodes
            Station A =  directEdge.getA();
            Station B =  directEdge.getB();
            String metroLineNum = directEdge.getMetroLine();

            addDirectEdge(A, B, metroLineNum);
            //addDirectEdge(B, A, metroLineNum);
        }
        for (ArrayList<DirectEdge> directEdges : metroGraph) {
            for (DirectEdge directEdge : directEdges) {
                directEdge.getB().setStationPosition(getPosition(directEdge.getB()));
            }
        }
    }

    private void addDirectEdge(Station A, Station B, String metroLineNum) {
        boolean containsVertex = false;
        for (ArrayList<DirectEdge> directedEdges : metroGraph) {
            if (directedEdges.get(0).getA().equals(A)) {
                containsVertex = true;
                A.setStationPosition(directedEdges.get(0).getA().getStationPosition());
                directedEdges.add(new DirectEdge(A, B,metroLineNum));
                directEdgeNumber++;
            }
        }
        if (!containsVertex) {
            metroGraph.add(new ArrayList<>());
            A.setStationPosition(stationNumber);
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

    private int getPosition(Station station) {
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

    public DirectEdge getDirectEdge(Station v, Station w) {
        for (DirectEdge directEdge : stationDirectEdge(v)) {
            if (directEdge.getB().getId() == w.getId()) {
                return directEdge;
            }
        }
        return null;
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
