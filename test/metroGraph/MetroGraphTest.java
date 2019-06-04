package metroGraph;

import org.junit.jupiter.api.Test;
import sp.Dijkstra;

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

}