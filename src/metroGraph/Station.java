package metroGraph;

import org.json.simple.JSONObject;

import java.util.Objects;

public class Station {
    private int id;

    private int stationOrder;
    private static int order = 0;

    private String name;
    private static ArrayList<String> allStationsNames = new ArrayList<>();

    private double lat;
    private double lon;


    public Station(int id,JSONObject stations) {
        this.id = id;
        JSONObject st = (JSONObject) stations.get(Integer.toString(id));
        this.name = (String) st.get("nom");

        this.lat = Double.parseDouble((String) st.get("lat"));
        this.lon = Double.parseDouble((String) st.get("lng"));

        if (!allStationsNames.contains(this.name)) {
            allStationsNames.add(this.name);
            this.stationOrder = this.order;
            this.order++;
        } else {
            this.stationOrder = allStationsNames.indexOf(this.name);
        }
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


    public static int getOrder() {
        return order;
    }

    public static void decrementOrderID() {
        order--;
    }

    public int getStationOrder() {
        return stationOrder;
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
