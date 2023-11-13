// https://leetcode.com/problems/delay-the-resolution-of-each-promise/solutions/3924568/javascript-promise-vs-async-await/

/**
 * @param {Array<Function>} functions
 * @param {number} ms
 * @return {Array<Function>}
 */
var delayAll = function (functions, ms) {
  return functions.map((func) => {
    return () => {
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          func()
            .then((result) => resolve(result))
            .catch((error) => reject(error));
        }, ms);
      });
    };
  });
};
