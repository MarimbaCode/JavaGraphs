package com.marimbacode.simplegraphs.graphs;

import com.marimbacode.simplegraphs.graph_nodes.GraphNode;
import com.marimbacode.simplegraphs.graph_nodes.GraphNodeFactory;
import com.marimbacode.simplegraphs.util.GraphObserver;
import com.marimbacode.simplegraphs.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**A base class to handle basic graph functions
 *
 * @param <A> The identifier for each vertex in the graph
 */
public class Graph<A>{
	
	/** A map to connect identifier to nodes */
	private final Map<A, GraphNode<A>> nodes = new HashMap<>();
	
	/** The factory used to generate new vertices */
	protected GraphNodeFactory<A> factory = new DefaultGraphNodeFactory<>();
	
	private final Set<GraphObserver> graphObservers = new HashSet<>();
	
	/**Adds a new vertex to the graph if it does not exist
	 *
	 * @param id The identifier for this vertex
	 */
	public void addVertex(A id) {
		if(nodes.get(id) == null) {
			GraphNode<A> node = factory.newNode();
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
		addVertex(a);
		addVertex(b);
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
	
	/**Gets all adjacent vertices to the specified vertex
	 *
	 * @param id The vertex to get adjacency
	 * @return A set of all identifiers for adjacent vertices
	 */
	public Set<A> getAdjacent(A id){
		return getNode(id).adjacency.keySet();
	}
	
	/**Creates and returns a new pair object for the graph
	 *
	 * @param a The first value in the pair
	 * @param b The second value in the pair
	 * @return A new pair of (A,B)
	 */
	public Pair<A> getPair(A a, A b){
		return factory.newPair(a, b);
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
	public Iterable<Pair<A>> getEdges(){
		Set<Pair<A>> edges = new HashSet<>();
		
		Set<A> visited = new HashSet<>();
		for(GraphNode<A> n: nodes.values()){
			for(A a: n.adjacency.keySet()){
				if(!visited.contains(a)){
					edges.add(new Pair<A>(n.id, a));
				}
			}
			visited.add(n.id);
		}
		return edges;
	}
	
	/**
	 * Notifies observer when the graph structure changes
	 */
	private void notifyChanges(){
		graphObservers.forEach(GraphObserver::onChange);
	}
	
	/**Registers a new observer for the graph
	 *
	 * @param g The observer to register
	 */
	public void registerObserver(GraphObserver g){
		graphObservers.add(g);
	}
	
	/**Unregisters an observer for the graph
	 *
	 * @param g The observer to remove
	 */
	public void unregisterObserver(GraphObserver g){
		graphObservers.remove(g);
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
	
	protected static class DefaultGraphNodeFactory<A> implements GraphNodeFactory<A>{
		@Override
		public GraphNode<A> newNode() {
			return new GraphNode<>();
		}
		
		@Override
		public Pair<A> newPair(A a, A b) {
			return new Pair<>(a, b);
		}
	}
}
