package com.marimbacode.graph;

import java.util.List;

/**Base class to handle pathfinding behaviours
 *
 * @param <A> The type of the identifier in the graph
 */
public abstract class Pathfinding<A> {
	
	/**The graph to search
	 *
	 */
	protected Graph<A> graph;
	
	/**Constructs a new pathfinding object for a specified graph
	 *
	 * @param graph The graph to search
	 */
	public Pathfinding(Graph<A> graph){
		this.graph = graph;
	}
	
	/**Searches the graph
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public abstract List<A> pathFind(A start, A end);
	
}
