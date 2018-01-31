import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BasicCalculator_IV {
	public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
		Map<String, Integer> variable2value = new HashMap<String, Integer>();
		for (int i = 0; i < evalvars.length; i++) {
			variable2value.put(evalvars[i], evalints[i]);
		}

		expression = expression.replaceAll("-", "- *");

		Map<String, Term> result = calculate(expression, variable2value);
		return result.values().stream().sorted().map(Term::toString).collect(Collectors.toList());
	}

	Map<String, Term> calculate(String s, Map<String, Integer> variable2value) {
		Map<String, Term> result = Collections.emptyMap();
		Map<String, Term> part = Collections.emptyMap();

		Expression expr = new Expression(s);
		String token;
		while ((token = expr.readNext()) != null) {
			if (token.equals("+") || token.equals("-")) {
				result = add(result, part);

				part = Collections.singletonMap("", new Term(Collections.emptyList(), token.equals("+") ? 1 : -1));
			} else if (token.equals("*")) {
				String t = expr.readNext();
				Map<String, Term> otherPart = readPart(s, t, expr, variable2value);
				part = multiply(part, otherPart);
			} else {
				part = readPart(s, token, expr, variable2value);
			}
		}
		result = add(result, part);

		return result;
	}

	Map<String, Term> multiply(Term term1, Map<String, Term> part2) {
		Map<String, Term> result = new HashMap<String, Term>();
		for (Term term2 : part2.values()) {
			List<String> freeVariables = new ArrayList<String>(term1.freeVariables);
			freeVariables.addAll(term2.freeVariables);
			Collections.sort(freeVariables);

			int coef = term1.coef * term2.coef;

			Term term = new Term(freeVariables, coef);
			result.put(term.generateKey(), term);
		}
		return result;
	}

	Map<String, Term> multiply(Map<String, Term> part1, Map<String, Term> part2) {
		Map<String, Term> result = new HashMap<String, Term>();
		for (Term term1 : part1.values()) {
			result = add(result, multiply(term1, part2));
		}
		return result;
	}

	Map<String, Term> add(Map<String, Term> part1, Map<String, Term> part2) {
		Map<String, Term> result = new HashMap<String, Term>();
		for (String key1 : part1.keySet()) {
			if (part2.containsKey(key1)) {
				int coef = part1.get(key1).coef + part2.get(key1).coef;
				if (coef != 0) {
					result.put(key1, new Term(part1.get(key1).freeVariables, coef));
				}
			} else {
				result.put(key1, part1.get(key1));
			}
		}
		for (String key2 : part2.keySet()) {
			if (!part1.containsKey(key2)) {
				result.put(key2, part2.get(key2));
			}
		}
		return result;
	}

	Map<String, Term> readPart(String s, String token, Expression expr, Map<String, Integer> variable2value) {
		Map<String, Term> part = new HashMap<String, Term>();
		if (token.equals("(")) {
			int beginIndex = expr.index;
			int depth = 1;
			while (depth != 0) {
				String t = expr.readNext();
				if (t.equals("(")) {
					depth++;
				} else if (t.equals(")")) {
					depth--;
				}
			}
			int endIndex = expr.index - 1;

			return calculate(s.substring(beginIndex, endIndex), variable2value);
		} else if (Character.isDigit(token.charAt(0))) {
			int value = Integer.parseInt(token);
			if (value != 0) {
				part.put("", new Term(Collections.emptyList(), value));
			}
		} else if (variable2value.containsKey(token)) {
			int value = variable2value.get(token);
			if (value != 0) {
				part.put("", new Term(Collections.emptyList(), value));
			}
		} else {
			part.put(token, new Term(Collections.singletonList(token), 1));
		}
		return part;
	}
}

class Term implements Comparable<Term> {
	List<String> freeVariables;
	int coef;

	Term(List<String> freeVariables, int coef) {
		this.freeVariables = freeVariables;
		this.coef = coef;
	}

	String generateKey() {
		return String.join("*", freeVariables);
	}

	@Override
	public String toString() {
		String key = generateKey();
		return String.valueOf(coef) + (key.isEmpty() ? "" : ("*" + key));
	}

	@Override
	public int compareTo(Term other) {
		if (freeVariables.size() != other.freeVariables.size()) {
			return Integer.compare(other.freeVariables.size(), freeVariables.size());
		}

		for (int i = 0;; i++) {
			if (!freeVariables.get(i).equals(other.freeVariables.get(i))) {
				return freeVariables.get(i).compareTo(other.freeVariables.get(i));
			}
		}
	}
}

class Expression {
	String str;
	int index = 0;

	public Expression(String str) {
		this.str = str;
	}

	public String readNext() {
		while (index < str.length() && str.charAt(index) == ' ') {
			index++;
		}

		if (index == str.length()) {
			return null;
		}

		char ch = str.charAt(index);
		if (ch == '(' || ch == ')' || ch == '+' || ch == '-' || ch == '*') {
			index++;
			return ch + "";
		}

		int nextIndex = index + 1;
		while (nextIndex < str.length() && Character.isLetterOrDigit(str.charAt(nextIndex))) {
			nextIndex++;
		}
		String token = str.substring(index, nextIndex);
		index = nextIndex;
		return token;
	}
}