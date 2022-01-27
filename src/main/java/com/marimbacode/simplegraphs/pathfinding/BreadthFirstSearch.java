package com.marimbacode.simplegraphs.pathfinding;

import com.marimbacode.simplegraphs.graphs.Graph;

import java.util.*;

/**A class used to apply breadth first search to a graph
 *
 * @param <A> The identifier used in a graph
 */
public class BreadthFirstSearch<A> extends Pathfinding<A> {
	
	public BreadthFirstSearch(Graph<A> graph) {
		super(graph);
	}
	
	@Override
	public void search(A start, A end) {
		
		HashMap<A, A> path = new HashMap<>();
		HashSet<A> visited = new HashSet<>();
		Queue<A> checkNext = new LinkedList<>();
		checkNext.add(start);
		
		while(!checkNext.isEmpty()){
			
			A a = checkNext.poll();
			for (A b : graph.getAdjacent(a)) {
				
			    if(b.equals(end)){
					path.put(b, a);
					checkNext.clear();
					break;
				}
				if(!visited.contains(b)){
					path.put(b, a);
					visited.add(b);
					checkNext.offer(b);
				}
			}
		}
		List<A> finalPath = new ArrayList<>();
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
}
