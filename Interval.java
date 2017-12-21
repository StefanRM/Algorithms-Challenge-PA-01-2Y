/**
 * 
 * @author Maftei Stefan - Radu
 *
 *         This class represents an interval of integer numbers. (e. g. [left,
 *         right])
 */
public class Interval implements Comparable<Interval> {
	public int left; // left or start point of the interval
	public int right; // right or end point of the interval

	/**
	 * Class constructor which have both members as parameters.
	 */
	public Interval(int left, int right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * This function is used for sorting by left member of class.
	 */
	@Override
	public int compareTo(Interval o) {
		return this.left - o.left;
	}
}