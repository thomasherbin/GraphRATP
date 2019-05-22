package graphOperations;

import metroGraph.MetroGraph;
import metroGraph.Station;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class SPHelper {

    private boolean[] marked;
    private Station[] previous;
    private int[] distance;

    public SPHelper(MetroGraph dg) {
        marked = new boolean[dg.getOrder()];
        previous = new Station[dg.getOrder()];
        distance = new int[dg.getOrder()];
    }


    /**
     *
     * BFS algorithm
     *
     */
    public void BFS(MetroGraph mg, int start) {
        ArrayList<Integer> queue = new ArrayList<>();

        marked[start-1] = true;
        distance[start-1] = 0;
        previous[start-1] = null;

        queue.add(start);

        while (!queue.isEmpty()) {
            int currentNode = queue.get(0);
            queue.remove(queue.get(0));

            mg.getBFSResult().add(currentNode);

            Iterator<Integer> i = mg.getNeighbors().get(currentNode-1).listIterator();

            while (i.hasNext()) {
                int neighbor = i.next();

                if (!marked[neighbor-1]) {
                    marked[neighbor-1] = true;
                    previous[neighbor-1] = currentNode;
                    distance[neighbor-1] = distance[previous[neighbor-1] - 1] + 1;

                    queue.add(neighbor);
                }

            }
        }

        for(int i=0; i<mg.getOrder(); i++) {
            if (previous[i] == 0 && !marked[i]) {
                distance[i] = -1;
            }
        }

        System.out.println("BFS : " + Arrays.toString(mg.getBFSResult().toArray()));
        System.out.println("Visited/Marked for each node starting from vertex " + start + " : " + Arrays.toString(marked));
        System.out.println("Distance for each node from vertex " + start + " : " + Arrays.toString(distance));
        System.out.println("Previous node for each node starting from vertex " + start + " : " + Arrays.toString(previous));

        for(int i=1; i<=mg.getOrder(); i++) {
            System.out.println("Is there a path from " + start + " to " + i + " ? " + hasPathTo(i) );
            System.out.println("Path from " + start + " to " + i + " is : " + printSP(i) + " (Distance = " + distTo(i) + ")" );
        }
    }


    public boolean hasPathTo(int v) {
        return this.marked[v-1];
    }

    public int distTo(int v) {
        return this.distance[v-1];
    }



    /**
     * printSP method
     *
     * We rebuild the path thanks to the previous array, which is itself built based
     * on the shortest distance. Once we have the list, we reverse it to have the actual shortest path
     *
     */
    public String printSP(int v) {
        ArrayList<Integer> shortpath = new ArrayList<>();
        shortpath.add(v);

        int prevNode = previous[v-1];

        for(int i=1; i <= distTo(v); i++) {
            if (prevNode < 0) {
                break;
            }
            shortpath.add(prevNode);
            prevNode = previous[prevNode-1];
        }
        Collections.reverse(shortpath);

        //if there is no path we remove the default entry in the list (default entry = destination node)
        if(distTo(v) == -1) {
            shortpath.clear();
        }

        return Arrays.toString(shortpath.toArray());

}
