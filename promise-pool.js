// https://leetcode.com/problems/promise-pool/solutions/3410998/2-lines-effective-solution-for-beginnner/

/**
 * @param {Function[]} functions
 * @param {number} n
 * @return {Function}
 */
var promisePool = async function (functions, n) {
  let next = () => functions[n++]?.().then(next);

  return Promise.all(functions.slice(0, n).map((f) => f().then(next)));
};

/**
 * const sleep = (t) => new Promise(res => setTimeout(res, t));
 * promisePool([() => sleep(500), () => sleep(400)], 1)
 *   .then(console.log) // After 900ms
 */
