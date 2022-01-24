package com.marimbacode.graph;

import java.util.HashMap;
import java.util.Map;

/**A basic vertex for graphs
 *
 * @param <A> The identifier type of the graph
 */
public class GraphNode<A> {
    
    /** The graph this vertex is attached to */
    private Graph<A> graph;
    
    /** The identifier for the vertex */
    public A id;
    
    /** A map of adjacency to connection count */
    public Map<A, Integer> adjacency;
    
    
    public GraphNode()
    {
        adjacency = new HashMap<>();
    }
    
    /**Connects this vertex to another
     *
     * @param oId The id of the other vertex
     */
    public void addConnection(A oId){
        Integer connCount = adjacency.get(oId);
        if(connCount == null){
            connCount = 0;
        }
        adjacency.put(oId, ++connCount);
    }
    
    /**Sets the graph this vertex is in
     *
     * @param g The graph this vertex is in
     */
    public void setGraph(Graph<A> g){
        graph = g;
    }
    
    
}