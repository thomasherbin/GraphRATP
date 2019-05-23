package metroGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra {
    private MetroGraph metroGraph;
    private boolean[] marked;
    private Station[] previous;
    private double[] distance;



    public Dijkstra(MetroGraph G, Station startNode) {
        this.metroGraph = G;
        this.marked = new boolean[G.getStationNumber()];
        this.previous = new Station[G.getStationNumber()];
        this.distance = new double[G.getStationNumber()];

        Station node = startNode;
        int nodePos = G.getPosition(node);
        //System.out.println("Start Node : "+node.toString());
        //System.out.println("Start Node Position : "+nodePos);
        boolean isNegativeWeight = verifyNonNegative();
        ArrayList<Station> L = new ArrayList<>();
        L.add(node);
        this.marked[nodePos]= true;
        for (int i = 0; i < this.distance.length; i++) {
            this.distance[i]= Double.POSITIVE_INFINITY;
        }
        this.distance[nodePos] = 0;

        //System.out.println(this.toString());
        int i = 0;
        while (!allMarked()) {
            //System.out.println("Dijkstra list : "+L.toString());
            //System.out.println("Neighbors of current Node : "+G.neighbors(node).toString());

            for (Station neighbor : G.neighbors(node)) {
                int neighborPos = G.getPosition(neighbor);
                //System.out.println("Neighbor position : "+neighborPos);
                if (isNegativeWeight){
                    if (!this.marked[neighborPos]) {
                        //System.out.println(this.distance[nodePos]+ " + "+G.getWeightDirectEdge(node,neighbor) + " "+ this.distance[neighborPos]);
                        this.distance[neighborPos]= Math.min(this.distance[neighborPos], this.distance[nodePos]+G.getWeightDirectEdge(node,neighbor));
                        //System.out.println("Distance chosen : "+this.distance[neighborPos]);
                        this.previous[neighborPos] = node;
                    }
                } else {
                    //System.out.println(this.distance[node-1]+ " + "+G.getWeightInGraph(node-1,G.Neighbors(node).get(i)) + " "+ this.distance[G.Neighbors(node).get(i)-1]);
                    this.distance[neighborPos]= Math.min(this.distance[neighborPos], this.distance[nodePos]+G.getWeightDirectEdge(node,neighbor));
                    this.previous[neighborPos] = node;
                }
            }

            this.marked[nodePos] = true;
            //System.out.println(this.toString());
            node = nextNode();
            nodePos = G.getPosition(node);
            //System.out.println("Current Node : "+node);
            //System.out.println("Current Node Position : "+nodePos);
            L.add(node);
            i++;
        }
        L.remove(L.size() - 1);
        //System.out.println(this.toString());
        //System.out.println(L.toString());
        printSP(metroGraph.getStation(L.size() - 1), startNode);
    }


    public boolean verifyNonNegative() {
        for(ArrayList<DirectEdge> stations : metroGraph.getMetroGraph()) {
            for(DirectEdge directEdge : stations){
                if (directEdge.getWeight() < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean allMarked() {
        for (int i = 0; i < this.marked.length; i++) {
            if (!marked[i]) {
                return false;
            }
        }
        return true;
    }

    public Station nextNode() {
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
        return metroGraph.getStation(nodePos);
    }

    public boolean hasPathTo(int pos) {
        if (this.marked[pos]) {
            return true;
        } else {
            return false;
        }
    }

    public double distTo(Station v) {
        return distance[metroGraph.getPosition(v)];
    }

    public void printSP(Station v, Station s) {
        int vPos = metroGraph.getPosition(v);
        if (this.hasPathTo(vPos)) {
            Station i = this.previous[vPos];
            int iPos = metroGraph.getPosition(i);
            ArrayList<Station> SP = new ArrayList<>();
            SP.add(v);
            SP.add(i);
            while (iPos != 0) {
                i = this.previous[iPos];
                iPos = metroGraph.getPosition(this.previous[iPos]);
                if (iPos != 0) {
                    SP.add(i);
                }
            }
            System.out.print("shortest path from "+s.getName()+" to "+v.getName()+" :");
            for (int k = SP.size() - 1; k >= 0; k--) {
                System.out.print(" -> "+ SP.get(k));
            }
        } else {
            System.out.println("No link between this two nodes");
        }
    }

    @Override
    public String toString() {
        return
                "marked=" + Arrays.toString(marked) +
                        ",\nprevious=" + Arrays.toString(previous) +
                        ",\ndistance=" + Arrays.toString(distance) ;
    }
}
