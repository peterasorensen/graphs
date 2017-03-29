package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author Peter Sorensen
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        _preds.add(0);
        _weights.add(0.0);
        final int lotta = 2000;
        for (int i = 0; i < lotta; i++) {
            _preds.add(0);
            _weights.add(0.0);
        }
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    @Override
    protected abstract double getWeight(int u, int v);

    @Override
    public double getWeight(int v) {
        if (!_G.contains(v)) {
            return Double.MAX_VALUE;
        } else {
            return _weights.get(v);
        }
    }

    @Override
    protected void setWeight(int v, double w) {
        while (_weights.size() <= v) {
            _weights.add(0.0);
        }
        _weights.set(v, w);
    }

    @Override
    public int getPredecessor(int v) {
        if (!(_preds.size() > v)) {
            return 0;
        } else {
            return _preds.get(v);
        }
    }

    @Override
    protected void setPredecessor(int v, int u) {
        while (_preds.size() <= v) {
            _preds.add(0);
        }
        if (_preds.size() > v && _preds.size() > u) {
            _preds.set(v, u);
        }
    }

    /** ArrayList of all predecessors. */
    private ArrayList<Integer> _preds = new ArrayList<>();

    /** ArrayList of all weights of integers. */
    private ArrayList<Double> _weights = new ArrayList<>();

}
