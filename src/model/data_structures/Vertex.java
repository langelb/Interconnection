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
            e.printStackTrace();
        }
    }

    public void addIncomingEdge(Edge<K, V> edge) {
        try {
            incomingEdges.addLast(edge);
        } catch (NullException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return adjacentVertices;
    }

    public void dfs(Vertex<K, V> parent) {
        // Marca el vértice actual como visitado
        this.mark();
        
        // Recorre todas las aristas salientes
        for (Edge<K, V> edge : this.edges()) {
            Vertex<K, V> neighbor = edge.getDestination();
            if (!neighbor.getMark()) { // Si el vértice vecino no ha sido visitado
                neighbor.dfs(this);    // Llamada recursiva al vértice vecino
            }
        }
    }

    public void bfs() {
        ColaEncadenada<Vertex<K, V>> queue = new ColaEncadenada<>();
        this.mark(); // Marca el vértice actual como visitado
        queue.enqueue(this);
    
        while (!queue.isEmpty()) {
            try {
                Vertex<K, V> current = queue.dequeue();
                for (Edge<K, V> edge : current.edges()) {
                    Vertex<K, V> neighbor = edge.getDestination();
                    if (!neighbor.getMark()) { // Si el vecino no ha sido visitado
                        neighbor.mark(); // Márquelo como visitado
                        queue.enqueue(neighbor); // Agréguelo a la cola
                    }
                }
            } catch (VacioException e) {
                logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
            }
        }
    }

    public void getSCC(ITablaSimbolos<K, Integer> tabla, int idComponente) {
        this.mark(); // Marca el vértice actual como visitado
        tabla.put(this.getId(), idComponente); // Asocia el vértice con el ID del componente
    
        // Recorre las aristas salientes
        for (Edge<K, V> edge : this.edges()) {
            Vertex<K, V> neighbor = edge.getDestination();
            if (!neighbor.getMark()) {
                neighbor.getSCC(tabla, idComponente); // Llamada recursiva
            }
        }
    }
    
    public void topologicalOrder(ColaEncadenada<Vertex<K, V>> pre, 
                             ColaEncadenada<Vertex<K, V>> post, 
                             PilaEncadenada<Vertex<K, V>> reversePost) {
        pre.enqueue(this); // Agregar el vértice a la cola de preorden
        this.mark(); // Marcar el vértice como visitado

        // Recorrer las aristas salientes del vértice
        for (Edge<K, V> edge : this.edges()) {
            Vertex<K, V> neighbor = edge.getDestination();
            if (!neighbor.getMark()) {
                neighbor.topologicalOrder(pre, post, reversePost); // Llamada recursiva
            }
        }

        post.enqueue(this); // Agregar el vértice a la cola de postorden
        reversePost.push(this); // Agregar el vértice a la pila de orden inverso
    }

    public ILista<Edge<K, V>> mstPrimLazy() throws NullException {
        ILista<Edge<K, V>> mst = new ListaEncadenada<>(); // Lista para almacenar las aristas del MST
        ColaEncadenada<Edge<K, V>> minHeap = new ColaEncadenada<>(); // Cola de prioridad para manejar aristas
    
        this.mark(); // Marca el vértice actual como visitado
    
        // Agrega todas las aristas salientes del vértice inicial a la cola de prioridad
        for (Edge<K, V> edge : this.edges()) {
            minHeap.enqueue(edge);
        }
    
        // Mientras haya aristas en la cola de prioridad
        while (!minHeap.isEmpty()) {
            try {
                // Obtiene la arista con menor peso
                Edge<K, V> minEdge = minHeap.dequeue();
    
                Vertex<K, V> destination = minEdge.getDestination();
    
                // Si el destino de la arista no está marcado (no visitado)
                if (!destination.getMark()) {
                    // Marca el vértice destino
                    destination.mark();
    
                    // Agrega la arista al MST
                    mst.addLast(minEdge);
    
                    // Agrega todas las aristas salientes del destino a la cola de prioridad
                    for (Edge<K, V> edge : destination.edges()) {
                        if (!edge.getDestination().getMark()) {
                            minHeap.enqueue(edge);
                        }
                    }
                }
            } catch (VacioException e) {
                e.printStackTrace();
            }
        }
    
        return mst;
    }

    public ITablaSimbolos<K, NodoTS<Float, Edge<K, V>>> minPathTree() {
        // Tabla para almacenar las distancias mínimas desde este vértice
        ITablaSimbolos<K, NodoTS<Float, Edge<K, V>>> tree = new TablaHashLinearProbing<>(10);
        // Cola de prioridad para procesar los vértices
        MinPQ<Float, Vertex<K, V>> priorityQueue = new MinPQ<>(10);
    
        // Inicializa las distancias mínimas de todos los vértices como infinito
        tree.put(this.getId(), new NodoTS<>(0.0f, null));
        priorityQueue.insert(0.0f, this);
    
        while (!priorityQueue.isEmpty()) {
            NodoTS<Float, Vertex<K, V>> current = priorityQueue.delMin();
            Vertex<K, V> currentVertex = current.getValue();
    
            // Marca el vértice como procesado
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
    
        // Desmarca todos los vértices al final
        this.unmark();
    
        return tree;
    }    
    
    @Override
    public int compareTo(Vertex<K, V> o) {
        return key.compareTo(o.getId());
    }
}
