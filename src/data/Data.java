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
        if (getDirectEdge(A, B) == null) {
            data.add(new DirectEdge (A, B, metroLineNum));
        }
    }

    public ArrayList<DirectEdge> getData() {
        return data;
    }

    public DirectEdge getDirectEdge(Station v, Station w) {
        for (DirectEdge directEdge : data) {
            if (directEdge.getB().getId() == w.getId() && directEdge.getA().getId() == v.getId()) {
                return directEdge;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "";
        String metroLine = data.get(0).getMetroLine();
        str += "Ligne : " + metroLine + "\n";
        for (int i = 1; i < data.size(); i++) {
            if (!data.get(i).getMetroLine().equals(metroLine)) {
                metroLine = data.get(i).getMetroLine();
                str += "Ligne : " + metroLine + "\n";
            }
            str += data.get(i).toString() + "\n";
        }

        return str;
    }
}