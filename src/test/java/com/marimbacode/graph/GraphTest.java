package com.marimbacode.graph;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
	
	@Test
	public final void test0(){
		
		Graph<String> g = new Graph<>();
		
		g.addVertex("A");
		
		assertEquals(1, g.getVertexCount());
		assertEquals(0, g.getEdgeCount());
		
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "A");
		
		assertEquals(3, g.getVertexCount());
		assertEquals(3, g.getEdgeCount());
		
		SortedSet<String> vertices = new TreeSet<>();
		for(String s: g.getVertices()){
			vertices.add(s);
		}
		
		assertArrayEquals(
				new String[]{"A","B","C"},
				vertices.toArray(String[]::new)
		);
		
		List<Edge<String>> edges = new ArrayList<>();
		edges.add(new Edge<>("A", "B"));
		edges.add(new Edge<>("B", "C"));
		edges.add(new Edge<>("A", "C"));
		
		for (Edge<String> d : g.getEdges()) {
		    assertTrue(edges.contains(d));
		}
		
	}
	
	@Test
	public final void test1(){
		
		Graph<String> g = new Graph<>();
		
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "A");
		g.addEdge("A", "D");
		g.addEdge("C", "D");
		
		assertEquals(4, g.getVertexCount());
		assertEquals(5, g.getEdgeCount());
		
		SortedSet<String> vertices = new TreeSet<>();
		for(String s: g.getVertices()){
			vertices.add(s);
		}
		
		assertArrayEquals(
				new String[]{"A","B","C","D"},
				vertices.toArray(String[]::new)
		);
		
		List<Edge<String>> edges = new ArrayList<>();
		edges.add(new Edge<>("A", "B"));
		edges.add(new Edge<>("B", "C"));
		edges.add(new Edge<>("A", "C"));
		edges.add(new Edge<>("A", "D"));
		edges.add(new Edge<>("D", "C"));
		
		for (Edge<String> d : g.getEdges()) {
			assertTrue(edges.contains(d));
		}
	}
}
