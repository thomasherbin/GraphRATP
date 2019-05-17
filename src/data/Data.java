package data;

import metroGraph.DirectEdge;
import metroGraph.Station;

import java.util.ArrayList;

public class Data {
    private ArrayList<DirectEdge> data;

    public Data() {
        data = new ArrayList<>();
    }

    public void addEdge(Station A, Station B , String metroLineNum) {
        data.add(new DirectEdge(A, B, metroLineNum));
    }

    public ArrayList<DirectEdge> getData() {
        return data;
    }
}