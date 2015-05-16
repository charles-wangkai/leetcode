import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseSchedule_II {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		Course[] courses = new Course[numCourses];
		for (int i = 0; i < courses.length; i++) {
			courses[i] = new Course();
		}

		for (int[] prerequisite : prerequisites) {
			int to = prerequisite[0];
			int from = prerequisite[1];
			courses[from].tos.add(to);
			courses[to].froms.add(from);
		}

		List<Integer> orderList = new ArrayList<Integer>();
		boolean[] takens = new boolean[courses.length];
		for (int i = 0; i < courses.length; i++) {
			if (!takens[i] && courses[i].froms.isEmpty()) {
				take(courses, takens, orderList, i);
			}
		}

		if (orderList.size() < numCourses) {
			return new int[0];
		}

		int[] ordering = new int[numCourses];
		for (int i = 0; i < ordering.length; i++) {
			ordering[i] = orderList.get(i);
		}
		return ordering;
	}

	void take(Course[] courses, boolean[] takens, List<Integer> orderList,
			int index) {
		takens[index] = true;
		orderList.add(index);
		for (int to : courses[index].tos) {
			courses[to].froms.remove(index);
			if (!takens[to] && courses[to].froms.isEmpty()) {
				take(courses, takens, orderList, to);
			}
		}
	}
}

class Course {
	Set<Integer> froms = new HashSet<Integer>();
	Set<Integer> tos = new HashSet<Integer>();
}