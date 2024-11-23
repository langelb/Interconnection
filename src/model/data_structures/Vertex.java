package model.data_structures;

import java.util.logging.Logger;

public class Vertex<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Vertex<K, V>> {
    private K key; 
    private V value; 
    private ILista<Edge<K, V>> edges;
    private ILista<Edge<K, V>> incomingEdges;
    private boolean marked; 
    private static final Logger logger = Logger.getLogger(MinPQ.class.getName());
    
    public Vertex(K id, V value) {
        this.key = id;
        this.value = value;
        this.edges = new ListaEncadenada<>();
        this.incomingEdges = new ListaEncadenada<>();
        this.marked = false;
    }
    
    public K getId() {
        return key;
    }

    public V getInfo() {
        return value;
    }

    public void setInfo(V info) {
        this.value = info;
    }

    public boolean getMark() {
        return marked;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    public int indegree() {
        return incomingEdges.size();
    }

    public int outdegree() {
        return edges.size();
    }

    public ILista<Edge<K, V>> edges() {
        return this.edges;
    }
    
    public void addEdge(Edge<K, V> edge) {
        try {
            edges.insertElement(edge, edges.size() + 1);
        } catch (PosException | NullException e) {
            logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
        }
    }

    public void addIncomingEdge(Edge<K, V> edge) {
        try {
            incomingEdges.addLast(edge);
        } catch (NullException e) {
            logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
        }
    }

    public Edge<K, V> getEdge(K destinationId) {
        try {
            for (int i = 1; i <= edges.size(); i++) {
                Edge<K, V> edge = edges.getElement(i);
                if (edge.getDestination().getId().equals(destinationId)) {
                    return edge;
                }
            }
        } catch (PosException | VacioException e) {
            logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
        }
        return null; 
    }
    
    public ILista<Vertex<K, V>> vertices() {
        ILista<Vertex<K, V>> adjacentVertices = new ListaEncadenada<>();
        try {
            for (int i = 1; i <= edges.size(); i++) {
                adjacentVertices.addLast(edges.getElement(i).getDestination());
            }
        } catch (PosException | VacioException | NullException e) {
            logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
        }
        return adjacentVertices;
    }

    public void dfs(Vertex<K, V> parent) {
        
        this.mark();
        
        
        for (Edge<K, V> edge : this.edges()) {
            Vertex<K, V> neighbor = edge.getDestination();
            if (!neighbor.getMark()) { 
                neighbor.dfs(this);    
            }
        }
    }

    public void bfs() {
        ColaEncadenada<Vertex<K, V>> queue = new ColaEncadenada<>();
        this.mark(); 
        queue.enqueue(this);
    
        while (!queue.isEmpty()) {
            try {
                Vertex<K, V> current = queue.dequeue();
                for (Edge<K, V> edge : current.edges()) {
                    Vertex<K, V> neighbor = edge.getDestination();
                    if (!neighbor.getMark()) { 
                        neighbor.mark(); 
                        queue.enqueue(neighbor); 
                    }
                }
            } catch (VacioException e) {
                logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
            }
        }
    }

    public void getSCC(ITablaSimbolos<K, Integer> tabla, int idComponente) {
        this.mark(); 
        tabla.put(this.getId(), idComponente); 
    
        
        for (Edge<K, V> edge : this.edges()) {
            Vertex<K, V> neighbor = edge.getDestination();
            if (!neighbor.getMark()) {
                neighbor.getSCC(tabla, idComponente); 
            }
        }
    }
    
    public void topologicalOrder(ColaEncadenada<Vertex<K, V>> pre, 
                             ColaEncadenada<Vertex<K, V>> post, 
                             PilaEncadenada<Vertex<K, V>> reversePost) {
        pre.enqueue(this); 
        this.mark(); 

        
        for (Edge<K, V> edge : this.edges()) {
            Vertex<K, V> neighbor = edge.getDestination();
            if (!neighbor.getMark()) {
                neighbor.topologicalOrder(pre, post, reversePost); 
            }
        }

        post.enqueue(this); 
        reversePost.push(this); 
    }

    public ILista<Edge<K, V>> mstPrimLazy() throws NullException {
        ILista<Edge<K, V>> mst = new ListaEncadenada<>(); 
        ColaEncadenada<Edge<K, V>> minHeap = new ColaEncadenada<>(); 
    
        this.mark(); 
    
        
        for (Edge<K, V> edge : this.edges()) {
            minHeap.enqueue(edge);
        }
    
        
        while (!minHeap.isEmpty()) {
            try {
                
                Edge<K, V> minEdge = minHeap.dequeue();
    
                Vertex<K, V> destination = minEdge.getDestination();
    
                
                if (!destination.getMark()) {
                    
                    destination.mark();
    
                    
                    mst.addLast(minEdge);
    
                    
                    for (Edge<K, V> edge : destination.edges()) {
                        if (!edge.getDestination().getMark()) {
                            minHeap.enqueue(edge);
                        }
                    }
                }
            } catch (VacioException e) {
                logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
            }
        }
    
        return mst;
    }

    public ITablaSimbolos<K, NodoTS<Float, Edge<K, V>>> minPathTree() {
        
        ITablaSimbolos<K, NodoTS<Float, Edge<K, V>>> tree = new TablaHashLinearProbing<>(10);
        
        MinPQ<Float, Vertex<K, V>> priorityQueue = new MinPQ<>(10);
    
        
        tree.put(this.getId(), new NodoTS<>(0.0f, null));
        priorityQueue.insert(0.0f, this);
    
        while (!priorityQueue.isEmpty()) {
            NodoTS<Float, Vertex<K, V>> current = priorityQueue.delMin();
            Vertex<K, V> currentVertex = current.getValue();
    
            
            currentVertex.mark();
    
            for (Edge<K, V> edge : currentVertex.edges()) {
                Vertex<K, V> neighbor = edge.getDestination();
                if (!neighbor.getMark()) {
                    float newDist = tree.get(currentVertex.getId()).getKey() + edge.getWeight();
    
                    NodoTS<Float, Edge<K, V>> neighborInfo = tree.get(neighbor.getId());
                    if (neighborInfo == null || newDist < neighborInfo.getKey()) {
                        tree.put(neighbor.getId(), new NodoTS<>(newDist, edge));
                        priorityQueue.insert(newDist, neighbor);
                    }
                }
            }
        }
    
        
        this.unmark();
    
        return tree;
    }    
    
    @Override
    public int compareTo(Vertex<K, V> o) {
        return key.compareTo(o.getId());
    }
}
