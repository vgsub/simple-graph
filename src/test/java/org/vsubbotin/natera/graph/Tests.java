package org.vsubbotin.natera.graph;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.vsubbotin.natera.graph.impl.GraphBuilder;

import java.util.List;
import java.util.stream.Collectors;

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

        Assert.assertEquals("[Alice, Den, Charlie]", graph.getAdjacentNodes("Bob").toString());
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

        Assert.assertEquals("[Den, Charlie]", graph.getAdjacentNodes("Bob").toString());
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

        List<Edge<String>> edges = graph.getPath("Alice", "Eagle");

        Assert.assertEquals("Bob,Den,Eagle", edges.stream().map(Edge::getVertexB).collect(Collectors.joining(",")));
    }

    @Test
    public void testDirectedGraphGetPath() {
        Graph<String> graph = GraphBuilder.<String>create().directed().build();
        graph.addVertex("Alice");
        graph.addEdge("Den", "Bob");
        graph.addEdge("Bob", "Charlie");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Eagle", "Den");
        graph.addEdge("Alice", "Charlie");
        graph.addEdge("Alice", "Eagle");
        graph.addEdge("Charlie", "Den");

        log.debug("Graph: {}", graph);

        List<Edge<String>> edges = graph.getPath("Den", "Eagle");

        Assert.assertEquals("Bob,Alice,Eagle", edges.stream().map(Edge::getVertexB).collect(Collectors.joining(",")));
    }
}
