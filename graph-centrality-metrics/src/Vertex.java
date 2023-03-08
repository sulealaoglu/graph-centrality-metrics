import java.util.ArrayList;

public class Vertex{
    private String name;
    private ArrayList<Edge> edges;
    private Vertex parent;
    private Vertex pred;
    private int dist;
    private int betweenness;
    private long totalCloseness;
    private double closeness;

    public Vertex() {
        betweenness=0;closeness=0;totalCloseness=Long.MAX_VALUE;
    }

    public Vertex(String name) {
        this.name = name;
        edges = new ArrayList<Edge>();
        parent = null;
        pred=null;
        dist=0;
        betweenness=0;
        totalCloseness =0;
        closeness=0;
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;

    }
    public void resetLabel(){
        this.pred=null;
        this.dist=0;
    }

    public long getTotalCloseness() {
        return totalCloseness;
    }

    public void setTotalCloseness(long totalCloseness) {
        this.totalCloseness = totalCloseness;
    }

    public int getBetweenness() {
        return betweenness;
    }

    public void setBetweenness(int betweenness) {
        this.betweenness = betweenness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public Vertex getPred() {
        return pred;
    }

    public void setPred(Vertex pred) {
        this.pred = pred;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public double getCloseness() {
        return 1.0/totalCloseness;
    }

    public void setCloseness(double closeness) {
        this.closeness = closeness;
    }
}
