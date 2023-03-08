import java.util.*;

public class Graph {
    private HashMap<String, Vertex> vertices;
    private HashMap<String, Edge> edges;
    private int[][] adjacency;
    private int size;;
    private Vertex max_closeness_vertex, max_betweennes_vertex;

    public Graph(int size) {
        vertices = new HashMap<>();
        edges = new HashMap<>();
        adjacency = new int[size][size];
        this.size = size;
        max_betweennes_vertex =new Vertex();
        max_closeness_vertex =new Vertex();
    }

    public void calculateAllDijkstra() {
        for (Vertex v : vertices.values()
        )
            dijkstra(v.getName());

    }

    public Vertex getMax_betweennes_vertex() {
        return max_betweennes_vertex;
    }

    public Vertex getMax_closeness_vertex() {
        return max_closeness_vertex;
    }

    public void addEdge(String source, String destination) {
        if (edges.get(source + "-" + destination) == null && edges.get(destination + "-" + source) == null) {
            Vertex source_v, destination_v;
            if (vertices.get(source) == null) {
                source_v = new Vertex(source);
                vertices.put(source, source_v);
            } else
                source_v = vertices.get(source);

            if (vertices.get(destination) == null) {
                destination_v = new Vertex(destination);
                vertices.put(destination, destination_v);
            } else
                destination_v = vertices.get(destination);

            Edge edge = new Edge(source_v, destination_v);
            source_v.addEdge(edge);
            destination_v.addEdge(edge);
            edges.put(source + "-" + destination, edge);
            int source_index = Integer.parseInt(vertices.get(source).getName());
            int destination_index = Integer.parseInt(vertices.get(destination).getName());
            adjacency[source_index][destination_index] = 1;
            adjacency[destination_index][source_index] = 1;
        }

    }

    public void dijkstra(String src) {
        long totalDist=0;
        int size = vertices.size() + 1;
        int[] dist = new int[size];
        Boolean added[] = new Boolean[size];
        int[] parents = new int[size];
        parents[Integer.valueOf(src)] =-2;
        for (int i = 1; i < size; i++) {
            dist[i] = Integer.MAX_VALUE;
            added[i] = false;
        }
        dist[Integer.parseInt(src)] = 0;
        for (int i = 1; i < size; i++) {
            int u = minDistance(dist, added);
            added[u] = true;
            for (int j = 1; j < size; j++){
                if (!added[j] && adjacency[u][j] != 0 &&
                        dist[u] != Integer.MAX_VALUE && dist[u] + 1 < dist[j]) {
                    parents[j] = u;
                    dist[j] = dist[u] + 1;
                }
            }
        }
        for (int i = 0; i <dist.length ; i++) {
            totalDist+=dist[i];
        }
        printSolution(dist, src, parents);
        Vertex src_vertex=vertices.get(src);
        src_vertex.setTotalCloseness(totalDist);
        if (totalDist<= max_closeness_vertex.getTotalCloseness()) {
            max_closeness_vertex =src_vertex;
        }
    }

    public void printSolution(int dist[], String src, int[] parents) {
        for (int i = 1; i < vertices.size()+1; i++) {
            if (i != Integer.valueOf(src) && i > Integer.valueOf(src)) {
                // System.out.print("\n" + Integer.valueOf(src) + " -> ");
                //  System.out.print(i + " \t\t ");
                if (dist[i] != Integer.MAX_VALUE) {
                    // System.out.print(dist[i] + "\t\t");
                    printPath(i, parents,src,i);
                } //else
                //System.out.println("No Path");
            }
        }
    }

    public void printPath(int currentVertex,int[] parents,String src,int dest) {
        if (currentVertex == -2) //no parent
            return;
        int betweennes= max_betweennes_vertex.getBetweenness();
        Vertex v= vertices.get(String.valueOf(currentVertex));
        if (!v.getName().equals(String.valueOf(dest))&&!v.getName().equals(src)) {
            int newValue=v.getBetweenness()+1;
            v.setBetweenness(newValue);
            if (betweennes<=newValue&&!max_betweennes_vertex.equals(v))
                max_betweennes_vertex =v;
        }
        printPath(parents[currentVertex], parents,src,dest);
        //System.out.print(currentVertex + " ");
    }

    public int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int i = 1; i < vertices.size() + 1; i++)
            if (sptSet[i] == false && dist[i] <= min) {
                min = dist[i];
                min_index = i;
            }
        return min_index;
    }


}
