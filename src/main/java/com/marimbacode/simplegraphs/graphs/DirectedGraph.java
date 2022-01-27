package com.marimbacode.simplegraphs.graphs;

import com.marimbacode.simplegraphs.util.DirectedPair;
import com.marimbacode.simplegraphs.graph_nodes.GraphNode;
import com.marimbacode.simplegraphs.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class DirectedGraph<A> extends Graph<A> {
	
	public DirectedGraph(){
		factory = new DirectedGraphNodeFactory<>();
	}
	
	/**Adds an edge from vertex a to b, adding them if they do not exist
	 *
	 * @param a id for vertex a
	 * @param b id for vertex b
	 */
	@Override
	public void addEdge(A a, A b){
		GraphNode<A> na;
		addVertex(a);
		addVertex(b);
		na = getNode(a);
		
		na.addConnection(b);
	}
	
	/**Gets all the edges in the graph
	 *
	 * @return An iterable of all the edges in the graph
	 */
	@Override
	public Iterable<Pair<A>> getEdges(){
		Set<Pair<A>> edges = new HashSet<>();
		for(A a: getVertices()){
			for(A b : getAdjacent(a)){
				edges.add(new DirectedPair<>(a, b));
			}
		}
		return edges;
	}
	
	public static class DirectedGraphNodeFactory<A> extends DefaultGraphNodeFactory<A>{
		
		@Override
		public Pair<A> newPair(A a, A b) {
			return new DirectedPair<>(a, b);
		}
	}
}
