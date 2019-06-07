package graphOperations;

import metroGraph.Station;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends SP {
    private ArrayList<Station> bfsList;

    public BFS() throws Exception {
        super();
        this.bfsList = new ArrayList<>();
        bfsAlgorithm();
    }

    public BFS(int startNodePos) throws Exception {
        super(startNodePos);
        this.bfsList = new ArrayList<>();
        bfsAlgorithm();
    }

    public BFS(int startNodePos, int endNodePos) throws Exception {
        super(startNodePos, endNodePos);
        this.bfsList = new ArrayList<>();
        bfsAlgorithm();
    }


    private void bfsAlgorithm() {

        Queue<Station> queue = new LinkedList<>();
        Station node = startNode;
        int nodePos = startNodePos;

        marked[nodePos] = true;
        distance[nodePos] = 0;
        previous[nodePos] = null;
        queue.add(node);


        while (!queue.isEmpty()) {
            node = queue.element();
            queue.remove();
            nodePos = node.getStationPosition();
            bfsList.add(node);


            for (Station neighbor : neighbors(node)) {
                int neighborPos = neighbor.getStationPosition();
                if (!marked[neighborPos]) {
                    marked[neighborPos] = true;
                    previous[neighborPos] = node;
                    distance[neighborPos] = distance[nodePos] + 1;
                    queue.add(neighbor);
                }
            }
        }


    }

    @Override
    public String toString() {
        return bfsList.toString();
    }
}
