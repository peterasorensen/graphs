package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayDeque;
import java.util.ArrayList;

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Peter Sorensen
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        _verts = new ArrayList<>();
        _edges.add(new ArrayList<>());
        _predecessors.add(new ArrayList<>());
    }

    @Override
    public int vertexSize() {
        return _verts.size();
    }

    @Override
    public int maxVertex() {
        int max = 0;
        for (int each : _verts) {
            if (each > max) {
                max = each;
            }
        }
        return max;
    }

    @Override
    public int edgeSize() {
        int total = 0;
        for (int i = 0; i < _edges.size(); i++) {
            for (Object ea : _edges.get(i)) {
                if ((Integer) ea == i && !isDirected()) {
                    total++;
                }
                total++;
            }
        }
        if (!isDirected()) {
            total /= 2;
        }
        return total;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (!_verts.contains(v)) {
            return 0;
        }
        return _edges.get(v).size();
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return _verts.contains(u);
    }

    @Override
    public boolean contains(int u, int v) {
        return _verts.contains(u) && _verts.contains(v)
                && (_edges.get(u).contains(v));
    }

    @Override
    public int add() {
        boolean found = false;
        int i = 0;
        while (!found) {
            i++;
            if (!_verts.contains(i)) {
                _verts.add(i);
                _edges.add(new ArrayList<>());
                _predecessors.add(new ArrayList<>());
                found = true;
            }
        }
        return i;
    }

    @Override
    public int add(int u, int v) {
        if (_edges.get(u).contains(v)) {
            return edgeId(u, v);
        } else if (!isDirected() && u != v) {
            _edges.get(v).add(u);
            _predecessors.get(u).add(v);
        }
        _edges.get(u).add(v);
        _predecessors.get(v).add(u);
        return edgeId(u, v);
    }

    @Override
    public void remove(int v) {
        if (contains(v)) {
            for (Integer each : _verts) {
                remove(each, v);
                remove(v, each);
            }
            _verts.remove((Integer) v);
        }
    }

    @Override
    public void remove(int u, int v) {
        if (_edges.size() - 1 < u || _edges.size() - 1 < v
                || u < 1 || v < 1) {
            return;
        }
        _edges.get(u).remove((Integer) v);
        _predecessors.get(v).remove((Integer) u);
        if (!isDirected()) {
            _edges.get(v).remove((Integer) u);
            _predecessors.get(u).remove((Integer) v);
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        return Iteration.iteration(_verts.iterator());
    }

    @Override
    public int successor(int v, int k) {
        if (!_verts.contains(v)) {
            return 0;
        }
        if (_edges.get(v).size() - 1 < k || k < 0) {
            return 0;
        }
        return _edges.get(v).get(k);
    }

    @Override
    public abstract int predecessor(int v, int k);

    @Override
    public Iteration<Integer> successors(int v) {
        if (contains(v)) {
            return Iteration.iteration(_edges.get(v).iterator());
        } else {
            ArrayDeque<Integer> emp = new ArrayDeque<>();
            return Iteration.iteration(emp.iterator());
        }
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        ArrayList<int[]> edgy = new ArrayList<>();
        for (int i = 0; i < _edges.size(); i++) {
            for (int each : _edges.get(i)) {
                if (!isDirected()) {
                    if (i <= each) {
                        edgy.add(new int[]{i, each});
                    }
                } else {
                    edgy.add(new int[]{i, each});
                }
            }
        }
        return Iteration.iteration(edgy.iterator());
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new IllegalArgumentException("vertex not from Graph");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        if (!(contains(u, v))) {
            return 0;
        }
        if (!isDirected()) {
            if (u < v) {
                int temp = u;
                u = v;
                v = temp;
            }
        }
        return (u >= v) ? (u * u + u + v) : (v * v + u);
    }

    /** Returns the ArrayList of vertices. */
    ArrayList<Integer> getVerts() {
        return _verts;
    }

    /** Returns the ArrayList of edges. */
    ArrayList<ArrayList<Integer>> getEdges() {
        return _edges;
    }

    /** Returns the ArrayList of predecessors. */
    ArrayList<ArrayList<Integer>> getPreds() {
        return _predecessors;
    }

    /** ArrayList which stores vertex numbers. */
    private ArrayList<Integer> _verts;

    /** ArrayList of ArrayLists of edges implemented as an adjacency list. */
    private ArrayList<ArrayList<Integer>> _edges = new ArrayList<>();

    /** ArrayList of ArrayLists of vertices predecessors. */
    private ArrayList<ArrayList<Integer>> _predecessors = new ArrayList<>();
}
