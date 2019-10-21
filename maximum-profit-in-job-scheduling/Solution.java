import java.util.stream.IntStream;

public class Solution {
	public int jobScheduling(int[] startTimes, int[] endTimes, int[] profits) {
		Job[] jobs = IntStream.range(0, startTimes.length)
				.mapToObj(i -> new Job(startTimes[i], endTimes[i], profits[i]))
				.sorted((job1, job2) -> Integer.compare(job1.endTime, job2.endTime)).toArray(Job[]::new);

		int[] maxProfits = new int[jobs.length + 1];
		for (int i = 1; i < maxProfits.length; i++) {
			maxProfits[i] = Math.max(maxProfits[i - 1], jobs[i - 1].profit + maxProfits[findIndex(jobs, i - 1) + 1]);
		}

		return maxProfits[maxProfits.length - 1];
	}

	int findIndex(Job[] jobs, int lastIndex) {
		int result = -1;
		int lowerIndex = 0;
		int upperIndex = lastIndex - 1;
		while (lowerIndex <= upperIndex) {
			int middleIndex = (lowerIndex + upperIndex) / 2;

			if (jobs[middleIndex].endTime <= jobs[lastIndex].startTime) {
				result = middleIndex;

				lowerIndex = middleIndex + 1;
			} else {
				upperIndex = middleIndex - 1;
			}
		}

		return result;
	}
}

class Job {
	int startTime;
	int endTime;
	int profit;

	Job(int startTime, int endTime, int profit) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.profit = profit;
	}
}