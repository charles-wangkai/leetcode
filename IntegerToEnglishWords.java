import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerToEnglishWords {
	static final String[] TRUNK_WORDS = { "", "Thousand", "Million", "Billion" };
	static final String[] TEN_WORDS = { null, null, "Twenty", "Thirty",
			"Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
	static final String[] TEEN_WORDS = { "Ten", "Eleven", "Twelve", "Thirteen",
			"Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
			"Nineteen" };
	static final String[] DIGIT_WORDS = { "Zero", "One", "Two", "Three",
			"Four", "Five", "Six", "Seven", "Eight", "Nine" };

	public String numberToWords(int num) {
		if (num == 0) {
			return DIGIT_WORDS[0];
		}

		List<String> words = new LinkedList<String>();
		int trunkWordIndex = 0;
		while (num != 0) {
			int chunk = num % 1000;
			List<String> translated = translateChunk(chunk);
			if (!translated.isEmpty()) {
				translated.add(TRUNK_WORDS[trunkWordIndex]);
				words.addAll(0, translated);
			}

			num /= 1000;
			trunkWordIndex++;
		}
		return String.join(" ", words.stream().filter(word -> !word.isEmpty())
				.collect(Collectors.toList()));
	}

	List<String> translateChunk(int chunk) {
		List<String> translated = new ArrayList<String>();
		int hundred = chunk / 100;
		if (hundred != 0) {
			translated.add(DIGIT_WORDS[hundred]);
			translated.add("Hundred");
		}
		int ten = chunk % 100 / 10;
		int one = chunk % 10;
		if (ten == 1) {
			translated.add(TEEN_WORDS[one]);
		} else {
			if (ten != 0) {
				translated.add(TEN_WORDS[ten]);
			}
			if (one != 0) {
				translated.add(DIGIT_WORDS[one]);
			}
		}
		return translated;
	}
}
