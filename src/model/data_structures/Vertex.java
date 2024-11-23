// Refactored Vertex.java

package model.data_structures;

import utils.GraphUtility;

public class Vertex<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Vertex<K, V>> {
    private K key;
    private V value;
    private ILista<Edge<K, V>> edges;
    private boolean marked;

    public Vertex(K id, V value) {
        this.key = id;
        this.value = value;
        this.edges = new ArregloDinamico<>(1);
    }

    public K getId() {
        return key;
    }

    public V getInfo() {
        return value;
    }

    public boolean getMark() {
        return marked;
    }

    public void addEdge(Edge<K, V> edge) {
        try {
            edges.insertElement(edge, edges.size() + 1);
        } catch (PosException | NullException e) {
            e.printStackTrace();
        }
    }

    public int outdegree() {
        return edges.size();
    }

    public int indegree() {
        return edges.size();
    }

    public ILista<Edge<K, V>> getEdges() {
        return edges;
    }

    @Override
    public int compareTo(Vertex<K, V> o) {
        return key.compareTo(o.getId());
    }
}
