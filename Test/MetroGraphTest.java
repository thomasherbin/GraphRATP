import data.Data;
import data.DataBuilder;
import metroGraph.Dijkstra;
import metroGraph.MetroGraph;
import metroGraph.Station;
import org.junit.jupiter.api.Test;


class MetroGraphTest {
    @Test
    void printGraph() throws Exception{
        DataBuilder data = new DataBuilder();
        MetroGraph metroGraph = new MetroGraph(data.getData());
        System.out.println(metroGraph.toString());
    }

    @Test
    void graph() throws Exception {
        DataBuilder data = new DataBuilder();
        MetroGraph metroGraph = new MetroGraph(data.getData());
    }

    @Test
    void dijkstra() throws Exception {
        DataBuilder data = new DataBuilder();
        MetroGraph metroGraph = new MetroGraph(data.getData());
        Dijkstra dijkstra = new Dijkstra(metroGraph, metroGraph.getMetroGraph().get(0).get(0).getA());
    }
}