package org.vsubbotin.natera.graph;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Edge<V> {
    private V vertexA;
    private V vertexB;
}
