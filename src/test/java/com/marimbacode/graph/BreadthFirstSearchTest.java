package com.marimbacode.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BreadthFirstSearchTest {
	
	@Test
	public final void test0(){
		
		Graph<Integer> g = new Graph<>();
		
		for (int i = 0; i < 100; i++) {
			g.addVertex(i);
		}
		for (int i = 0; i < 90; i++) {
			g.addEdge(i, i+10);
			g.addEdge(i%9, (i%9)+1);
		}
		
		Pathfinding<Integer> bfs = new BreadthFirstSearch<>(g);
		List<Integer> path = bfs.pathFind(0, 99);
		assertArrayEquals(new Integer[]{0,1,2,3,4,5,6,7,8,9,19,29,39,49,59,69,79,89,99}, path.toArray(Integer[]::new));
	}
}
