package com.marimbacode.simplegraphs;

import com.marimbacode.simplegraphs.graphs.DirectedGraph;
import com.marimbacode.simplegraphs.graphs.Graph;
import com.marimbacode.simplegraphs.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectedGraphTest {
	
	@Test
	public final void test0(){
		
		Graph<String> g = new DirectedGraph<>();
		
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
		
		List<Pair<String>> edges = new ArrayList<>();
		edges.add(new Pair<>("A", "B"));
		edges.add(new Pair<>("B", "C"));
		edges.add(new Pair<>("A", "C"));
		
		for (Pair<String> d : g.getEdges()) {
			assertTrue(edges.contains(d));
		}
		
	}
	
	@Test
	public final void test1(){
		
		Graph<String> g = new DirectedGraph<>();
		
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "A");
		g.addEdge("A", "C");
		g.addEdge("A", "D");
		g.addEdge("C", "D");
		
		assertEquals(4, g.getVertexCount());
		assertEquals(6, g.getEdgeCount());
		
		SortedSet<String> vertices = new TreeSet<>();
		for(String s: g.getVertices()){
			vertices.add(s);
		}
		
		assertArrayEquals(
				new String[]{"A","B","C","D"},
				vertices.toArray(String[]::new)
		);
		
		List<Pair<String>> edges = new ArrayList<>();
		edges.add(new Pair<>("A", "B"));
		edges.add(new Pair<>("B", "C"));
		edges.add(new Pair<>("A", "C"));
		edges.add(new Pair<>("C", "A"));
		edges.add(new Pair<>("A", "D"));
		edges.add(new Pair<>("D", "C"));
		
		for (Pair<String> d : g.getEdges()) {
			assertTrue(edges.contains(d));
		}
		assertArrayEquals(new String[]{"C"},g.getAdjacent("B").toArray(String[]::new));
	}
}
