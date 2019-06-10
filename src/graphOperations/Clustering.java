package graphOperations;

import metroGraph.DirectEdge;
import metroGraph.MetroGraph;
import metroGraph.Station;

import java.util.ArrayList;

public class Clustering {
    private MetroGraph G;

    public Clustering(MetroGraph G) {
        this.G = G;

        for (int i = 0; i < G.getStationNumber(); i++) {
            BFS bfs = new BFS(G, i);
            for (int k = 0; k < G.getStationNumber(); k++) {
                if (i != k) {
                    ArrayList<Station> path = bfs.path(k);
                    if (!path.isEmpty()) {
                        for (int j = 1; j < path.size(); j++) {
                            Station A = path.get(j - 1);
                            Station B = path.get(j);

                            //DirectEdge directEdge = G.getData().getDirectEdge(A, B);
                            DirectEdge directEdge = G.getDirectEdge(A, B);
                            directEdge.addSpToSpCounter();
                        }
                    }
                }
            }
        }
    }

    public void removeHighBetweenessEdges(int limit) {
        for (ArrayList<DirectEdge> directEdges : G.metroGraph) {
            directEdges.removeIf(directEdge -> directEdge.getSpCounter() > limit);
            //directEdges.removeIf(directEdge -> directEdge.getSpCounter() < 300);
        }
        G.metroGraph.removeIf(ArrayList::isEmpty);
    }
}
