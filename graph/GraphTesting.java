package graph;

import org.junit.Test;
import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author Peter Sorensen
 */
public class GraphTesting {

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals(0, g.vertexSize());
        assertEquals(0, g.edgeSize());
    }

    @Test
    public void addGraph() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        assertEquals(1, g.vertexSize());
        assertEquals(0, g.edgeSize());
    }

    @Test
    public void removeGraph() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.remove(1);
        assertEquals(0, g.vertexSize());
        assertEquals(0, g.edgeSize());
    }

    @Test
    public void outdegGraph() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add(1, 2);
        assertEquals(2, g.vertexSize());
        assertEquals(1, g.edgeSize());
        assertEquals(1, g.outDegree(1));
        assertEquals(0, g.outDegree(2));
    }

    @Test
    public void indegGraph() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add(1, 2);
        g.inDegree(1);
        assertEquals(2, g.vertexSize());
        assertEquals(1, g.edgeSize());
        assertEquals(1, g.inDegree(2));
    }

    @Test
    public void addedEdgesGraph() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        assertEquals(0, g.predecessor(4, 0));
        assertEquals(0, g.inDegree(4));
        g.remove(2);
        g.add(1, 3);
        g.add(1, 4);
        assertEquals(1, g.inDegree(4));
        g.add(3, 4);
        assertEquals(3, g.vertexSize());
        assertEquals(3, g.edgeSize());
        assertEquals(3, g.predecessor(4, 1));
        assertEquals(2, g.inDegree(4));
    }

    @Test
    public void addedGraph() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        assertEquals(0, g.predecessor(4, 0));
        assertEquals(0, g.inDegree(4));
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        assertEquals(1, g.inDegree(4));
        g.add(3, 4);
        g.add(2, 5);
        g.add(5, 6);
        g.add(3, 7);
        g.add(7, 8);
        assertEquals(8, g.vertexSize());
        assertEquals(8, g.edgeSize());
        assertEquals(3, g.predecessor(4, 1));
        assertEquals(2, g.inDegree(4));
    }

    @Test
    public void coolGraph() {
        UndirectedGraph gphi = new UndirectedGraph();
        gphi.add();
        gphi.add();
        gphi.add();
        gphi.add();
        assertEquals(4, gphi.vertexSize());
        gphi.add(1, 2);
        gphi.add(1, 3);
        assertEquals(2, gphi.edgeSize());
        gphi.add(3, 4);
        gphi.add();
        gphi.add(3, 5);
        assertEquals(2, gphi.outDegree(1));
        assertEquals(1, gphi.outDegree(2));
        assertEquals(gphi.contains(5), true);
        assertEquals(gphi.contains(0), false);
        assertEquals(gphi.contains(3, 5), true);
        gphi.remove(3, 5);
        assertEquals(gphi.contains(3, 5), false);
        gphi.remove(5);
        gphi.remove(4);
        assertEquals(gphi.contains(3, 4), false);
        assertEquals(gphi.contains(3, 5), false);
        gphi.remove(11);
        gphi.remove(11, 12);
        gphi.remove(-1);
        gphi.remove(-1, -2);
    }

    @Test
    public void sneakyGraph() {
        DirectedGraph gphi2 = new DirectedGraph();
        gphi2.add();
        gphi2.add();
        gphi2.add();
        gphi2.add();
        gphi2.add();
        gphi2.add();
        gphi2.add(1, 2);
        gphi2.add(1, 6);
        gphi2.add(2, 3);
        gphi2.add(2, 5);
        gphi2.add(3, 4);
        assertEquals(gphi2.contains(2, 1), false);
        assertEquals(gphi2.contains(1, 2), true);
        assertEquals(gphi2.contains(10, 2), false);
        assertEquals(gphi2.successor(1, 0), 2);
        assertEquals(gphi2.successor(4, 0), 0);
        assertEquals(gphi2.successor(1, 1), 6);
    }
}
