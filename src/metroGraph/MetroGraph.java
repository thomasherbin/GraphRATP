package metroGraph;

import data.Data;

import java.util.ArrayList;


public class MetroGraph {
    private ArrayList<ArrayList<DirectEdge>> metroGraph;
    private int graphOrder;
    private int graphSize;

    public MetroGraph(Data data) {
        this.metroGraph = new ArrayList<>();
        this.graphOrder = 0;
        this.graphSize = 0;

        for (DirectEdge directEdge : data.getData()) {

            //Get nodes
            Station A =  directEdge.getA();
            Station B =  directEdge.getB();
            String metroLineNum = directEdge.getMetroLine();

            addDirectEdge(A, B, metroLineNum);
            addDirectEdge(B, A, metroLineNum);

        }
    }

    public void addDirectEdge(Station A, Station B, String metroLineNum) {
        //compute order based on the highest station id
        this.graphOrder = Station.getOrder() > this.graphOrder ? Station.getOrder() : this.graphOrder;

        boolean containsVertex = false;
        for (ArrayList<DirectEdge> directedEdges : metroGraph) {
            if (directedEdges.get(0).getA().equals(A)) {
                containsVertex = true;
                directedEdges.add(new DirectEdge(A, B,metroLineNum));
            }
        }
        if (!containsVertex) {
            metroGraph.add(new ArrayList<>());
            metroGraph.get(metroGraph.size() - 1).add(new DirectEdge(A, B, metroLineNum));
        }

        graphSize++;
    }


    public ArrayList<ArrayList<DirectEdge>> getMetroGraph() {
        return metroGraph;
    }

    @Override
    public String toString() {
        String str = "";
        for (ArrayList<DirectEdge> stations : metroGraph) {
            for (DirectEdge directEdge : stations) {
                str += directEdge.toString() + ",";
            }
            str += "\n";
        }
        return str;
    }


    public int getOrder() {
        return graphOrder;
    }

    public int getSize() {
        return graphSize;
    }
}
