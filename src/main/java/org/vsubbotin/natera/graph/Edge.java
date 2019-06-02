package org.vsubbotin.natera.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge<V> {
    private V vertexA;
    private V vertexB;
}
