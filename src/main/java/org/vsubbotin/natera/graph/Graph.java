package org.vsubbotin.natera.graph;

import java.util.List;

public interface Graph<V> {
    void addVertex(V vertex);

    void addEdge(V vertexA, V vertexB);

    List<Edge<V>> getPath(V vertexA, V vertexB);

    boolean isDirected();

    List<V> getAdjacent(V vertex);
}
