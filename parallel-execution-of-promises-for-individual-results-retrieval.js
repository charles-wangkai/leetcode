// https://leetcode.com/problems/parallel-execution-of-promises-for-individual-results-retrieval/solutions/3840189/simple-typescript-solution-with-explanation/

/**
 * @param {Array<Function>} functions
 * @return {Promise<Array>}
 */
var promiseAllSettled = function (functions) {
  const results = [];
  let finCount = 0;
  return new Promise((resolve) => {
    functions.forEach((fn, i) => {
      Promise.resolve(fn())
        .then((res) => {
          results[i] = {
            status: "fulfilled",
            value: res,
          };
        })
        .catch((err) => {
          results[i] = {
            status: "rejected",
            reason: err,
          };
        })
        .finally(() => {
          finCount++;
          if (finCount === functions.length) {
            resolve(results);
          }
        });
    });
  });
};

/**
 * const functions = [
 *    () => new Promise(resolve => setTimeout(() => resolve(15), 100))
 * ]
 * const time = performance.now()
 *
 * const promise = promiseAllSettled(functions);
 *
 * promise.then(res => {
 *     const out = {t: Math.floor(performance.now() - time), values: res}
 *     console.log(out) // {"t":100,"values":[{"status":"fulfilled","value":15}]}
 * })
 */
