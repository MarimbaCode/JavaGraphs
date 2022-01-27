package com.marimbacode.simplegraphs.pathfinding;

import com.marimbacode.simplegraphs.graphs.Graph;
import com.marimbacode.simplegraphs.graphs.WeightedGraph;

import java.util.*;

public class DijkstraAlgorithm<A> extends Pathfinding<A>{
	
	
	/**
	 * Constructs a new pathfinding object for a specified graph
	 *
	 * @param graph The graph to search
	 */
	public DijkstraAlgorithm(Graph<A> graph) {
		super(graph);
	}
	
	@Override
	public void search(A start, A end) {
		
		List<A> finalPath = new ArrayList<>();
		HashMap<A, Double> visited = new HashMap<>();
		HashMap<A, A> path = new HashMap<>();
		if(graph instanceof WeightedGraph<A> graph) {
			
			PriorityQueue<PathPoint<A>> q = new PriorityQueue<>();
			
			visited.put(start, 0.0);
			for(A adj : graph.getAdjacent(start)){
				Double w = graph.getLowestWeight(start, adj);
				q.add(new PathPoint<>(adj, w));
				visited.put(adj, w);
				path.put(adj, start);
			}
			
			while (!q.isEmpty()){
				PathPoint<A> point = q.poll();
				A curr = point.a;
				if(curr.equals(end)){
					break;
				}
				
				for(A adj : graph.getAdjacent(curr)){
					if(visited.containsKey(adj)){
						continue;
					}
					Double w = visited.get(curr) + (graph.getLowestWeight(curr, adj));
					visited.put(adj, w);
					path.put(adj, curr);
					q.add(new PathPoint<>(adj, w));
				}
			}
		}
		A current = end;
		while(!current.equals(start)){
			finalPath.add(current);
			current = path.get(current);
			if(current == null){
				finalPath.clear();
				break;
			}
		}
		if(!finalPath.isEmpty()) {
			finalPath.add(start);
		}
		Collections.reverse(finalPath);
		addPath(graph.getPair(start, end), finalPath);
	}
	
	private record PathPoint<A>(A a, Double len) implements Comparable<PathPoint<A>>{
		@Override
		public int compareTo(PathPoint<A> o) {
			return len.compareTo(o.len);
		}
	}
}
