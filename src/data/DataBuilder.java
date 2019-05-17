package data;

import metroGraph.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class DataBuilder {
    private final String DATA_PATH = "D:\\chewi\\Documents\\Ecole\\Isep\\a2\\[II.2315] Algorithmique et programmation avancée\\GraphRATP\\data";
    private Data data;

    public DataBuilder() throws Exception{
        this.data = new Data();
        File metroDataPath = new File(DATA_PATH+ "\\reseau.json");
        JSONObject reseau = (JSONObject) new JSONParser().parse(new FileReader(metroDataPath));
        JSONObject stations = (JSONObject) reseau.get("stations");
        Iterator<Map.Entry> lignes = ((Map)reseau.get("lignes")).entrySet().iterator();
        while(lignes.hasNext()) {
            JSONObject ligne = (JSONObject) lignes.next().getValue();
            if (ligne.get("type").equals("metro")) {
                JSONArray arrets = (JSONArray) ligne.get("arrets");
                String metroLineNum = (String) ligne.get("num");
                arrets = (JSONArray) arrets.get(0);
                lineGraph(stations, arrets, metroLineNum);
            }
        }
    }

    private void lineGraph(JSONObject stations, JSONArray arrets, String metroLineNum) {
        for (int i = 1; i < arrets.size(); i++) {
            int aStationId = Integer.parseInt(arrets.get(i - 1).toString());
            int bStationId = Integer.parseInt(arrets.get(i).toString());
            Station A = new Station(aStationId, stations);
            Station B = new Station(bStationId, stations);
            data.addEdge(A,B, metroLineNum);
        }
    }

    public Data getData() {
        return data;
    }
}
