package com.marimbacode.graph;

public class Edge<A> {
	
	public final A a, b;
	public Edge(A a, A b){
		this.a = a;
		this.b = b;
	}
	
	/**Checks if the values for a and b are the same (order does not matter)
	 *
	 * @param o The other object to check against
	 * @return If the two objects are equal
	 */
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (this == o) {
			result = true;
		} else if (o instanceof Edge other) {
			if (a == other.a) {
				result = b == other.b;
			} else if (a == other.b) {
				result = b == other.a;
			}
		}
		return result;
	}
	
}
