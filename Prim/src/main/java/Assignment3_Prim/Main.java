package Assignment3_Prim;
import java.nio.file.*;
import java.util.*;
import org.json.*;
public class Main{
    public static void main(String[] args) {
        try{
            String jsonData = new String(Files.readAllBytes(Paths.get("src/input.json")));
            JSONObject obj = new JSONObject(jsonData);
            JSONArray graphs = obj.getJSONArray("graphs");
            JSONArray resultsArray = new JSONArray();

            for (int g = 0; g < graphs.length(); g++) {
                JSONObject graphData = graphs.getJSONObject(g);
                JSONArray nodesArray = graphData.getJSONArray("nodes");
                JSONArray edgesArray = graphData.getJSONArray("edges");

                List<String> nodes = new ArrayList<>();
                for (int i = 0; i < nodesArray.length(); i++) {
                    nodes.add(nodesArray.getString(i));
                }

                List<Map<String, Object>> edges = new ArrayList<>();
                for (int i = 0; i < edgesArray.length(); i++) {
                    JSONObject e = edgesArray.getJSONObject(i);
                    Map<String, Object> edge = new HashMap<>();
                    edge.put("from", e.getString("from"));
                    edge.put("to", e.getString("to"));
                    edge.put("weight", e.getInt("weight"));
                    edges.add(edge);
                }

                Graph graph = new Graph(nodes, edges);
                Prim prim = new Prim(graph);

                long start = System.nanoTime();
                Map<String, Object> primResult = prim.findMST();
                long end = System.nanoTime();
                double execTime = (end - start) / 1_000_000.0;
                Object ops = primResult.remove("operations");
                primResult.put("operations_count", ops);
                primResult.put("execution_time_ms", Math.round(execTime * 100.0) / 100.0); // округление до сотых
                JSONObject graphResult = new JSONObject();
                graphResult.put("graph_id", graphData.getInt("id"));
                JSONObject inputStats = new JSONObject();
                inputStats.put("vertices", nodes.size());
                inputStats.put("edges", edges.size());
                graphResult.put("input_stats", inputStats);
                graphResult.put("prim", new JSONObject(primResult));
                resultsArray.put(graphResult);}
            JSONObject finalOutput = new JSONObject();
            finalOutput.put("results", resultsArray);
            Files.write(Paths.get("prim_output.json"), finalOutput.toString(4).getBytes());
            System.out.println(finalOutput.toString(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


