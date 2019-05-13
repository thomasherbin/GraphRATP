package metroGraph;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Station {
    private int id;
    private String name;
    private double lat;
    private double lon;
    private boolean isInGraph;

    public Station(int id) {
        this.id = id;
        this.isInGraph = false;
    }

    public Station(int id, JSONObject stations, ArrayList<ArrayList<DirectEdge>> metroGraph) {
        this.isInGraph = false;
        this.id = id;

        for (ArrayList<DirectEdge> sts : metroGraph) {
            Station station = sts.get(0).getA();
            if (station.id == id) {
                this.name = station.name;
                this.lat = station.lat;
                this.lon = station.lon;
                this.isInGraph = true;
                break;
            }
        }
        if (!this.isInGraph) {


            JSONObject st = (JSONObject) stations.get(Integer.toString(id));
            this.name = (String) st.get("nom");
            this.lat = Double.parseDouble((String) st.get("lat"));
            this.lon = Double.parseDouble((String) st.get("lng"));
        }
    }

    public Station() {
        this.isInGraph = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public boolean isInGraph() {
        return isInGraph;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setInGraph(boolean inGraph) {
        isInGraph = inGraph;
    }

    @Override
    public String toString() {
        return name;
    }
}