package metroGraph;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Station {
    private int id;
    private static int orderID = 0;

    private String name;
    private double lat;
    private double lon;


    public Station(int id,JSONObject stations) {
        this.id = id;
        JSONObject st = (JSONObject) stations.get(Integer.toString(id));
        this.name = (String) st.get("nom");
        this.lat = Double.parseDouble((String) st.get("lat"));
        this.lon = Double.parseDouble((String) st.get("lng"));

        this.orderID++;
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



    public void setId(int id) {
        this.id = id;
    }


    public static int getOrderID() {
        return orderID;
    }

    public static void decrementOrderID() {
        orderID--;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return id == station.id &&
                Double.compare(station.lat, lat) == 0 &&
                Double.compare(station.lon, lon) == 0 &&
                Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lat, lon);
    }
}
