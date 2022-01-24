package com.marimbacode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedNode<A, B extends Comparable<B>> extends GraphNode<A>{
	
	public Map<A, List<B>> weights;
	
	public WeightedNode()
	{
		adjacency = new HashMap<>();
		weights = new HashMap<>();
	}
	
	/**Connects this vertex to another
	 *
	 * @param oId The id of the other vertex
	 */
	public void addConnection(A oId, int edge, B weight){
		Integer connCount = adjacency.get(oId);
		if(connCount == null){
			connCount = 0;
		}
		weights.computeIfAbsent(oId, k -> new ArrayList<>());
		adjacency.put(oId, ++connCount);
		weights.get(oId).set(edge, weight);
	}
	
	/**Connects this vertex to another
	 *
	 * @param oId The id of the other vertex
	 */
	public void addConnection(A oId, B weight){
		Integer connCount = adjacency.get(oId);
		if(connCount == null){
			connCount = 0;
		}
		weights.computeIfAbsent(oId, k -> new ArrayList<>());
		adjacency.put(oId, ++connCount);
		weights.get(oId).set(weights.get(oId).size(), weight);
	}
}
