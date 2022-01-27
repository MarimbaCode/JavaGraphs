package com.marimbacode.simplegraphs.graphs;

import com.marimbacode.simplegraphs.graph_nodes.GraphNode;
import com.marimbacode.simplegraphs.graph_nodes.WeightedNode;

public class WeightedGraph<A> extends Graph<A> {
	
	public WeightedGraph(){
		factory = new WeightedNodeFactory<A>();
	}
	
	/**Adds an edge between two vertices, adding them if they do not exist
	 *
	 * @param a id for vertex a
	 * @param b id for vertex b
	 * @param edge The edge number to set
	 * @param weight The weight of the edge
	 */
	public void addEdge(A a, A b, int edge, double weight){
		WeightedNode<A> na, nb;
		addVertex(a);
		addVertex(b);
		na = (WeightedNode<A>) getNode(a);
		nb = (WeightedNode<A>) getNode(b);
		
		na.setWeight(b, edge, weight);
		nb.setWeight(a, edge, weight);
	}
	
	/**Adds an edge between two vertices, adding them if they do not exist
	 *
	 * @param a id for vertex a
	 * @param b id for vertex b
	 * @param weight The weight of the edge
	 */
	public void addEdge(A a, A b, double weight){
		WeightedNode<A> na, nb;
		addVertex(a);
		addVertex(b);
		na = (WeightedNode<A>) getNode(a);
		nb = (WeightedNode<A>) getNode(b);
		
		na.addConnection(b, weight);
		nb.addConnection(a, weight);
	}
	
	public Double getWeight(A a, A b, int edge){
		WeightedNode<A> vertex = (WeightedNode<A>) getNode(a);
		return vertex.getWeight(b, edge);
	}
	public Double getLowestWeight(A a, A b){
		WeightedNode<A> vertex = (WeightedNode<A>) getNode(a);
		return vertex.getLowestWeight(b);
	}

	
	private static class WeightedNodeFactory<A> extends DefaultGraphNodeFactory<A>{
		
		@Override
		public GraphNode<A> newNode() {
			return new WeightedNode<A>();
		}
		
	}
}
