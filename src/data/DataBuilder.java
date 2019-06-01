package data;

import com.sun.org.apache.bcel.internal.generic.JsrInstruction;
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
                //System.out.println("metroLineNum : "+metroLineNum);
                //System.out.println("arrets size : "+arrets.size());
                if (metroLineNum.equals("10")) {
                    lineGraph(stations,(JSONArray) arrets.get(0), metroLineNum, false);
                    lineGraph(stations,arretsBuilderLigne10(arrets), metroLineNum, false);
                } else if (metroLineNum.equals("7B")) {
                    arrets = (JSONArray) arrets.get(0);
                    arrets = (JSONArray) arretsBuilderLigne7B(arrets);
                    lineGraph(stations,(JSONArray)arrets.get(0), metroLineNum, true);
                    lineGraph(stations,(JSONArray)arrets.get(1), metroLineNum, false);
                } else {
                    for (int i = 0; i < arrets.size(); i++) {
                        lineGraph(stations,(JSONArray) arrets.get(i), metroLineNum, true);
                    }
                }
            }
        }
    }

    private void lineGraph(JSONObject stations, JSONArray arrets, String metroLineNum,boolean multiDirection) {
        for (int i = 1; i < arrets.size(); i++) {
            int aStationId = Integer.parseInt(arrets.get(i - 1).toString());
            int bStationId = Integer.parseInt(arrets.get(i).toString());
            Station A = new Station(aStationId, stations);
            Station B = new Station(bStationId, stations);
            data.addEdge(A,B, metroLineNum);
            if (multiDirection) {
                data.addEdge(B,A, metroLineNum);
            }
        }
    }

    private JSONArray arretsBuilderLigne7B(JSONArray arrets) {
        JSONArray arretsBiDirectionel = new JSONArray();
        JSONArray arretsUniDirectionel = new JSONArray();
        boolean inUni = false;
        //System.out.println(arrets.toString());
        for (int i = 0; i < arrets.size(); i++) {
            int stationId = Integer.parseInt(arrets.get(i).toString());
            if (inUni) {
                if (stationId == 2016) {
                    inUni = false;
                    arretsUniDirectionel.add((arrets.get(i)));
                } else {
                    arretsUniDirectionel.add((arrets.get(i)));
                }

            } else {
                if (stationId == 2016) {
                    inUni = true;
                    arretsBiDirectionel.add(arrets.get(i));
                    arretsUniDirectionel.add((arrets.get(i)));
                } else {
                    arretsBiDirectionel.add(arrets.get(i));
                }
            }
        }
        arrets.clear();
        arrets.add(arretsBiDirectionel);
        arrets.add(arretsUniDirectionel);
        return arrets;
    }

    private JSONArray arretsBuilderLigne10(JSONArray arrets) {
        JSONArray buffer = new JSONArray();
        JSONArray firstDirection = (JSONArray) arrets.get(0);
        JSONArray secondDirection = (JSONArray) arrets.get(1);
        //System.out.println(firstDirection.toString());
        //System.out.println(secondDirection.toString());

        int k = secondDirection.size()-1;
        boolean inSecondDirection = false;
        for (int i = firstDirection.size()-1; i >= 0; i--) {
            int aStationId = Integer.parseInt(firstDirection.get(i).toString());
            int bStationId = Integer.parseInt(secondDirection.get(k).toString());
            //System.out.println("buffer : "+buffer.toString());
            //System.out.println("a : "+aStationId+ " i : "+i);
            //System.out.println("b : "+bStationId+ " k : "+ k);
            if (inSecondDirection) {
                if (aStationId == bStationId) {
                    inSecondDirection = false;
                    buffer.add(firstDirection.get(i));
                } else {
                    buffer.add(secondDirection.get(k));
                    k--;
                }
            } else {
                if (aStationId == bStationId) {
                    inSecondDirection = true;
                    buffer.add(firstDirection.get(i));
                    k--;
                } else {
                    buffer.add(firstDirection.get(i));
                }
            }
        }
        secondDirection = buffer;
        return secondDirection;
    }

    public Data getData() {
        return data;
    }
}
