package com.marimbacode.simplegraphs.graph_nodes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeightedNode<A> extends GraphNode<A> {
	
	private final Map<A, Map<Integer, Double>> weights;
	
	public WeightedNode()
	{
		adjacency = new HashMap<>();
		weights = new HashMap<>();
	}
	
	/**Connects this vertex to another
	 *
	 * @param oId The id of the other vertex
	 */
	public void setWeight(A oId, int edge, double weight) {
		Integer connCount = adjacency.get(oId);
		if (connCount == null) {
			connCount = 0;
		}
		weights.computeIfAbsent(oId, k -> new HashMap<>());
		adjacency.put(oId, ++connCount);
		weights.get(oId).put(edge, weight);
		
	}
	/**Connects this vertex to another
	 *
	 * @param oId The id of the other vertex
	 */
	public void addConnection(A oId, double weight) {
		Integer connCount = adjacency.get(oId);
		if (connCount == null) {
			connCount = 0;
		}
		weights.computeIfAbsent(oId, k -> new HashMap<>());
		adjacency.put(oId, ++connCount);
		weights.get(oId).put(weights.size(), weight);
		
	}
	
	public Double getWeight(A oId, int edge){
		return weights.get(oId).get(edge);
	}
	
	public Double getLowestWeight(A oId){
		Set<Integer> weight = weights.get(oId).keySet();
		if(weight.size() == 0){
			return null;
		}
		Double lowest = Double.MIN_VALUE;
		for(Integer b: weight){
			lowest = weights.get(oId).get(b).compareTo(lowest) < 0 ? weights.get(oId).get(b) : lowest;
		}
		return lowest;
	}
}
