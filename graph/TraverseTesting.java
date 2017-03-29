package graph;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Unit tests for the Traversal class.
 *  @author Peter Sorensen
 */
public class TraverseTesting {

    private class DepthClient extends DepthFirstTraversal {
        /** A constructor. */
        protected DepthClient(Graph G) {
            super(G);
            _record = new ArrayList<>();
        }

        @Override
        protected boolean visit(int v) {
            _record.add(v);
            return true;
        }

        @Override
        protected boolean shouldPostVisit(int v) {
            return false;
        }

        public ArrayList<Integer> getRecord() {
            return _record;
        }

        /** Visited record. */
        private ArrayList<Integer> _record;
    }

    private class BreadthClient extends BreadthFirstTraversal {
        /** A constructor. */
        protected BreadthClient(Graph G) {
            super(G);
            _record = new ArrayList<>();
        }

        @Override
        protected boolean visit(int v) {
            _record.add(v);
            return true;
        }

        public ArrayList<Integer> getRecord() {
            return _record;
        }

        /** Visited record. */
        private ArrayList<Integer> _record;
    }

    private class DepthAfterClient extends DepthFirstTraversal {

        protected DepthAfterClient(Graph G) {
            super(G);
            _G = G;
            _record = new ArrayList<Integer>();
        }

        @Override
        protected boolean postVisit(int v) {
            _record.add(v);
            return true;
        }

        public ArrayList<Integer> getRecord() {
            return _record;
        }

        /** Visited record. */
        ArrayList<Integer> _record;
        /** The graph being traversed. */
        private Graph _G;
    }

    @Test
    public void testClientTraversal() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(4, 1);
        g.add(3, 2);
        g.add(1, 5);
        g.add(5, 4);
        g.add(5, 3);
        DepthClient bigD = new DepthClient(g);
        BreadthClient bigB = new BreadthClient(g);
        DepthAfterClient depthAftClient = new DepthAfterClient(g);
        bigD.traverse(5);
        bigB.traverse(5);
        depthAftClient.traverse(5);
        ArrayList<Integer> depthVisit = bigD.getRecord();
        ArrayList<Integer> depthexpect = new ArrayList<>();
        depthexpect.add(5);
        depthexpect.add(3);
        depthexpect.add(2);
        depthexpect.add(4);
        depthexpect.add(1);
        ArrayList<Integer> breadthvisit = bigB.getRecord();
        ArrayList<Integer> breadthexpect = new ArrayList<>();
        breadthexpect.add(5);
        breadthexpect.add(4);
        breadthexpect.add(3);
        breadthexpect.add(1);
        breadthexpect.add(2);
        ArrayList<Integer> depthpostvisit = depthAftClient.getRecord();
        ArrayList<Integer> defpthpostexpect = new ArrayList<>();
        defpthpostexpect.add(2);
        defpthpostexpect.add(3);
        defpthpostexpect.add(1);
        defpthpostexpect.add(4);
        defpthpostexpect.add(5);
        assertEquals(true, depthVisit.equals(depthexpect));
        assertEquals(true, breadthvisit.equals(breadthexpect));
        assertEquals(true, depthpostvisit.equals(defpthpostexpect));
    }
}
