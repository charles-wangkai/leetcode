// https://leetcode.com/problems/throttle/solutions/3514204/javascript-solution/

/**
 * @param {Function} fn
 * @param {number} t
 * @return {Function}
 */
var throttle = function (fn, t) {
  let inTimeout = false;
  let currentArgs = null;

  function call() {
    if (currentArgs) {
      fn(...currentArgs);
      currentArgs = null;

      inTimeout = true;
      setTimeout(call, t);
    } else {
      inTimeout = false;
    }
  }

  return function (...args) {
    if (inTimeout) {
      currentArgs = args;
    } else {
      fn(...args);

      inTimeout = true;
      setTimeout(call, t);
    }
  };
};

/**
 * const throttled = throttle(console.log, 100);
 * throttled("log"); // logged immediately.
 * throttled("log"); // logged at t=100ms.
 */
