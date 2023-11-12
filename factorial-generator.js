// https://leetcode.com/problems/factorial-generator/solutions/3918852/solution-with-while-loop/

/**
 * @param {number} n
 * @yields {number}
 */
function* factorial(n) {
  let result = 1;
  yield result;
  for (let i = 2; i <= n; ++i) {
    result *= i;
    yield result;
  }
}

/**
 * const gen = factorial(2);
 * gen.next().value; // 1
 * gen.next().value; // 2
 */
