package org.vsubbotin.natera.graph.impl;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.vsubbotin.natera.graph.Edge;
import org.vsubbotin.natera.graph.Graph;

import java.util.*;

@Slf4j
@ToString
public class SimpleGraph<V> implements Graph<V> {
    private boolean isDirected;
    private Map<V, List<V>> data = new HashMap<>();

    SimpleGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    @Override
    public void addVertex(V vertex) {
        if (!data.containsKey(vertex)) {
            data.put(vertex, new ArrayList<>());
        }
    }

    @Override
    public void addEdge(V vertexA, V vertexB) {
        if (data.containsKey(vertexA)) {
            data.get(vertexA).add(vertexB);
        } else {
            data.put(vertexA, new ArrayList<V>(){{
                add(vertexB);
            }});
        }

        if (!data.containsKey(vertexB)) {
            data.put(vertexB, new ArrayList<>());
        }

        if (!isDirected) {
            data.get(vertexB).add(vertexA);
        }
    }

    @Override
    public List<Edge<V>> getPath(V vertexA, V vertexB) {
        List<Edge<V>> result = new ArrayList<>();

        Stack<V> path = new Stack<>();
        path.add(vertexA);

        doFindPath(vertexA, vertexB, path, result);

        log.debug("Result: {}", result);
        return result;
    }

    @Override
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override
    public List<V> getAdjacentNodes(V vertex) {
        return data.get(vertex);
    }

    private void doFindPath(V vertexA, V vertexB, Stack<V> path, List<Edge<V>> edges) {
        if (vertexA.equals(vertexB)) {
            log.debug("Path: {}", path);
            if (path.size() > 1) {
                for (int i = 0; i < path.size() - 1; i++) {
                    Edge<V> edge = new Edge<>(path.get(i), path.get(i + 1));
                    edges.add(edge);
                }
            }
            return;
        }

        for (V current: data.get(vertexA)) {
            if (edges.isEmpty() && !path.contains(current)) {
                log.debug("Next node: {}", current);
                path.push(current);

                doFindPath(current, vertexB, path, edges);

                log.debug("Remove {} from path", current);
                path.pop();
            }
        }
    }
}
