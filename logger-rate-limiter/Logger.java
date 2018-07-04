import java.util.HashMap;
import java.util.Map;

public class Logger {
	private final int MIN_ALLOWED_INTERVAL = 10;

	private Map<String, Integer> message2lastTimestamp;

	/** Initialize your data structure here. */
	public Logger() {
		message2lastTimestamp = new HashMap<String, Integer>();
	}

	/**
	 * Returns true if the message should be printed in the given timestamp,
	 * otherwise returns false. The timestamp is in seconds granularity.
	 */
	public boolean shouldPrintMessage(int timestamp, String message) {
		if (message2lastTimestamp.containsKey(message)
				&& timestamp - message2lastTimestamp.get(message) < MIN_ALLOWED_INTERVAL) {
			return false;
		}

		message2lastTimestamp.put(message, timestamp);
		return true;
	}
}

/**
 * Your Logger object will be instantiated and called as such: Logger obj = new
 * Logger(); boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */