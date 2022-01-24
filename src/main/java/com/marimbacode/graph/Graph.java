package com.marimbacode.graph;

import java.util.*;

/**A base class to handle basic graph functions
 *
 * @param <A> The identifier for each vertex in the graph
 */
public class Graph<A>{
	
	/** A map to connect identifier to nodes */
	private final Map<A, GraphNode<A>> nodes = new HashMap<>();
	
	/** The factory used to generate new vertices */
	protected GraphNodeFactory<A> factory = new DefaultGraphNodeFactory<>();
	
	/**Adds a new vertex to the graph if it does not exist
	 *
	 * @param id The identifier for this vertex
	 */
	public void addVertex(A id) {
		if(nodes.get(id) == null) {
			GraphNode<A> node = factory.newNode();
			node.setGraph(this);
			node.id = id;
			nodes.put(id, node);
		}
	}
	
	/**Adds an edge between two vertices, adding them if they do not exist
	 *
	 * @param a id for vertex a
	 * @param b id for vertex b
	 */
	public void addEdge(A a, A b){
		GraphNode<A> na, nb;
		na = getNode(a);
		nb = getNode(b);
		
		na.addConnection(b);
		nb.addConnection(a);
	}
	
	/**Gets the number of vertices in the graph
	 *
	 * @return number of vertices in the graph
	 */
	public int getVertexCount(){
		return nodes.size();
	}
	
	/**Gets the numeber of edges in the graph
	 *
	 * @return number of edges in the graph
	 */
	public int getEdgeCount(){
		
		Set<A> visited = new HashSet<>();
		int edgeCount = 0;
		for(GraphNode<A> n: nodes.values()){
			for(A a: n.adjacency.keySet()){
				if(!visited.contains(a)){
					edgeCount++;
				}
			}
			visited.add(n.id);
		}
		return edgeCount;
	}
	
	/**Gets a specified node from the graph
	 *
	 * @param id the vertex id
	 * @return the Node representing the vertex
	 */
	protected GraphNode<A> getNode(A id){
		return nodes.get(id);
	}
	
	protected Set<A> getAdjacent(A id){
		return getNode(id).adjacency.keySet();
	}
	
	/**Gets all the vertices in the graph
	 *
	 * @return An iterable of all the vertices in the graph
	 */
	public Iterable<A> getVertices(){
		return nodes.keySet();
	}
	
	/**Gets all the edges in the graph
	 *
	 * @return An iterable of all the edges in the graph
	 */
	public Iterable<Edge<A>> getEdges(){
		Set<Edge<A>> edges = new HashSet<>();
		
		Set<A> visited = new HashSet<>();
		for(GraphNode<A> n: nodes.values()){
			for(A a: n.adjacency.keySet()){
				if(!visited.contains(a)){
					edges.add(new Edge<A>(n.id, a));
				}
			}
			visited.add(n.id);
		}
		return edges;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (A a : nodes.keySet()) {
			sb.append(a).append(": [");
			GraphNode<A> node = nodes.get(a);
			for (A b : node.adjacency.keySet()) {
				sb.append(b).append("-").append(node.adjacency.get(b)).append(",");
			}
			sb.append("]\n");
		}
		return sb.toString();
	}
	
	private static class DefaultGraphNodeFactory<A> implements GraphNodeFactory<A>{
		@Override
		public GraphNode<A> newNode() {
			return new GraphNode<>();
		}
	}
}
