package graphOperations;

import metroGraph.DirectEdge;
import metroGraph.MetroGraph;
import metroGraph.Station;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class SPHelper {

    private boolean[] marked;
    private int[] previous;
    private String[] previousName;
    private int[] distance;

    private MetroGraph copyMg;

    private ArrayList<Integer> BFSList;

    public SPHelper(MetroGraph mg) {
        this.marked = new boolean[mg.getStationNumber()];
        this.previous = new int[mg.getStationNumber()];
        this.previousName = new String[mg.getStationNumber()];
        this.distance = new int[mg.getStationNumber()];

        this.copyMg = mg;
        System.out.println("Size copymg : " + copyMg.getMetroGraph().size());
        System.out.println("Order copymg : " + copyMg.getStationNumber());

        this.BFSList = new ArrayList<>();
    }


    /**
     *
     * BFS algorithm
     *
     */
    public void BFS(int start) {
        ArrayList<Integer> queue = new ArrayList<>();

        marked[start] = true;
        distance[start] = 0;
        previous[start] = start;
        previousName[start] = copyMg.getMetroGraph().get(start).get(start).getA().getName();

        queue.add(start);

        while (!queue.isEmpty()) {
            int currentNode = queue.get(0);
            queue.remove(queue.get(0));

            BFSList.add(currentNode);

            Iterator<DirectEdge> i = copyMg.getMetroGraph().get(currentNode).listIterator();

            while (i.hasNext()) {
                DirectEdge neighboringStations = i.next();

                Station neighbor = neighboringStations.getB();
                int neighborID = neighbor.getStationPosition();
                String neighborName = neighbor.getName();

                if (!marked[neighborID]) {
                    marked[neighborID] = true;
                    previous[neighborID] = currentNode;
                    previousName[neighborID] = neighborName;
                    distance[neighborID] = distance[previous[neighborID]] + 1;

                    queue.add(neighborID);
                }

            }
        }

        for(int i=0; i<copyMg.getStationNumber(); i++) {
            if (previous[i] == -1 && !marked[i]) {
                distance[i] = -1;
            }
        }

//        System.out.println("BFS : " + Arrays.toString(BFSList.toArray()));
//        System.out.println("Visited/Marked for each node starting from vertex " + start + " : " + Arrays.toString(marked));
//        System.out.println("Distance for each node from vertex " + start + " : " + Arrays.toString(distance));
//        System.out.println("Previous node for each node starting from vertex " + start + " : " + Arrays.toString(previous));

        for(int i=0; i<copyMg.getStationNumber(); i++) {
            System.out.println("Is there a path from " + previousName[start] + " to " + previousName[i] + " ? " + hasPathTo(i) );
            System.out.println("Path from " + previousName[start] + " to " + previousName[i] + " is : " + printSP(i) + " (Distance = " + distTo(i) + ")" );
        }
    }

    public void BFS(int start, int finish) {
        ArrayList<Integer> queue = new ArrayList<>();

        marked[start] = true;
        distance[start] = 0;
        previous[start] = 0;
        previousName[start] = copyMg.getMetroGraph().get(start).get(start).getA().getName();
        queue.add(start);

        while (!queue.isEmpty()) {
            int currentNode = queue.get(0);
            queue.remove(queue.get(0));

            BFSList.add(currentNode);

            Iterator<DirectEdge> i = copyMg.getMetroGraph().get(currentNode).listIterator();

            while (i.hasNext()) {
                DirectEdge neighboringStations = i.next();

                Station neighbor = neighboringStations.getB();
                int neighborID = neighbor.getStationPosition();
                String neighborName = neighbor.getName();

                if (!marked[neighborID]) {
                    marked[neighborID] = true;
                    previous[neighborID] = currentNode;
                    previousName[neighborID] = neighborName;
                    distance[neighborID] = distance[previous[neighborID]] + 1;

                    queue.add(neighborID);
                }

            }
        }

        for(int i=0; i<copyMg.getStationNumber(); i++) {
            if (previous[i] == -1 && !marked[i]) {
                distance[i] = -1;
            }
        }

//        System.out.println("BFS : " + Arrays.toString(BFSList.toArray()));
//        System.out.println("Visited/Marked for each node starting from vertex " + start + " : " + Arrays.toString(marked));
//        System.out.println("Distance for each node from vertex " + start + " : " + Arrays.toString(distance));
//        System.out.println("Previous node for each node starting from vertex " + start + " : " + Arrays.toString(previous));

        System.out.println("Is there a path from " + previousName[start] + " to " + previousName[finish] + " ? " + hasPathTo(finish) );
        System.out.println("Path from " + previousName[start] + " to " + previousName[finish] + " is :\n" + printSP(finish) + "\nDistance : " + distTo(finish));
    }

    public boolean hasPathTo(int v) {
        return this.marked[v];
    }

    public int distTo(int v) {
        return this.distance[v];
    }



    /**
     * printSP method
     *
     * We rebuild the path thanks to the 'previous' array, which is itself built based
     * on the shortest distance. Once we have the list, we reverse it to have the actual shortest path
     *
     */
    public String printSP(int v) {
        ArrayList<String> shortpath = new ArrayList<>();
        shortpath.add(previousName[v]);

        int prevNode = previous[v];

        for (int i = 1; i <= distTo(v); i++) {
            if (prevNode == -1) {
                break;
            }
            shortpath.add(previousName[prevNode]);
            prevNode = previous[prevNode];
        }
        Collections.reverse(shortpath);

        return Arrays.toString(shortpath.toArray());
    }

}
