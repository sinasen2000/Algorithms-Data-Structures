

public class AdjacencyMapGraph<V, E> implements Graph<V, E>{


    class InnerVertex<V> implements Vertex<V>{

        private V element;
        private Position<Vertex<V>> pos;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;

        public InnerVertex(V elem, boolean isDirected){
            element = elem;
            outgoing = new UnsortedTableMap<>();
            if (isDirected){
                incoming = new UnsortedTableMap<>();
            }else{
                incoming = outgoing;
            }
        }

        public V getElement(){
            return element;
        }
        public void setPosition(Position<Vertex<V>> p){
            pos = p;
        }
        public Position<Vertex<V>> getPosition(){
            return pos;
        }
        public Map<Vertex<V>, Edge<E>> getOutgoing(){
            return outgoing;
        }
        public Map<Vertex<V>, Edge<E>> getIncoming(){
            return incoming;
        }

    }

    class InnerEdge<E> implements Edge<E>{

        private E element;
        private Position<Edge<E>> pos;
        private Vertex<V>[] endpoints;

        public InnerEdge(Vertex<V> v, Vertex<V> u, E elem){

            element = elem;
            endpoints = (Vertex<V>[]) new Vertex[]{u, v};
        }

        @Override
        public E getElement() {
            return element;
        }

        public Vertex<V>[] getEndpoints(){
            return endpoints;
        }

        public void setPosition(Position<Edge<E>> p){
            pos = p;
        }

        public Position<Edge<E>> getPosition(){
            return pos;
        }
    }

    private boolean isDirected;

    private PositionalList<Vertex<V>> vertices = new PositionalList<>();
    private PositionalList<Edge<E>> edges = new PositionalList<>();


    public AdjacencyMapGraph(boolean directed){
        isDirected = directed;
    }

    public int numVertices(){
        return vertices.size();
    }

    public Iterable<Vertex<V>> vertices(){
        return vertices;
    }

    public int numEdges(){
        return edges.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edges;
    }

    public int outDegree(InnerVertex<V> v){
        return v.outgoing.values();
    }
}
