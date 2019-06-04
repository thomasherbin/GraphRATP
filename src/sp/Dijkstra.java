package sp;

import metroGraph.DirectEdge;
import metroGraph.MetroGraph;
import metroGraph.Station;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra {
    private MetroGraph metroGraph;
    private boolean[] marked;
    private Station[] previous;
    private double[] distance;
    private Station startNode;
    private Station endNode;
    private ArrayList<Station> dijkstraList;




    public Dijkstra(MetroGraph G, Station startNode, Station endNode) {
        this.metroGraph = G;
        this.startNode = startNode;
        this.endNode = endNode;
        this.dijkstraList = new ArrayList<>();

        //Initialisation
        Station node = startNode;
        int nodePos = G.getPosition(node);
        this.marked = new boolean[G.getStationNumber()];
        this.previous = new Station[G.getStationNumber()];
        this.distance = new double[G.getStationNumber()];
        dijkstraList.add(node);
        for (int i = 0; i < distance.length; i++) { distance[i]= Double.POSITIVE_INFINITY; }
        distance[nodePos] = 0;
        //System.out.println("Start Node : "+node.toString());
        //System.out.println("Start Node Position : "+nodePos);
        //System.out.println(this.printArrays());


        while (!allMarked() && node != null) {
            //System.out.println("Dijkstra list : "+this.toString());
            //System.out.println("Neighbors of current Node : "+G.neighbors(node).toString());

            for (Station neighbor : G.neighbors(node)) {
                int neighborPos = G.getPosition(neighbor);
                //System.out.println("Neighbor position : "+neighborPos);
                if (!marked[neighborPos]) {
                    if (distance[neighborPos] > distance[nodePos] + G.getWeightDirectEdge(node, neighbor)) {
                        distance[neighborPos] = distance[nodePos] + G.getWeightDirectEdge(node, neighbor);
                        previous[neighborPos] = node;
                        //System.out.println(this.distance[nodePos]+ " + "+G.getWeightDirectEdge(node,neighbor) + " "+ this.distance[neighborPos]);
                        //System.out.println("Distance chosen : "+this.distance[neighborPos]);
                    }
                }
            }

            marked[nodePos] = true;
            node = nextNode();
            nodePos = G.getPosition(node);
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
            return metroGraph.getStation(nodePos);
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
        return distance[metroGraph.getPosition(endNode)];
    }

    private ArrayList<Station> SP() {
        int vPos = metroGraph.getPosition(endNode);
        ArrayList<Station> SP = new ArrayList<>();
        if (this.hasPathTo(vPos)) {
            Station i = this.previous[vPos];
            int iPos = metroGraph.getPosition(i);
            ArrayList<Station> SPr = new ArrayList<>();
            SPr.add(endNode);
            SPr.add(i);
            while (iPos != metroGraph.getPosition(startNode)) {
                i = this.previous[iPos];
                iPos = metroGraph.getPosition(this.previous[iPos]);
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

    private ArrayList<DirectEdge> directEdgeSP() {
        ArrayList<Station> SP = SP();
        ArrayList<DirectEdge> directEdgeSP = new ArrayList<>();
        if (!SP.isEmpty()) {
            for (int i = 1; i < SP.size(); i++) {
                Station A = SP.get(i - 1);
                Station B = SP.get(i);
                directEdgeSP.add(metroGraph.getDirectEdge(A, B));
            }
        }
        return directEdgeSP;
    }

    public String printFullSP() {
        String str = "";
        ArrayList<Station> SP = SP();
        if (!SP.isEmpty()) {
            str += "Shortest path from "+startNode.getName()+" to "+endNode.getName()+" :";
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
            str += "Shortest path from "+startNode.getName()+" to "+endNode.getName()+" :\n";
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

    private String printArrays() {
        return "marked=" + Arrays.toString(marked) + ",\nprevious=" + Arrays.toString(previous) +",\ndistance=" + Arrays.toString(distance) ;
    }

    @Override
    public String toString() {
        return dijkstraList.toString();
    }
}
