package graphOperations;

import metroGraph.DirectEdge;
import metroGraph.MetroGraph;
import metroGraph.Station;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SP extends MetroGraph {
    boolean[] marked;
    Station[] previous;
    double[] distance;
    Station startNode;
    private Station endNode;
    private int endNodePos;
    int startNodePos;

    public SP() throws Exception {
        this.startNodePos = 0;
        this.startNode = getStation(startNodePos);
        this.endNodePos = stationNumber;
        this.endNode = getStation(endNodePos);
        this.marked = new boolean[stationNumber];
        this.previous = new Station[stationNumber];
        this.distance = new double[stationNumber];
    }

    public SP(int startNodePos) throws Exception {
        this.startNodePos = startNodePos;
        this.startNode = getStation(startNodePos);
        this.endNodePos = stationNumber;
        this.endNode = getStation(endNodePos);
        this.marked = new boolean[stationNumber];
        this.previous = new Station[stationNumber];
        this.distance = new double[stationNumber];
    }

    public SP(int startNodePos, int endNodePos) throws Exception {
        this.startNodePos = startNodePos;
        this.startNode = getStation(startNodePos);
        this.endNodePos = endNodePos;
        this.endNode = getStation(endNodePos);
        this.marked = new boolean[stationNumber];
        this.previous = new Station[stationNumber];
        this.distance = new double[stationNumber];
    }



    private ArrayList<Station> path() {
        int vPos = endNodePos;
        ArrayList<Station> SPr = new ArrayList<>();
        if (this.hasPathTo(vPos)) {
            Station i = this.previous[vPos];
            int iPos = i.getStationPosition();
            SPr.add(endNode);
            SPr.add(i);
            while (iPos != startNodePos) {
                i = this.previous[iPos];
                iPos = this.previous[iPos].getStationPosition();
                if (iPos != 0) {
                    SPr.add(i);
                }
            }
            SPr.add(startNode);
            Collections.reverse(SPr);
        }
        return SPr;
    }



    private ArrayList<DirectEdge> directEdgeSP() {
        ArrayList<Station> path = path();
        ArrayList<DirectEdge> directEdgeSP = new ArrayList<>();
        if (!path.isEmpty()) {
            for (int i = 1; i < path.size(); i++) {
                Station A = path.get(i - 1);
                Station B = path.get(i);
                directEdgeSP.add(getDirectEdge(A, B));
            }
        }
        return directEdgeSP;
    }

    public String printFullSP() {
        String str = "";
        ArrayList<Station> path = path();
        if (!path.isEmpty()) {
            str += "Shortest path from "+path.get(0).getName()+" to "+path.get(path.size()-1).getName()+" :\n";
            for (Station station : path) {
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

    private boolean hasPathTo(int pos) {
        return  this.marked[pos];
    }

    private double distTo() {
        return distance[endNodePos];
    }



    @Override
    public String toString() {
        return "marked=" + Arrays.toString(marked) + ",\nprevious=" + Arrays.toString(previous) +",\ndistance=" + Arrays.toString(distance) ;
    }

}
