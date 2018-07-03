import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	public int scheduleCourse(int[][] courses) {
		Arrays.sort(courses, (course1, course2) -> course1[1] - course2[1]);

		PriorityQueue<Integer> times = new PriorityQueue<Integer>((time1, time2) -> time2 - time1);
		int start = 0;
		for (int[] course : courses) {
			start += course[0];
			times.offer(course[0]);

			while (start > course[1]) {
				start -= times.poll();
			}
		}
		return times.size();
	}
}
