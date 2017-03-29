package graph;

import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Comparator;
import java.util.AbstractQueue;
import java.util.List;
import java.util.LinkedList;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Peter Sorensen
 */
public abstract class ShortestPaths {

    /** A star Traversal class. */
    class AStarTraverse extends Traversal {

        /** Constructor to initialize an A-Star Traversal
         *  with graph G and queue FRINGE. */
        protected AStarTraverse(Graph G, Queue<Integer> fringe) {
            super(G, fringe);
        }

        @Override
        protected boolean visit(int v) {
            if (v != getDest()) {
                for (Integer each : _G.successors(v)) {
                    double vowDist, ngo, nowDist, wowDist;
                    vowDist = getWeight(v);
                    ngo = getWeight(v, each);
                    nowDist = ngo + vowDist;
                    wowDist = getWeight(each);
                    if (wowDist > nowDist) {
                        setWeight(each, nowDist);
                        setPredecessor(each, v);
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        _immAStarTrav = new AStarTraverse(_G, new AStarQ());
        for (Integer i : _G.vertices()) {
            setPredecessor(i, 0);
            setWeight(i, Double.MAX_VALUE);
        }
        setWeight(getSource(), 0);
        _immAStarTrav.traverse(getSource());
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        while (getSource() != v) {
            _journey.addFirst(v);
            v = getPredecessor(v);
        }
        _journey.addFirst(getSource());
        return _journey;
    }

    /** A PQ implmented for A Star. */
    class AStarQ extends AbstractQueue<Integer> {

        /** The Constructor for the A Star Queue. */
        public AStarQ() {
            _prioQ = new PriorityQueue<>(1, new VertCompa());
            _vertices = new ArrayList<>();
        }

        /** A comparator for vertex. */
        class VertCompa implements Comparator<VertNode> {

            @Override
            public int compare(VertNode one, VertNode two) {
                if (one.getValue() == two.getValue()) {
                    return 0;
                } else if (one.getValue() > two.getValue()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

        @Override
        public int size() {
            return _prioQ.size();
        }

        @Override
        public Iterator<Integer> iterator() {
            return _vertices.iterator();
        }

        @Override
        public Integer peek() {
            VertNode vert = _prioQ.peek();
            if (vert != null) {
                return vert.getVertex();
            } else {
                return null;
            }
        }

        @Override
        public Integer poll() {
            VertNode vert = _prioQ.poll();
            if (vert != null) {
                _vertices.remove((Integer) (vert.getVertex()));
                return vert.getVertex();
            } else {
                return null;
            }
        }

        @Override
        public boolean offer(Integer e) {
            _vertices.add(e);
            return _prioQ.offer(new VertNode(e, estimatedDistance(e)
                    + getWeight(e)));
        }

        /** Class for vertex nodes. */
        class VertNode {

            /** Constructor for Vertex Node with vertex number
             *  as VERTEX and weight as VALUE.*/
            public VertNode(int vertex, double value) {
                _vert = vertex;
                _val = value;
            }

            /** Returns the vertex number. */
            public int getVertex() {
                return _vert;
            }

            /** Returns the vertex value, weight plus estimated distance. */
            public double getValue() {
                return _val;
            }

            /** The vertex's number. */
            private int _vert;
            /** The estimated distance plus the total weight so far. */
            private double _val;
        }

        /** Vertex numbers in ArrayList. */
        private ArrayList<Integer> _vertices;
        /** Storing vertices with Priority Queue. */
        private PriorityQueue<VertNode> _prioQ;
    }

    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    /** A LinkedList of specified path. */
    private LinkedList<Integer> _journey = new LinkedList<>();
    /** Dijkstra Traversal with heuristic (A-Star). */
    private AStarTraverse _immAStarTrav;
}
