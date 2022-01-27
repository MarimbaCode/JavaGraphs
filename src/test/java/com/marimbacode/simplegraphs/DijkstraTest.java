package com.marimbacode.simplegraphs;

import com.marimbacode.simplegraphs.graphs.WeightedGraph;
import com.marimbacode.simplegraphs.pathfinding.DijkstraAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DijkstraTest {
	
	@Test
	public final void test0(){
		
		WeightedGraph<Integer> g = new WeightedGraph<>();
		
		g.addEdge(0, 1, 0, 3.0);
		g.addEdge(0, 2, 0, 6.0);
		g.addEdge(1, 3, 0, 9.0);
		g.addEdge(2, 3, 0, 3.0);
		
		DijkstraAlgorithm<Integer> pathfind = new DijkstraAlgorithm<>(g);
		
		System.out.println(Arrays.deepToString(pathfind.pathFind(0,3).toArray()));
		
	}
}
