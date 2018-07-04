import java.util.PriorityQueue;

public class Solution {
	public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
		PriorityQueue<Project> remainProjects = new PriorityQueue<Project>(
				(p1, p2) -> (p1.capital != p2.capital) ? (p1.capital - p2.capital) : (p2.profit - p1.profit));
		for (int i = 0; i < Profits.length; i++) {
			remainProjects.add(new Project(Profits[i], Capital[i]));
		}

		PriorityQueue<Project> readyProjects = new PriorityQueue<Project>((p1, p2) -> p2.profit - p1.profit);

		int maxCapital = W;
		for (int i = 0; i < Math.min(k, Profits.length); i++) {
			while (!remainProjects.isEmpty() && remainProjects.peek().capital <= maxCapital) {
				readyProjects.add(remainProjects.poll());
			}

			if (readyProjects.isEmpty()) {
				break;
			}

			maxCapital += readyProjects.poll().profit;
		}
		return maxCapital;
	}
}

class Project {
	int profit;
	int capital;

	Project(int profit, int capital) {
		this.profit = profit;
		this.capital = capital;
	}
}