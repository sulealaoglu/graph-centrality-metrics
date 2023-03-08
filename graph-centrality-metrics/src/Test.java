import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        Graph karateGraph = new Graph(35);
        Graph facebookGraph = new Graph(1519);
        System.out.println("Hatice Şule Alaoğlu");
        calculateKarateGraph(karateGraph);
        calculateFacebookGraph(facebookGraph);
    }

    public static void calculate(String name,Graph graph) {
        graph.calculateAllDijkstra();
        System.out.println(name+" – The Highest Node for Betweennes and the value " + graph.getMax_betweennes_vertex().getName() + " " + graph.getMax_betweennes_vertex().getBetweenness());
        System.out.println(name+" – The Highest Node for Closeness and the value " + graph.getMax_closeness_vertex().getName() + " " + graph.getMax_closeness_vertex().getCloseness());
    }

    public static void calculateFacebookGraph(Graph facebookGraph) throws IOException {
        String path2 = "facebook_social_network.txt";
        readFile(path2, facebookGraph);
        calculate("Facebook Social Network ",facebookGraph);

    }

    public static void calculateKarateGraph(Graph karateGraph) throws IOException {
        String path1 = "karate_club_network.txt";
        readFile(path1, karateGraph);
        calculate("Zachary Karate Club Network",karateGraph);

    }
    public static void readFile(String path, Graph graph) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            String[] vertices = line.split(" ");
            String source = vertices[0];
            String dest = vertices[1];
            graph.addEdge(source, dest);
            line = br.readLine();
        }
    }
}
