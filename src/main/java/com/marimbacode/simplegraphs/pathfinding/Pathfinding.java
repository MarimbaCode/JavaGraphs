package com.marimbacode.simplegraphs.pathfinding;

import com.marimbacode.simplegraphs.graphs.Graph;
import com.marimbacode.simplegraphs.util.GraphObserver;
import com.marimbacode.simplegraphs.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class to handle pathfinding behaviours
 *
 * @param <A> The type of the identifier in the graph
 */
public abstract class Pathfinding<A> implements GraphObserver {

	
	private final Map<Pair<A>, List<A>> paths;
	
	/**
	 * The graph to search
	 */
	public final Graph<A> graph;
	
	/**
	 * Constructs a new pathfinding object for a specified graph
	 *
	 * @param graph The graph to search
	 */
	public Pathfinding(Graph<A> graph) {
		this.graph = graph;
		paths = new HashMap<>();
		graph.registerObserver(this);
	}
	
	/**Adds and saves the path calculated
	 *
	 * @param travel The pair of vertices that indicate the start and end
	 * @param path The path taken
	 */
	protected void addPath(Pair<A> travel, List<A> path) {
		paths.put(travel, path);
	}
	
	/**
	 * Searches the graph
	 *
	 * @param start Starting point of the path
	 * @param end   Ending point of the path
	 * @return A list of vertices to get from start to end inclusive
	 */
	public List<A> pathFind(A start, A end) {
		if (!paths.containsKey(new Pair<>(start, end))) {
			search(start, end);
		}
		return paths.get(new Pair<>(start, end));
	}
	
	protected abstract void search(A start, A end);
	
	@Override
	public void onChange() {
		paths.clear();
	}
}
