// https://leetcode.com/problems/memoize-ii/solutions/3408825/using-map-and-symbol/

/**
 * @param {Function} fn
 */
function memoize(fn) {
  const RESULT_KEY = Symbol("result");

  const cache = new Map();

  return function (...args) {
    let currentCache = cache;
    for (const arg of args) {
      if (!currentCache.has(arg)) {
        currentCache.set(arg, new Map());
      }
      currentCache = currentCache.get(arg);
    }

    if (!currentCache.has(RESULT_KEY)) {
      currentCache.set(RESULT_KEY, fn(...args));
    }

    return currentCache.get(RESULT_KEY);
  };
}

/**
 * let callCount = 0;
 * const memoizedFn = memoize(function (a, b) {
 *	 callCount += 1;
 *   return a + b;
 * })
 * memoizedFn(2, 3) // 5
 * memoizedFn(2, 3) // 5
 * console.log(callCount) // 1
 */
