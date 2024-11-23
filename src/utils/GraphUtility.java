package utils;

import model.data_structures.*;

public class GraphUtility {

    public static <K extends Comparable<K>, V extends Comparable<V>> void dfs(Vertex<K, V> vertex) {
        vertex.mark();
        for (Edge<K, V> edge : vertex.edges()) {
            Vertex<K, V> dest = edge.getDestination();
            if (!dest.getMark()) {
                dfs(dest);
            }
        }
    }
}
