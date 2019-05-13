package metroGraph;

public class DirectEdge {
    private Station A;
    private Station B;
    private String metroLine;

    public DirectEdge(Station a, Station b, String metroLine) {
        A = a;
        B = b;
        this.metroLine = metroLine;
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
        return "{" + A.toString() + "," + B.toString() + "}";
    }
}
