import java.util.HashMap;
import java.util.Map;

class Logger {
	private static final int MIN_ALLOWED_INTERVAL = 10;

	private Map<String, Integer> messageToLastTimestamp = new HashMap<>();

	/**
	 * Returns true if the message should be printed in the given timestamp,
	 * otherwise returns false. If this method returns false, the message will not
	 * be printed. The timestamp is in seconds granularity.
	 */
	public boolean shouldPrintMessage(int timestamp, String message) {
		if (messageToLastTimestamp.containsKey(message)
				&& timestamp - messageToLastTimestamp.get(message) < MIN_ALLOWED_INTERVAL) {
			return false;
		}

		messageToLastTimestamp.put(message, timestamp);

		return true;
	}
}

// Your Logger object will be instantiated and called as such:
// Logger obj = new Logger();
// boolean param_1 = obj.shouldPrintMessage(timestamp,message);
