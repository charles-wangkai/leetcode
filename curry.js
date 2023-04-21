// https://leetcode.com/problems/curry/solutions/3415871/javascript-currying-2-solutions/

/**
 * @param {Function} fn
 * @return {Function}
 */
var curry = function (fn) {
  return function curried(...args) {
    return args.length == fn.length
      ? fn(...args)
      : (...newArgs) => curried(...args, ...newArgs);
  };
};

/**
 * function sum(a, b) { return a + b; }
 * const csum = curry(sum);
 * csum(1)(2) // 3
 */
