import java.util.ArrayList;
import java.util.List;

public class Solution {
  public String solveEquation(String equation) {
    int equalIndex = equation.indexOf('=');

    Term x = new Term(0, false);
    Term c = new Term(0, true);

    add(x, c, parseTerms(equation.substring(0, equalIndex)));
    add(x, c, parseTerms(equation.substring(equalIndex + 1)).stream().map(Term::negate).toList());

    if (x.coefficient == 0) {
      if (c.coefficient == 0) {
        return "Infinite solutions";
      } else {
        return "No solution";
      }
    } else {
      return String.format("x=%d", -c.coefficient / x.coefficient);
    }
  }

  List<Term> parseTerms(String s) {
    if (!s.startsWith("+") && !s.startsWith("-")) {
      s = "+" + s;
    }

    List<Term> terms = new ArrayList<>();

    int beginIndex = 0;
    for (int i = 1; i <= s.length(); i++) {
      if (i == s.length() || s.charAt(i) == '+' || s.charAt(i) == '-') {
        String part = s.substring(beginIndex, i);

        boolean constant;
        int endIndex;
        if (part.endsWith("x")) {
          constant = false;
          endIndex = part.length() - 1;
        } else {
          constant = true;
          endIndex = part.length();
        }

        int coefficient;
        if (endIndex == 1) {
          if (part.startsWith("+")) {
            coefficient = 1;
          } else {
            coefficient = -1;
          }
        } else {
          coefficient = Integer.parseInt(part.substring(0, endIndex));
        }

        terms.add(new Term(coefficient, constant));

        beginIndex = i;
      }
    }

    return terms;
  }

  void add(Term x, Term c, List<Term> terms) {
    for (Term term : terms) {
      if (term.constant) {
        c.coefficient += term.coefficient;
      } else {
        x.coefficient += term.coefficient;
      }
    }
  }
}

class Term {
  int coefficient;
  boolean constant;

  Term(int coefficient, boolean constant) {
    this.coefficient = coefficient;
    this.constant = constant;
  }

  static Term negate(Term t) {
    return new Term(-t.coefficient, t.constant);
  }
}
