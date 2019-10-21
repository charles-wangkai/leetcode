public class Solution {
	public double probabilityOfHeads(double[] prob, int target) {
		double[] p = new double[prob.length + 1];
		p[0] = 1;

		for (double coinP : prob) {
			double[] nextP = new double[p.length];
			for (int i = 0; i < nextP.length; i++) {
				nextP[i] = p[i] * (1 - coinP);

				if (i != 0) {
					nextP[i] += p[i - 1] * coinP;
				}
			}

			p = nextP;
		}

		return p[target];
	}
}
