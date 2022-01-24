package com.marimbacode.graph;

import java.util.HashSet;
import java.util.Set;

public class DirectedGraph<A> extends Graph<A>{
	
	/**Adds an edge from vertex a to b, adding them if they do not exist
	 *
	 * @param a id for vertex a
	 * @param b id for vertex b
	 */
	@Override
	public void addEdge(A a, A b){
		GraphNode<A> na;
		na = getNode(a);
		
		na.addConnection(b);
	}
	
	/**Gets all the edges in the graph
	 *
	 * @return An iterable of all the edges in the graph
	 */
	@Override
	public Iterable<Edge<A>> getEdges(){
		Set<Edge<A>> edges = new HashSet<>();
		for(A a: getVertices()){
			for(A b : getAdjacent(a)){
				edges.add(new DirectedEdge<>(a, b));
			}
		}
		return edges;
	}
}
