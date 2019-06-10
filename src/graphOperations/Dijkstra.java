package graphOperations;

import metroGraph.MetroGraph;
import metroGraph.Station;

import java.util.ArrayList;

public class Dijkstra extends SP {
    private ArrayList<Station> dijkstraList;

    private Station stationAmax;
    private Station stationBmax;

    public Dijkstra() throws Exception {
        super();
        this.dijkstraList = new ArrayList<>();
        dijkstraAlgorithm();
    }

    public Dijkstra(int startNodePos) throws Exception {
        super(startNodePos);
        this.dijkstraList = new ArrayList<>();
        dijkstraAlgorithm();
    }

    public Dijkstra(int startNodePos, int endNodePos) throws Exception {
        super(startNodePos, endNodePos);
        this.dijkstraList = new ArrayList<>();
        dijkstraAlgorithm();
    }

    public Dijkstra(MetroGraph G, int startNodePos)  {
        super(startNodePos, G);
        this.dijkstraList = new ArrayList<>();
        dijkstraAlgorithm();
    }



    private void dijkstraAlgorithm() {
        //Initialisation
        Station node = startNode;
        int nodePos = startNodePos;
        dijkstraList.add(node);
        for (int i = 0; i < distance.length; i++) { distance[i]= Double.POSITIVE_INFINITY; }
        distance[nodePos] = 0;

        while (!allMarked() && distance[nodePos] != Double.POSITIVE_INFINITY) {
            for (Station neighbor : G.neighbors(node)) {
                int neighborPos = neighbor.getStationPosition();
                if (!marked[neighborPos]) {
                    if (distance[neighborPos] > distance[nodePos] + G.getWeightDirectEdge(node, neighbor)) {
                        distance[neighborPos] = distance[nodePos] + G.getWeightDirectEdge(node, neighbor);
                        previous[neighborPos] = node;

                        //testing if this distance is greater than the current maxDistance
                        if (distance[neighborPos] > maxDistance) {
                            maxDistance = distance[neighborPos];
                            stationAmax = dijkstraList.get(0);
                            stationBmax = neighbor;
                        }
                    }
                }
            }

            marked[nodePos] = true;
            node = nextNode();
            nodePos = node.getStationPosition();
            dijkstraList.add(node);
        }
        dijkstraList.remove(dijkstraList.size() - 1);

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
        return G.getStation(nodePos);
    }



    public String getMaxDistancedStations() {
        double lon1 = stationAmax.getLon();
        double lat1 = stationAmax.getLat();
        double lon2 = stationBmax.getLon();
        double lat2 = stationBmax.getLat();

        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515 * 1.609344;

        return stationAmax.getName() + " - " + stationBmax.getName() + "\nDistance (in kilometers) : " + dist;
    }


    @Override
    public String toString() {
        return dijkstraList.toString();
    }
}
