import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


public class MetroGraph {
    private ArrayList<ArrayList<DirectEdge>> metroGraph;
    private final String DATA_PATH = "C:\\Users\\chewi\\Documents\\Ecole\\Isep\\a2\\[II.2315] Algorithmique et programmation avancée\\GraphRATP\\data";

    public MetroGraph() throws Exception {
        this.metroGraph = new ArrayList<>();
        File metroDataPath = new File(DATA_PATH+ "\\reseau.json");
        JSONObject reseau = (JSONObject) new JSONParser().parse(new FileReader(metroDataPath));
        JSONObject stations = (JSONObject) reseau.get("stations");
        Iterator<Map.Entry> lignes = ((Map)reseau.get("lignes")).entrySet().iterator();
        while (lignes.hasNext()) {
            JSONObject ligne = (JSONObject) lignes.next().getValue();
            if (ligne.get("type").equals("metro")) {
                String metroLineNum = (String) ligne.get("num");
                JSONArray arrets = (JSONArray) ligne.get("arrets");
                arrets = (JSONArray) arrets.get(0);
                lineGraph(stations, arrets, metroLineNum);
            }
        }

    }

    private void lineGraph(JSONObject stations, JSONArray arrets, String metroLineNum) {
        for (int i = 1; i < arrets.size(); i++) {
            int aStationId = Integer.parseInt(arrets.get(i - 1).toString());
            int bStationId = Integer.parseInt(arrets.get(i).toString());
            Station A = new Station(aStationId, stations, this.metroGraph);
            Station B = new Station(bStationId, stations, this.metroGraph);
            this.addDirectEdge(new DirectEdge(A, B, metroLineNum));
            this.addDirectEdge(new DirectEdge(B, A, metroLineNum));
        }
    }

    private void addDirectEdge(DirectEdge directEdge) {
        if (!directEdge.getA().isInGraph()) {
            directEdge.getA().setInGraph(true);
            metroGraph.add(new ArrayList<>());
            metroGraph.get(metroGraph.size() - 1).add(directEdge);
        } else {
            for (ArrayList<DirectEdge> stations : metroGraph) {
                if (stations.get(0).getA().getId() == directEdge.getA().getId()) {
                    stations.add(directEdge);
                    break;
                }
            }
        }
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
}
