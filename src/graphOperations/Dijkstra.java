package graphOperations;

import metroGraph.MetroGraph;
import metroGraph.Station;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra extends MetroGraph {
    private boolean[] marked;
    private Station[] previous;
    private double[] distance;
    private Station startNode;
    private Station endNode;
    private int endNodePos;
    private int startNodePos;
    private ArrayList<Station> dijkstraList;

    public Dijkstra() throws Exception {

    }

    public Dijkstra(int startNodePos) throws Exception {
        this.startNodePos = startNodePos;
        this.startNode = getStation(startNodePos);
        dijkstraAlgorithm();
    }

    public Dijkstra(int startNodePos, int endNodePos) throws Exception {
        this.startNodePos = startNodePos;
        this.startNode = getStation(startNodePos);
        this.endNodePos = endNodePos;
        this.endNode = getStation(endNodePos);
        dijkstraAlgorithm();

    }

    private void dijkstraAlgorithm() {
        //Initialisation
        Station node = startNode;
        int nodePos = startNodePos;
        this.dijkstraList = new ArrayList<>();
        this.marked = new boolean[getStationNumber()];
        this.previous = new Station[getStationNumber()];
        this.distance = new double[getStationNumber()];
        dijkstraList.add(node);
        for (int i = 0; i < distance.length; i++) { distance[i]= Double.POSITIVE_INFINITY; }
        distance[nodePos] = 0;
        //System.out.println("Start Node : "+node.toString());
        //System.out.println("Start Node Position : "+nodePos);
        //System.out.println(this.printArrays());


        while (!allMarked() && node != null) {
            //System.out.println("Dijkstra list : "+this.toString());
            //System.out.println("Neighbors of current Node : "+G.neighbors(node).toString());

            for (Station neighbor : neighbors(node)) {
                int neighborPos = neighbor.getStationPosition();
                //System.out.println("Neighbor position : "+neighborPos);
                if (!marked[neighborPos]) {
                    if (distance[neighborPos] > distance[nodePos] + getWeightDirectEdge(node, neighbor)) {
                        distance[neighborPos] = distance[nodePos] + getWeightDirectEdge(node, neighbor);
                        previous[neighborPos] = node;
                        //System.out.println(this.distance[nodePos]+ " + "+G.getWeightDirectEdge(node,neighbor) + " "+ this.distance[neighborPos]);
                        //System.out.println("Distance chosen : "+this.distance[neighborPos]);
                    }
                }
            }

            marked[nodePos] = true;
            node = nextNode();
            nodePos = node.getStationPosition();
            dijkstraList.add(node);

            //System.out.println(this.toString());
            //System.out.println("Current Node : "+node);
            //System.out.println("Current Node Position : "+nodePos);
        }
        dijkstraList.remove(dijkstraList.size() - 1);


        //System.out.println(this.toString());
        //System.out.println(this.printArrays());
    }



    private boolean allMarked() {
        for (int i = 0; i < this.marked.length; i++) {
            if (!marked[i]) {
                return false;
            }
        }
        return true;
    }

    private Station nextNode() {
        double min = Double.POSITIVE_INFINITY;
        int nodePos = 0;
        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) {
                if (min != Math.min(min, distance[i])) {
                    nodePos = i;
                }
                min = Math.min(min, distance[i]);
            }
        }
        if (distance[nodePos] == Double.POSITIVE_INFINITY) {
            return null;
        } else {
            return getStation(nodePos);
        }
    }

    public boolean hasPathTo(int pos) {
        if (this.marked[pos]) {
            return true;
        } else {
            return false;
        }
    }

    public double distTo() {
        return distance[endNodePos];
    }

    public ArrayList<Station> SP() {
        int vPos = endNodePos;
        ArrayList<Station> SP = new ArrayList<>();
        if (this.hasPathTo(vPos)) {
            Station i = this.previous[vPos];
            int iPos = i.getStationPosition();
            ArrayList<Station> SPr = new ArrayList<>();
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
            for (int k = SPr.size() - 1; k >= 0; k--) {
                SP.add(SPr.get(k));
            }
        }
        return SP;
    }



    private String printArrays() {
        return "marked=" + Arrays.toString(marked) + ",\nprevious=" + Arrays.toString(previous) +",\ndistance=" + Arrays.toString(distance) ;
    }

    @Override
    public String toString() {
        return dijkstraList.toString();
    }
}
