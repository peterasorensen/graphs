package graph;

import org.junit.Test;
import ucb.junit.textui;

import static org.junit.Assert.assertEquals;

/* You MAY add public @Test methods to this class.  You may also add
 * additional public classes containing "Testing" in their name. These
 * may not be part of your graph package per se (that is, it must be
 * possible to remove them and still have your package work). */

/** Unit tests for the graph package.  This class serves to dispatch
 *  other test classes, which are listed in the argument to runClasses.
 *  @author Peter Sorensen
 */
public class UnitTest {

    @Test(expected = IllegalArgumentException.class)
    public void testChecktMyVertex1() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.checkMyVertex(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChecktMyVertex2() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.add();
        dGraph.checkMyVertex(1);
        dGraph.checkMyVertex(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChecktMyVertex3() {
        UndirectedGraph uDGraph = new UndirectedGraph();
        uDGraph.checkMyVertex(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChecktMyVertex4() {
        UndirectedGraph uDGraph = new UndirectedGraph();
        uDGraph.add();
        uDGraph.checkMyVertex(1);
        uDGraph.checkMyVertex(2);
    }

    @Test
    public void testVertexSize() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.vertexSize());
        assertEquals(1, dGraph.add());
        assertEquals(2, dGraph.add());
        assertEquals(3, dGraph.add());
        assertEquals(3, dGraph.vertexSize());
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(0, uDGraph.vertexSize());
        assertEquals(1, uDGraph.add());
        assertEquals(2, uDGraph.add());
        assertEquals(3, uDGraph.add());
        assertEquals(3, uDGraph.vertexSize());
    }

    @Test
    public void testMaxxVertex() {
        DirectedGraph bigDikjGraph = new DirectedGraph();
        assertEquals(0, bigDikjGraph.maxVertex());
        assertEquals(1, bigDikjGraph.add());
        assertEquals(2, bigDikjGraph.add());
        assertEquals(3, bigDikjGraph.add());
        assertEquals(3, bigDikjGraph.maxVertex());
        assertEquals(4, bigDikjGraph.add());
        assertEquals(4, bigDikjGraph.maxVertex());
        UndirectedGraph ubigDikjGraph = new UndirectedGraph();
        assertEquals(0, ubigDikjGraph.maxVertex());
        assertEquals(1, ubigDikjGraph.add());
        assertEquals(2, ubigDikjGraph.add());
        assertEquals(3, ubigDikjGraph.add());
        assertEquals(3, ubigDikjGraph.maxVertex());
        assertEquals(4, ubigDikjGraph.add());
        assertEquals(4, ubigDikjGraph.maxVertex());
    }

    @Test
    public void testIssDirected() {
        DirectedGraph bigDikjGraph = new DirectedGraph();
        assertEquals(true, bigDikjGraph.isDirected());
        UndirectedGraph ubigDikjGraph = new UndirectedGraph();
        assertEquals(false, ubigDikjGraph.isDirected());
    }

    @Test
    public void testOutDegree() {
        DirectedGraph dijkheadgraph = new DirectedGraph();
        assertEquals(0, dijkheadgraph.outDegree(1));
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add();
        assertEquals(0, dijkheadgraph.outDegree(2));
        dijkheadgraph.add(1, 2);
        dijkheadgraph.add(1, 3);
        assertEquals(2, dijkheadgraph.outDegree(1));
        assertEquals(0, dijkheadgraph.outDegree(3));
        dijkheadgraph.add(3, 1);
        assertEquals(1, dijkheadgraph.outDegree(3));
    }

    @Test
    public void testInDegree() {
        DirectedGraph dijkheadgraph = new DirectedGraph();
        assertEquals(0, dijkheadgraph.inDegree(1));
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add();
        assertEquals(0, dijkheadgraph.inDegree(2));
        dijkheadgraph.add(1, 2);
        dijkheadgraph.add(1, 3);
        assertEquals(0, dijkheadgraph.inDegree(1));
        assertEquals(1, dijkheadgraph.inDegree(3));
        dijkheadgraph.add(3, 1);
        assertEquals(1, dijkheadgraph.inDegree(1));
        dijkheadgraph.add(2, 1);
        assertEquals(2, dijkheadgraph.inDegree(1));
    }

    @Test
    public void testDegree() {
        UndirectedGraph udikhheadgraph = new UndirectedGraph();
        assertEquals(0, udikhheadgraph.degree(1));
        udikhheadgraph.add();
        udikhheadgraph.add();
        udikhheadgraph.add();
        assertEquals(0, udikhheadgraph.degree(2));
        udikhheadgraph.add(1, 2);
        udikhheadgraph.add(1, 3);
        assertEquals(2, udikhheadgraph.degree(1));
        assertEquals(1, udikhheadgraph.degree(3));
        udikhheadgraph.add(3, 1);
        assertEquals(1, udikhheadgraph.degree(3));
    }

    @Test
    public void testRemove() {
        DirectedGraph dijkheadgraph = new DirectedGraph();
        dijkheadgraph.add();
        dijkheadgraph.add();
        assertEquals(true, dijkheadgraph.contains(1));
        dijkheadgraph.remove(1);
        assertEquals(false, dijkheadgraph.contains(1));
        dijkheadgraph.add();
        assertEquals(true, dijkheadgraph.contains(1));
        dijkheadgraph.add(1, 2);
        assertEquals(true, dijkheadgraph.contains(1, 2));
        dijkheadgraph.remove(1);
        assertEquals(false, dijkheadgraph.contains(1, 2));
        dijkheadgraph.add();
        dijkheadgraph.add(1, 2);
        assertEquals(true, dijkheadgraph.contains(1, 2));
        dijkheadgraph.remove(1, 2);
        assertEquals(false, dijkheadgraph.contains(1, 2));
        UndirectedGraph udikhheadgraph = new UndirectedGraph();
        udikhheadgraph.add();
        udikhheadgraph.add();
        assertEquals(true, udikhheadgraph.contains(1));
        udikhheadgraph.remove(1);
        assertEquals(false, udikhheadgraph.contains(1));
        udikhheadgraph.add();
        assertEquals(true, udikhheadgraph.contains(1));
        udikhheadgraph.add(1, 2);
        assertEquals(true, udikhheadgraph.contains(1, 2));
        udikhheadgraph.remove(1);
        assertEquals(false, udikhheadgraph.contains(1, 2));
        udikhheadgraph.add();
        udikhheadgraph.add(1, 2);
        assertEquals(true, udikhheadgraph.contains(1, 2));
        udikhheadgraph.remove(1, 2);
        assertEquals(false, udikhheadgraph.contains(1, 2));
    }

    @Test
    public void testVertices() {
        DirectedGraph dijkheadgraph = new DirectedGraph();
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add();
        Iteration<Integer> iter = dijkheadgraph.vertices();
        int count = 1;
        while (iter.hasNext()) {
            assertEquals(count, iter.next().intValue());
            count += 1;
        }
        UndirectedGraph udikhheadgraph = new UndirectedGraph();
        udikhheadgraph.add();
        udikhheadgraph.add();
        udikhheadgraph.add();
        iter = udikhheadgraph.vertices();
        count = 1;
        while (iter.hasNext()) {
            assertEquals(count, iter.next().intValue());
            count += 1;
        }
    }

    @Test
    public void testSuccessor() {
        DirectedGraph dijkheadgraph = new DirectedGraph();
        assertEquals(0, dijkheadgraph.successor(1, 1));
        assertEquals(0, dijkheadgraph.successor(2, 0));
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add(1, 2);
        dijkheadgraph.add(1, 3);
        assertEquals(2, dijkheadgraph.successor(1, 0));
        assertEquals(3, dijkheadgraph.successor(1, 1));
        dijkheadgraph.add(2, 3);
        assertEquals(3, dijkheadgraph.successor(2, 0));
        dijkheadgraph.remove(1);
        assertEquals(0, dijkheadgraph.successor(1, 1));
        dijkheadgraph.remove(2, 3);
        assertEquals(0, dijkheadgraph.successor(2, 0));
    }

    @Test
    public void testNeighbor() {
        UndirectedGraph udikhheadgraph = new UndirectedGraph();
        assertEquals(0, udikhheadgraph.neighbor(1, 1));
        assertEquals(0, udikhheadgraph.neighbor(2, 0));
        udikhheadgraph.add();
        udikhheadgraph.add();
        udikhheadgraph.add();
        udikhheadgraph.add(1, 2);
        udikhheadgraph.add(1, 3);
        assertEquals(2, udikhheadgraph.neighbor(1, 0));
        assertEquals(3, udikhheadgraph.neighbor(1, 1));
        udikhheadgraph.add(2, 3);
        assertEquals(1, udikhheadgraph.neighbor(2, 0));
        assertEquals(3, udikhheadgraph.neighbor(2, 1));
        udikhheadgraph.remove(1);
        assertEquals(0, udikhheadgraph.neighbor(1, 1));
        udikhheadgraph.remove(2, 3);
        assertEquals(0, udikhheadgraph.neighbor(2, 0));
        assertEquals(0, udikhheadgraph.neighbor(2, 1));
    }

    @Test
    public void testSuccessors() {
        DirectedGraph dijkheadgraph = new DirectedGraph();
        assertEquals(false, dijkheadgraph.successors(1).hasNext());
        assertEquals(false, dijkheadgraph.successors(2).hasNext());
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add(1, 2);
        dijkheadgraph.add(1, 3);
        dijkheadgraph.add(2, 4);
        dijkheadgraph.add(2, 3);
        Iteration<Integer> iter = dijkheadgraph.successors(1);
        assertEquals(2, iter.next().intValue());
        assertEquals(3, iter.next().intValue());
        iter = dijkheadgraph.successors(2);
        assertEquals(4, iter.next().intValue());
        assertEquals(3, iter.next().intValue());
        assertEquals(false, iter.hasNext());
    }

    @Test
    public void testPredecessors() {
        DirectedGraph dijkheadgraph = new DirectedGraph();
        assertEquals(false, dijkheadgraph.predecessors(1).hasNext());
        assertEquals(false, dijkheadgraph.predecessors(2).hasNext());
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add(3, 2);
        dijkheadgraph.add(1, 2);
        dijkheadgraph.add(1, 3);
        dijkheadgraph.add(2, 3);
        dijkheadgraph.add(2, 4);
        Iteration<Integer> iter = dijkheadgraph.predecessors(2);
        assertEquals(3, iter.next().intValue());
        assertEquals(1, iter.next().intValue());
        iter = dijkheadgraph.predecessors(3);
        assertEquals(1, iter.next().intValue());
        assertEquals(2, iter.next().intValue());
        iter = dijkheadgraph.predecessors(4);
        assertEquals(2, iter.next().intValue());
        assertEquals(false, iter.hasNext());
    }

    @Test
    public void testNeighbors() {
        UndirectedGraph udikhheadgraph = new UndirectedGraph();
        assertEquals(false, udikhheadgraph.neighbors(1).hasNext());
        assertEquals(false, udikhheadgraph.neighbors(2).hasNext());

        udikhheadgraph.add();
        udikhheadgraph.add();
        udikhheadgraph.add();
        udikhheadgraph.add();
        udikhheadgraph.add(1, 2);
        udikhheadgraph.add(1, 3);
        udikhheadgraph.add(2, 4);
        udikhheadgraph.add(2, 3);
        Iteration<Integer> iter = udikhheadgraph.neighbors(1);
        assertEquals(2, iter.next().intValue());
        assertEquals(3, iter.next().intValue());
        iter = udikhheadgraph.successors(2);
        assertEquals(1, iter.next().intValue());
        assertEquals(4, iter.next().intValue());
        assertEquals(3, iter.next().intValue());
        assertEquals(false, iter.hasNext());
    }

    @Test
    public void testEdges() {
        DirectedGraph dijkheadgraph = new DirectedGraph();
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add();
        dijkheadgraph.add(1, 2);
        dijkheadgraph.add(1, 3);
        dijkheadgraph.add(1, 4);
        dijkheadgraph.add(2, 3);
        dijkheadgraph.add(2, 4);
        Iteration<int[]> iter = dijkheadgraph.edges();
        int[] tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(2, tmp[1]);
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(3, tmp[1]);
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(4, tmp[1]);
        tmp = iter.next();
        assertEquals(2, tmp[0]);
        assertEquals(3, tmp[1]);
        tmp = iter.next();
        assertEquals(2, tmp[0]);
        assertEquals(4, tmp[1]);
        assertEquals(false, iter.hasNext());
        UndirectedGraph udikhheadgraph = new UndirectedGraph();
        udikhheadgraph.add();
        udikhheadgraph.add();
        udikhheadgraph.add();
        udikhheadgraph.add();
        udikhheadgraph.add(1, 2);
        udikhheadgraph.add(1, 2);
        udikhheadgraph.add(2, 1);
        udikhheadgraph.add(1, 3);
        udikhheadgraph.add(3, 1);
        iter = udikhheadgraph.edges();
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(2, tmp[1]);
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(3, tmp[1]);
        assertEquals(false, iter.hasNext());
    }

    @Test
    public void emptyGraph2() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
        UndirectedGraph uG = new UndirectedGraph();
        assertEquals(0, uG.vertexSize());
        assertEquals(0, uG.edgeSize());
    }

    @Test
    public void testUndirectedMaxVertex() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(10, 7);
        assertEquals(12, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(1);
        assertEquals(8, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(10);
        assertEquals(6, g.edgeSize());
        assertEquals(9, g.maxVertex());
        g.remove(2, 6);
        assertEquals(5, g.edgeSize());
        assertEquals(9, g.maxVertex());
        g.remove(7, 3);
        assertEquals(4, g.edgeSize());
        assertEquals(9, g.maxVertex());
    }

    @Test
    public void testUndirectedEdgeSize() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(1, 1);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(10, 7);
        assertEquals(13, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(1);
        assertEquals(8, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(2);
        assertEquals(5, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(2, 5);
        assertEquals(5, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(7, 10);
        assertEquals(4, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(8);
        assertEquals(1, g.edgeSize());
        assertEquals(10, g.maxVertex());
    }

    @Test
    public void testDirectedMaxVertex() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(10, 7);
        assertEquals(12, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(1);
        assertEquals(8, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(10);
        assertEquals(6, g.edgeSize());
        assertEquals(9, g.maxVertex());
        g.remove(10, 9);
        assertEquals(6, g.edgeSize());
        assertEquals(9, g.maxVertex());
        g.remove(8, 9);
        assertEquals(5, g.edgeSize());
        assertEquals(9, g.maxVertex());
    }

    @Test
    public void testDirectedEdgeSize() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(10, 7);
        assertEquals(12, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(1, 2);
        assertEquals(11, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(2, 5);
        assertEquals(10, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(10, 7);
        assertEquals(9, g.edgeSize());
        assertEquals(10, g.maxVertex());
    }

    /** Run all JUnit tests in the graph package. */
    public static void main(String[] ignored) {
        System.exit(textui.runClasses(graph.GraphTesting.class));
    }

}
