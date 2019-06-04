package metroGraph;

import graphOperations.SPHelper;
import org.junit.jupiter.api.Test;
import sp.Dijkstra;

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
        MetroGraph metroGraph = new MetroGraph();
        Dijkstra dijkstra = new Dijkstra(metroGraph, metroGraph.getStation(0), metroGraph.getStation(50));
        //System.out.println(dijkstra.printFullSP());
        System.out.println(dijkstra.printShortSP());
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
                System.out.println(directEdge.getA().toString() + " (ID : " + directEdge.getA().getStationOrder() + ")" + " (RATP_ID : " + directEdge.getA().getId() + ")");
                System.out.println(directEdge.getB().toString() + " (ID : " + directEdge.getB().getStationOrder() + ")" + " (RATP_ID : " + directEdge.getB().getId() + ")");
                System.out.println();
            }
        }

    }


    @Test
    void BFSShortestPathTest() throws Exception {
        MetroGraph metroGraph = new MetroGraph();

        SPHelper sp = new SPHelper(metroGraph);
        sp.BFS(122);
    }

}