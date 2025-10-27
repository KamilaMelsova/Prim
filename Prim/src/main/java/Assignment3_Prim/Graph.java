package Assignment3_Prim;
import java.util.*;
public class Graph{
    private List<String> nodes;
    private int[][] adjacencyMatrix;

    public Graph(List<String> nodes, List<Map<String, Object>> edges){
        this.nodes = nodes;
        int n = nodes.size();
        adjacencyMatrix = new int[n][n];
        for (Map<String, Object> edge : edges) {
            String from = (String) edge.get("from");
            String to = (String) edge.get("to");
            int weight = (int) edge.get("weight");
            int i = nodes.indexOf(from);
            int j = nodes.indexOf(to);
            adjacencyMatrix[i][j] = weight;
            adjacencyMatrix[j][i] = weight;
        }}
    public int[][] getMatrix(){
        return adjacencyMatrix;
    }
    public List<String> getNodes(){
        return nodes;
    }
}
