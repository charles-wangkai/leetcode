/**
 * @param {Function} fn
 * @param {number} t milliseconds
 * @return {Function}
 */
var debounce = function (fn, t) {
  const timeoutIds = [];

  return function (...args) {
    while (timeoutIds.length !== 0) {
      clearTimeout(timeoutIds.pop());
    }

    timeoutIds.push(setTimeout(() => fn(...args), t));
  };
};

/**
 * const log = debounce(console.log, 100);
 * log('Hello'); // cancelled
 * log('Hello'); // cancelled
 * log('Hello'); // Logged at t=100ms
 */
