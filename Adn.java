import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Maftei Stefan - Radu
 *
 *         Class used for solving second problem: "Curiosity is in our dna" .
 *
 */
public class Adn {

	public static void main(String[] args) throws IOException {
		// reading from file
		MyScanner br = new MyScanner("adn.in");
		PrintWriter wr = new PrintWriter("adn.out"); // file writer
		String[] dnaSamples = new String[4]; // array for given sequences
		String dnaMars; // target string to check
		for (int i = 0; i < 4; ++i) { // initialize the array
			dnaSamples[i] = "";
		}

		// T - number of tests, N - number of given sequences
		int T = br.nextInt();
		int N;

		// solve each test
		for (int i = 0; i < T; ++i) {
			N = br.nextInt();

			for (int j = 0; j < N; ++j) {
				dnaSamples[j] = br.next();
			}
			dnaMars = br.next();

			// write solution
			wr.write(dnaCheck(dnaSamples[0].toCharArray(),
					dnaSamples[1].toCharArray(), dnaSamples[2].toCharArray(),
					dnaSamples[3].toCharArray(), dnaMars.toCharArray()) + "\n");

			for (int j = 0; j < N; ++j) { // reinitialize the array
				dnaSamples[j] = "";
			}
		}

		br.close();
		wr.close();
		// end of reading, writing
	}

	/**
	 * This function uses Dynamic Programming to solve the problem. Time
	 * Complexity: O(m * n * p * q), m, n, p, q -lengths of sequences (samples)
	 * O(n ^ 4), if the lengths of samples are equal
	 * 
	 * @param sample1
	 *            First sample for checking
	 * @param sample2
	 *            Second sample for checking
	 * @param sample3
	 *            Third sample for checking
	 * @param sample4
	 *            Fourth sample for checking
	 * @param dnaToCheck
	 *            Target string -> the dna for checking
	 * @return 0, if dnaToCheck is formed by the given samples, otherwise 1
	 */
	public static int dnaCheck(char[] sample1, char[] sample2, char[] sample3,
			char[] sample4, char[] dnaToCheck) {

		// first check if the given dna has the length equal to sum of samples'
		// length, because otherwise it can't be formed
		if (dnaToCheck.length != sample1.length + sample2.length
				+ sample3.length + sample4.length) {
			return 0;
		}

		// dp - dynamic programming table
		// it has an additional row because we also count the NULL string
		// last element of the table will have the answer
		boolean[][][][] dp = new boolean[sample1.length + 1][sample2.length
				+ 1][sample3.length + 1][sample4.length + 1];

		dp[0][0][0][0] = true; // all NULLs ("") are part of the dnaToCheck

		// verifying each character from each sample to dnaToCheck
		// using the previous results
		for (int i = 0; i < sample1.length + 1; ++i) {
			for (int j = 0; j < sample2.length + 1; ++j) {
				for (int k = 0; k < sample3.length + 1; ++k) {
					for (int l = 0; l < sample4.length + 1; ++l) {
						// we want to check the characters in each sample having
						// in mind
						// that we already treated the first cell and that now
						// we want to check the characters different from NULL

						if (l > 0) { // other character than NULL in table
							// checking character from sample 4
							dp[i][j][k][l] =
									dp[i][j][k][l] || (dp[i][j][k][l - 1]
											&& (sample4[l - 1] == dnaToCheck[i
													+ j + k + l - 1]));
						}
						if (k > 0) { // other character than NULL in table
							// checking character from sample 3
							dp[i][j][k][l] =
									dp[i][j][k][l] || (dp[i][j][k - 1][l]
											&& (sample3[k - 1] == dnaToCheck[i
													+ j + k + l - 1]));
						}
						if (j > 0) { // other character than NULL in table
							// checking character from sample 2
							dp[i][j][k][l] =
									dp[i][j][k][l] || (dp[i][j - 1][k][l]
											&& (sample2[j - 1] == dnaToCheck[i
													+ j + k + l - 1]));
						}
						if (i > 0) { // other character than NULL in table
							// checking character from sample 1
							dp[i][j][k][l] =
									dp[i][j][k][l] || (dp[i - 1][j][k][l]
											&& (sample1[i - 1] == dnaToCheck[i
													+ j + k + l - 1]));
						}
					}
				}
			}
		}

		// if the dna is formed by the samples
		if (dp[sample1.length][sample2.length][sample3.length][sample4.length]) {
			return 1;
		}

		return 0;
	}
}