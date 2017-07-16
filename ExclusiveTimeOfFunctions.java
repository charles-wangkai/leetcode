import java.util.List;
import java.util.Stack;

public class ExclusiveTimeOfFunctions {
	public int[] exclusiveTime(int n, List<String> logs) {
		int[] times = new int[n];

		int prevTime = -1;
		Stack<Integer> ids = new Stack<Integer>();
		for (String log : logs) {
			String[] fields = log.split(":");
			int id = Integer.parseInt(fields[0]);
			String command = fields[1];
			int timestamp = Integer.parseInt(fields[2]);

			if (command.equals("start")) {
				if (!ids.empty()) {
					times[ids.peek()] += timestamp - prevTime;
				}

				ids.push(id);
				prevTime = timestamp;
			} else {
				times[id] += timestamp + 1 - prevTime;

				ids.pop();
				prevTime = timestamp + 1;
			}
		}

		return times;
	}
}
