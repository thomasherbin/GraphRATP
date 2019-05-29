import data.Data;
import data.DataBuilder;
import graphOperations.SPHelper;
import metroGraph.DirectEdge;
import metroGraph.MetroGraph;
import metroGraph.Station;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class MetroGraphTest {
    @Test
    void printGraph() throws Exception{
        DataBuilder data = new DataBuilder();
        MetroGraph metroGraph = new MetroGraph(data.getData());

        System.out.println("Order : " + metroGraph.getOrder() + " | Size : " + metroGraph.getSize());
        System.out.println("DirectEdges : " + metroGraph.getMetroGraph().size());
        System.out.println();

        System.out.println(metroGraph.toString());
    }

    @Test
    void printStationAndIDs() throws Exception {
        DataBuilder data = new DataBuilder();
        MetroGraph metroGraph = new MetroGraph(data.getData());

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
    void graph() throws Exception {
        DataBuilder data = new DataBuilder();
        MetroGraph metroGraph = new MetroGraph(data.getData());
    }

    @Test
    void BFSShortestPathTest() throws Exception {
        DataBuilder data = new DataBuilder();
        MetroGraph metroGraph = new MetroGraph(data.getData());

        SPHelper sp = new SPHelper(metroGraph);
        sp.BFS(122);
    }
}