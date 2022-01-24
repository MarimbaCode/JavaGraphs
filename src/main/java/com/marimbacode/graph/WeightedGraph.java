package com.marimbacode.graph;

public class WeightedGraph<A, B extends Comparable<B>> extends Graph<A>{
	
	public WeightedGraph(){
		factory = new WeightedNodeFactory<A, B>();
	}
	
	/**Adds an edge between two vertices, adding them if they do not exist
	 *
	 * @param a id for vertex a
	 * @param b id for vertex b
	 */
	public void addEdge(A a, A b, int edge, B weight){
		WeightedNode<A, B> na, nb;
		na = (WeightedNode<A, B>) getNode(a);
		nb = (WeightedNode<A, B>) getNode(b);
		
		na.addConnection(b, edge, weight);
		nb.addConnection(a, edge, weight);
	}

	
	private static class WeightedNodeFactory<A, B extends Comparable<B>> implements GraphNodeFactory<A>{
		
		@Override
		public GraphNode<A> newNode() {
			return new WeightedNode<A, B>();
		}
	}
}
