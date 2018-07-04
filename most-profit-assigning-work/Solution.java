import java.util.Arrays;

public class Solution {
	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		Job[] jobs = new Job[difficulty.length];
		for (int i = 0; i < jobs.length; i++) {
			jobs[i] = new Job(difficulty[i], profit[i]);
		}

		Arrays.sort(jobs,
				(job1, job2) -> (job1.difficulty == job2.difficulty) ? Integer.compare(job2.profit, job1.profit)
						: Integer.compare(job1.difficulty, job2.difficulty));

		int[] maxProfits = new int[jobs.length];
		int maxProfit = -1;
		for (int i = 0; i < maxProfits.length; i++) {
			maxProfit = Math.max(maxProfit, jobs[i].profit);
			maxProfits[i] = maxProfit;
		}

		int result = 0;
		for (int difficultLimit : worker) {
			result += findMaxProfit(jobs, maxProfits, difficultLimit);
		}
		return result;
	}

	int findMaxProfit(Job[] jobs, int[] maxProfits, int difficultLimit) {
		int result = 0;
		int lower = 0;
		int upper = jobs.length - 1;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			if (jobs[middle].difficulty <= difficultLimit) {
				result = maxProfits[middle];

				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}
		return result;
	}
}

class Job {
	int difficulty;
	int profit;

	Job(int difficulty, int profit) {
		this.difficulty = difficulty;
		this.profit = profit;
	}
}