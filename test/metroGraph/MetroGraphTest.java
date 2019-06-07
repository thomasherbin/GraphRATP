package metroGraph;

import graphOperations.BFS;
import graphOperations.SPHelper;
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
        BFS bfs = new BFS(0, 50);
        System.out.println(bfs.toString());
        System.out.println(bfs.printShortSP());
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


    @Test
    void BFSShortestPathTest() throws Exception {
        MetroGraph metroGraph = new Dijkstra();

        SPHelper sp = new SPHelper(metroGraph);
//        sp.BFS(122);
        sp.BFS(0, 168);
    }

}