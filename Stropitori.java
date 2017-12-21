import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Maftei Stefan - Radu
 *
 *         Class used for solving third problem: "Stropitorile lui Gigel" .
 *
 */
public class Stropitori {
	public static void main(String[] args) throws IOException {
		// reading from file
		MyScanner br = new MyScanner("stropitori.in");
		br.nextLine(); // name of the stadium is irrelevant
		int n = br.nextInt(); // number of stropitori
		final long S = br.nextLong(); // length of the stadium

		// array of positions and powers for stropitori
		long[] poz = new long[n];
		long[] pow = new long[n];
		for (int i = 0; i < n; i++) {
			poz[i] = br.nextInt();
		}
		for (int i = 0; i < n; i++) {
			pow[i] = br.nextInt();
		}

		br.close();
		// end of reading

		// writing to file
		PrintWriter writer = new PrintWriter("stropitori.out");
		writer.print(maxNrStropitori(poz, pow, n, S));
		writer.close();
		// end of writing
	}

	/**
	 * This function uses Greedy Algorithm to solve the problem. Time
	 * Complexity: O(n), n - number of stropitori.
	 * 
	 * @param poz
	 *            Array of positions for stropitori
	 * @param pow
	 *            Array of powers for stropitori
	 * @param n
	 *            Size of arrays
	 * @param S
	 *            Length of stadium
	 * @return Maximum number of stropitori that water at maximum power
	 */
	public static int maxNrStropitori(long[] poz, long[] pow, int n, long S) {
		// left - if the current stropitoare is already used to water left side
		// num - maxmimum number of stropitori
		// i, j - indexes for iterating the arrays
		int left = 0, j, i = 0, num = 0;

		// case first stropitoare
		if (poz[i] - pow[i] >= 0) { // it can water left side
			left = 1;
			num++; // increase maximum number
		}

		// check the rest stropitori, except from the last one
		while (i < n - 1) {
			j = i + 1; // index of the next stropitoare
			if (left == 1) { // current stropitoare waters left side
				// if next stropitoare can water the left side
				if (poz[j] - pow[j] <= poz[i]) { // it cannot
					left = 0;
				} else { // it can
					num++; // increase maximum number
					left = 1;
				}
			} else { // current stropitoare does not water left side
				// if current stropitoare can water the right side
				if (poz[i] + pow[i] < poz[j]) { // it can
					num++; // increase maximum number

					// if next stropitoare can water the left side
					if (poz[j] - pow[j] <= poz[i] + pow[i]) { // it cannot
						left = 0;
					} else { // it can
						num++; // increase maximum number
						left = 1;
					}
				} else { // it cannot
					// if next stropitoare can water the left side
					// current stropitoare is shut down
					if (poz[j] - pow[j] <= poz[i]) { // it cannot
						left = 0;
					} else { // it can
						num++; // increase maximum number
						left = 1;
					}
				}
			}
			i++; // next stropitoare
		}

		// if the last stropitoare doesn't water left side
		if (left == 0) {
			// if the last stropitoare can water right side
			if (poz[i] + pow[i] <= S) {
				num++; // increase maximum number
			}
		}

		return num;
	}
}
