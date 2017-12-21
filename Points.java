import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Maftei Stefan - Radu
 *
 *         Class used for solving first problem: "My Points" .
 *
 */
class Points {
	public int[] points; // array for storing given points

	public static void main(String[] args) throws IOException {
		// reading from file
		MyScanner br = new MyScanner("points.in");

		// n points; m intervals
		int n = br.nextInt(), m = br.nextInt();
		Points pts = new Points();

		int i = 0;
		pts.points = new int[n]; // points array
		for (i = 0; i < n; ++i) {
			pts.points[i] = br.nextInt();
		}

		// list of intervals
		List<Interval> intervals = new ArrayList<Interval>();
		for (i = 0; i < m; ++i) {
			intervals.add(new Interval(br.nextInt(), br.nextInt()));
		}

		br.close();
		// end of reading from file

		// sorting the intervals by left member of Interval class
		Collections.sort(intervals);

		// writing to file
		PrintWriter writer = new PrintWriter("points.out");
		writer.print(pts.minNumberOfIntervals(intervals));
		writer.close();
		// end of writing to file
	}

	/**
	 * This function implements the Greedy Algorithm to solve the first problem.
	 * Time Complexity: O(m * n), m - number of intervals, n - number of points
	 * 
	 * @param intervals
	 *            Given intervals, sorted by left member
	 * @return Minimum number of intervals to cover all given points
	 * 
	 */
	public int minNumberOfIntervals(List<Interval> intervals) {
		// num - min number of intervals;
		// max - maximum number of points covered by current interval;
		// i, j - indexes of points array;
		int num = 0, max = 0, i = 0, j;
		for (Interval interval : intervals) {
			// if current interval doesn't cover current point
			if (interval.left > points[i]) {
				// we have found an interval to cover some uncovered points
				i += max; // we step over the already covered points
				if (i >= points.length) { // if all points covered
					++num; // increase number of found intervals
					max = 0; // there is no point to cover anymore
					break;
				}

				max = 0; // there is no point covered yet
				++num; // increase number of found intervals
			}

			j = i; // current position in points array

			// find the position of point the current interval won't cover
			while (j < points.length && interval.right >= points[j]) {
				++j;
			}

			// if the number of points is greater than the old one
			if (j - i > max) {
				max = j - i; // update the covered points
			}
		}

		if (max > 0) { // one of the last intervals covered the rest points
			num++; // increase number of found intervals
		}

		return num;
	}
}
