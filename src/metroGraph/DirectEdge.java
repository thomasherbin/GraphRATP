package metroGraph;

public class DirectEdge {
    private Station A;
    private Station B;
    private String metroLine;
    private double weight;

    public DirectEdge(Station a, Station b, String metroLine) {
        A = a;
        B = b;
        this.metroLine = metroLine;
        this.weight = Math.abs((a.getLon() - b.getLon()) / (a.getLat() - b.getLat()));
    }

    public double getWeight() {
        return weight;
    }

    public Station getA() {
        return A;
    }

    public Station getB() {
        return B;
    }

    public String getMetroLine() {
        return metroLine;
    }

    @Override
    public String toString() {
        return "{" + A.toString() + " (ID : " + A.getStationOrder() + "), " + B.toString() + " (ID : " + B.getStationOrder() + ")}";
    }
}
