package metroGraph;

public class DirectEdge {
    private Station A;
    private Station B;
    private String metroLine;
    private double weight;
    private int spCounter;

    public DirectEdge(Station a, Station b, String metroLine) {
        A = a;
        B = b;
        this.metroLine = metroLine;
        this.weight = Math.abs((a.getLon() - b.getLon()) / (a.getLat() - b.getLat()));
        spCounter = 0;

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

    public int getSpCounter() {
        return spCounter;
    }

    public void addSpToSpCounter() {
        spCounter++;
    }




    @Override
    public String toString() {
        return "{" + A.toString()
                + "->" + B.toString()
                + ", "+spCounter
                +")}";
    }
}
