package org.vsubbotin.natera.graph.impl;

import org.vsubbotin.natera.graph.Graph;

public class GraphBuilder<V> {
    private boolean isDirected = false;

    private GraphBuilder() {}

    public static <V> GraphBuilder<V> create() {
        return new GraphBuilder<>();
    }

    public GraphBuilder<V> directed() {
        this.isDirected = true;
        return this;
    }

    public GraphBuilder<V> undirected() {
        this.isDirected = false;
        return this;
    }

    public Graph<V> build() {
        return new SimpleGraph<>(this.isDirected);
    }
}
