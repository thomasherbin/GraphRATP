import data.Data;
import data.DataBuilder;
import metroGraph.MetroGraph;
import metroGraph.Station;
import org.junit.jupiter.api.Test;


class MetroGraphTest {
    @Test
    void printGraph() throws Exception{
        DataBuilder data = new DataBuilder();
        MetroGraph metroGraph = new MetroGraph(data.getData());
        System.out.println(metroGraph.toString());
        System.out.println("Order : " + metroGraph.getOrder() + " | Size : " + metroGraph.getSize());
    }

    @Test
    void graph() throws Exception {
        DataBuilder data = new DataBuilder();
        MetroGraph metroGraph = new MetroGraph(data.getData());
    }



}