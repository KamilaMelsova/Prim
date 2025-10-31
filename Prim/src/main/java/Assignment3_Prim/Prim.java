package Assignment3_Prim;
import java.util.*;
public class Prim {
    private Graph graph;
    public Prim(Graph graph) {
        this.graph = graph;
    }
    public Map<String, Object> findMST() {
        int[][] matrix = graph.getMatrix();
        List<String> nodes = graph.getNodes();
        int n = nodes.size();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0, -1});
        int totalCost = 0;
        int operations = 0;
        List<Map<String, Object>> mstEdges = new ArrayList<>();
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int weight = current[0];
            int u = current[1];
            int p = current[2];

            if (visited[u]) continue;
            visited[u] = true;
            operations++;

            if (p != -1) {
                totalCost += weight;
                Map<String, Object> edge = new HashMap<>();
                edge.put("from", nodes.get(p));
                edge.put("to", nodes.get(u));
                edge.put("cost", weight);
                mstEdges.add(edge);
            }
            for (int v = 0; v < n; v++) {
                if (matrix[u][v] != 0 && !visited[v]) {
                    pq.offer(new int[]{matrix[u][v], v, u});
                }
            }
        }
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("algorithm", "Prim (with PriorityQueue)");
        result.put("num_vertices", n);
        result.put("num_edges", mstEdges.size());
        result.put("mst_edges", mstEdges);
        result.put("total_cost", totalCost);
        result.put("operations", operations);
        return result;
    }
}
