package metroGraph;

import data.Data;
import data.DataBuilder;

import java.util.ArrayList;


public abstract class MetroGraph {
    private ArrayList<ArrayList<DirectEdge>> metroGraph;
    private int stationNumber;
    private int directEdgeNumber;

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


    public abstract ArrayList<Station> SP();

    protected abstract double distTo();


    private ArrayList<DirectEdge> directEdgeSP() {
        ArrayList<Station> SP = SP();
        ArrayList<DirectEdge> directEdgeSP = new ArrayList<>();
        if (!SP.isEmpty()) {
            for (int i = 1; i < SP.size(); i++) {
                Station A = SP.get(i - 1);
                Station B = SP.get(i);
                directEdgeSP.add(getDirectEdge(A, B));
            }
        }
        return directEdgeSP;
    }

    public String printFullSP() {
        String str = "";
        ArrayList<Station> SP = SP();
        if (!SP.isEmpty()) {
            str += "Shortest path from "+SP.get(0).getName()+" to "+SP.get(SP.size()-1).getName()+" :\n";
            for (Station station : SP) {
                str +=" -> "+ station.toString();
            }
            str +="\nDistance : "+ distTo();
        } else {
            str +="No link between this two nodes";
        }
        return str;
    }

    public String printShortSP() {
        String str = "";
        ArrayList<DirectEdge> directEdgeSP = directEdgeSP();
        if (!directEdgeSP.isEmpty()) {
            str += "Shortest path from "+directEdgeSP.get(0).getA().getName()+" to "+directEdgeSP.get(directEdgeSP.size()-1).getB().getName()+" :\n";
            String metroLineNum = directEdgeSP.get(0).getMetroLine();
            str += "Ligne "+ metroLineNum + " : ";
            str += directEdgeSP.get(0).getA().toString() + " -> ";
            int i = 1;
            while (i <= directEdgeSP.size()-1) {
                if (!directEdgeSP.get(i).getMetroLine().equals(metroLineNum)) {
                    metroLineNum = directEdgeSP.get(i).getMetroLine();
                    str += directEdgeSP.get(i-1).getB().toString() + "\n";
                    str += "Ligne "+ metroLineNum + " : ";
                    str += directEdgeSP.get(i).getA().toString() + " -> ";
                }
                i++;
            }
            str += directEdgeSP.get(directEdgeSP.size()-1).getB().toString() + "\n";
            str +="Distance : "+ distTo();
        } else {
            str +="No link between this two nodes";
        }
        return str;
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
