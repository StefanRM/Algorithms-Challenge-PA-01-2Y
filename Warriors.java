import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Maftei Stefan - Radu
 *
 *         Class used for solving bonus problem: "Warriors" .
 *
 */
public class Warriors {
	/**
	 * This function computes combination of n and k, C(n, k), k < n.
	 * 
	 * @param n
	 *            Total number of items
	 * @param k
	 *            Number of items in a combination
	 * @return The combination computed, C(n, k).
	 */
	public static long combination(long n, long k) {
		long comb = 1;

		// we will use the formula: (n - k + 1) * ... * n / k!
		for (int i = 1; i <= k; i++) {
			comb *= n - i + 1;
			comb /= i;
		}

		return comb;
	}

	/**
	 * This function computes the sum of combinations in the interval (1, n).
	 * 
	 * @param x
	 *            Number of total items
	 * @param n
	 *            The range of sum
	 * @return Sum of the computed combinations
	 */
	public static long sumCombinations(long x, long n) {
		long sum = 0;

		for (int i = 1; i <= n; i++) {
			if (x > i) { // condition for correct combination's computing
				sum += combination(x, i);
			} else {
				break;
			}
		}

		return sum;
	}

	/**
	 * In the maine function we use a formula to solve this problem. Time
	 * Complexity: O(x + log(n)), x - difference from log(n) to returned
	 * solution, n - maximum level.
	 */
	public static void main(String[] args) throws IOException {
		// reading from file
		MyScanner br = new MyScanner("warriors.in");

		long N = br.nextLong(); // maximum level
		long K = br.nextLong(); // number of lives

		br.close();
		// end of reading

		long nrOfTries;

		if (K > 1) {
			// if we have enough amount of lives (more than log(N) to be
			// precise) we can use binary search to solve the problem
			if ((nrOfTries =
					(long) Math.ceil(Math.log10(N) / Math.log10(2))) >= K) {
				// not enough lives case

				nrOfTries = 1; // we start to count the tries

				while (nrOfTries <= N) {
					// if the sum of combinations is greater than maximum level
					if (sumCombinations(nrOfTries, K) >= N) {
						break;
					} else {
						nrOfTries++; // another try
					}

				}

			}
		} else { // if K = 1 then it is only one way to play safe
			nrOfTries = N;
		}

		// writing to file
		PrintWriter wr = new PrintWriter("warriors.out");
		wr.print((int) nrOfTries);
		wr.close();
		// end of writing
	}
}
