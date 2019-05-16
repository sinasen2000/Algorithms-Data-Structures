public interface Graph<V, E> {
    public int numVertices();
    public Iterable<Vertex<V>> vertices();

    public int numEdges();
    public Iterable<Edge<E>> edges();


    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v);
    public Vertex<V> opposite(Vertex<V> u, Edge<E> e);

    public Iterable<Edge<E>> outgoingEdges(Vertex<V> u);
    public Iterable<Edge<E>> incomingEdges(Vertex<V> u);


    public Vertex<V> insertVertex(V x);
    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> u, E e);
    public void removeVertex(Vertex<V> v);
    public void removeEdge(Edge<E> e);

}
