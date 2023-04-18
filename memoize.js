/**
 * @param {Function} fn
 */
function memoize(fn) {
  let cache = new Map();

  return function (...args) {
    let key = args.toString();
    if (!cache.has(key)) {
      cache.set(key, fn(...args));
    }

    return cache.get(key);
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
