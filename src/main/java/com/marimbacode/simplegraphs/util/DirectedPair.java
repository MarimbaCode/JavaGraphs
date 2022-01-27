package com.marimbacode.simplegraphs.util;

public class DirectedPair<A> extends Pair<A> {
	public DirectedPair(A a, A b) {
		super(a, b);
	}
	/**Checks if the values for a and b are the same (order does matter)
	 *
	 * @param o The other object to check against
	 * @return If the two objects are equal
	 */
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (this == o) {
			result = true;
		} else if (o instanceof Pair other) {
			result = a.equals(other.a) && b.equals(other.b);
		}
		return result;
	}
}
