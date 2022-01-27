package com.marimbacode.simplegraphs.graph_nodes;

import com.marimbacode.simplegraphs.util.Pair;

public interface GraphNodeFactory<A> {
    GraphNode<A> newNode();
    Pair<A> newPair(A a, A b);
}
