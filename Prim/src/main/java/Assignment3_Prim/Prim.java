package Assignment3_Prim;
import java.util.*;
public class Prim{
    private Graph graph;
    public Prim(Graph graph){
        this.graph = graph;
    }
    public Map<String, Object> findMST(){
        int[][] matrix = graph.getMatrix();
        List<String> nodes = graph.getNodes();
        int n = nodes.size();

        boolean[] visited = new boolean[n];
        int[] key = new int[n];
        int[] parent = new int[n];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[0] = 0;
        int totalCost = 0;
        int operations = 0;
        for (int count = 0; count < n - 1; count++) {
            int u = minKey(key, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                operations++;
                if (matrix[u][v] != 0 && !visited[v] && matrix[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = matrix[u][v];
                }}}
        List<Map<String, Object>> mstEdges = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int from = parent[i];
            int to = i;
            int cost = matrix[from][to];
            totalCost += cost;

            Map<String, Object> edge = new HashMap<>();
            edge.put("from", nodes.get(from));
            edge.put("to", nodes.get(to));
            edge.put("cost", cost);
            mstEdges.add(edge);}
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("algorithm", "Prim");
        result.put("num_vertices", n);
        result.put("num_edges", mstEdges.size());
        result.put("mst_edges", mstEdges);
        result.put("total_cost", totalCost);
        result.put("operations", operations);

        return result;}
    private int minKey(int[] key, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < key.length; v++) {
            if (!visited[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }}
        return minIndex;
    }}