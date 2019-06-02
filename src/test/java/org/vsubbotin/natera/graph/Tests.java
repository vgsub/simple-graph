package org.vsubbotin.natera.graph;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.vsubbotin.natera.graph.impl.GraphBuilder;

@Slf4j
public class Tests {
    @Test
    public void testUndirectedGraphCreation() {
        Graph<String> graph = GraphBuilder.<String>create().undirected().build();
        graph.addVertex("Alice");
        graph.addEdge("Alice", "Bob");
        graph.addEdge("Bob", "Den");
        graph.addEdge("Bob", "Charlie");
        graph.addEdge("Den", "Eagle");

        log.debug("Graph: {}", graph);

        Assert.assertEquals("[Alice, Den, Charlie]", graph.getAdjacent("Bob").toString());
    }

    @Test
    public void testDirectedGraphCreation() {
        Graph<String> graph = GraphBuilder.<String>create().directed().build();
        graph.addVertex("Alice");
        graph.addEdge("Alice", "Bob");
        graph.addEdge("Bob", "Den");
        graph.addEdge("Bob", "Charlie");
        graph.addEdge("Den", "Eagle");

        log.debug("Graph: {}", graph);

        Assert.assertEquals("[Den, Charlie]", graph.getAdjacent("Bob").toString());
    }

    @Test
    public void testUndirectedGraphGetPath() {
        Graph<String> graph = GraphBuilder.<String>create().undirected().build();
        graph.addVertex("Alice");
        graph.addEdge("Alice", "Bob");
        graph.addEdge("Bob", "Den");
        graph.addEdge("Bob", "Charlie");
        graph.addEdge("Den", "Eagle");

        log.debug("Graph: {}", graph);

        graph.getPath("Alice", "Eagle");
    }

    @Test
    public void testDirectedGraphGetPath() {
        Graph<String> graph = GraphBuilder.<String>create().directed().build();
        graph.addVertex("Alice");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Den", "Bob");
        graph.addEdge("Bob", "Charlie");
        graph.addEdge("Eagle", "Den");
        graph.addEdge("Alice", "Eagle");

        log.debug("Graph: {}", graph);

        graph.getPath("Den", "Eagle");
    }
}
