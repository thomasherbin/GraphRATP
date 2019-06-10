package metroGraph;

import graphOperations.BFS;
import graphOperations.Clustering;
import org.junit.jupiter.api.Test;
import graphOperations.Dijkstra;

import java.util.ArrayList;

class MetroGraphTest {
    @Test
    void printGraph() throws Exception{
        MetroGraph metroGraph = new MetroGraph();
        System.out.println(metroGraph.toString());
    }

    @Test
    void graph() throws Exception {
        MetroGraph metroGraph = new MetroGraph();
    }

    @Test
    void dijkstra() throws Exception {
        Dijkstra dijkstra = new Dijkstra(0, 50);
        //System.out.println(dijkstra.printFullSP());
        System.out.println(dijkstra.printShortSP());
    }

    @Test
    void bfs() throws Exception {
        BFS bfs = new BFS(0, 302);
        System.out.println(bfs.toString());
        System.out.println(bfs.printShortSP());
    }

    @Test
    void findDiameter() throws Exception {
        double diameter = 0;
        String path = "";
        MetroGraph metroGraph = new MetroGraph();

        for(int i=0; i<metroGraph.getStationNumber(); i++) {
            Dijkstra dijkstra = new Dijkstra(metroGraph, i);

            if(dijkstra.getMaxDistance() > diameter) {
                diameter = dijkstra.getMaxDistance();
                path = dijkstra.getMaxDistancedStations();
            }
        }

        System.out.println("Diameter : " + diameter);
        System.out.println(path);
    }


    @Test
    void clustering() throws Exception {
        MetroGraph metroGraph = new MetroGraph();
        Clustering clustering = new Clustering(metroGraph);
        System.out.println(metroGraph.printByMetroLineNum());
        //clustering.removeHighBetweenessEdges(2000);
        //System.out.println(metroGraph.toString());
    }



    @Test
    void printGraph2() throws Exception{
        MetroGraph metroGraph = new MetroGraph();

        System.out.println("Order : " + metroGraph.getStationNumber() + " | Size : " + metroGraph.getDirectEdgeNumber());
        System.out.println("DirectEdges : " + metroGraph.getMetroGraph().size());
        System.out.println();

        System.out.println(metroGraph.toString());
    }

    @Test
    void printStationAndIDs() throws Exception {
        MetroGraph metroGraph = new MetroGraph();

        ArrayList<ArrayList<DirectEdge>> mg = metroGraph.getMetroGraph();

        for (ArrayList<DirectEdge> stations : mg) {
            for (DirectEdge directEdge : stations) {
                System.out.println(directEdge.getA().toString() + " (ID : " + directEdge.getA().getStationPosition() + ")" + " (RATP_ID : " + directEdge.getA().getId() + ")");
                System.out.println(directEdge.getB().toString() + " (ID : " + directEdge.getB().getStationPosition() + ")" + " (RATP_ID : " + directEdge.getB().getId() + ")");
                System.out.println();
            }
        }

    }




}