package com.marimbacode.simplegraphs.pathfinding;

import com.marimbacode.simplegraphs.graphs.Graph;
import com.marimbacode.simplegraphs.util.GraphObserver;

import java.util.List;

public class CompletePathfinding<A> implements GraphObserver {
	
	private final Pathfinding<A> pathfinding;
	
	public CompletePathfinding(Pathfinding<A> p){
		pathfinding = p;
		p.graph.registerObserver(this);
	}
	
	public List<A> getPath(A start, A end){
		return pathfinding.pathFind(start, end);
	}
	
	@Override
	public void onChange() {
		
		Graph<A> g = pathfinding.graph;
		for (A a : g.getVertices()) {
		    for (A b : g.getVertices()) {
		        if(!a.equals(b)){
					pathfinding.pathFind(a, b);
				}
		    }
		}
	}
}
