import java.util.HashSet;
import java.util.Set;

public class CourseSchedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
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

		boolean[] takens = new boolean[courses.length];
		for (int i = 0; i < courses.length; i++) {
			if (!takens[i] && courses[i].froms.isEmpty()) {
				take(courses, takens, i);
			}
		}

		for (Course course : courses) {
			if (!course.froms.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	void take(Course[] courses, boolean[] takens, int index) {
		takens[index] = true;
		for (int to : courses[index].tos) {
			courses[to].froms.remove(index);
			if (!takens[to] && courses[to].froms.isEmpty()) {
				take(courses, takens, to);
			}
		}
	}
}

class Course {
	Set<Integer> froms = new HashSet<Integer>();
	Set<Integer> tos = new HashSet<Integer>();
}