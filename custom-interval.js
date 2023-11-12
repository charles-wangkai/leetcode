// https://leetcode.com/problems/custom-interval/solutions/3857062/fake-interval-id/

const ids = [];

/**
 * @param {Function} fn
 * @param {number} delay
 * @param {number} period
 * @return {number} id
 */

function customInterval(fn, delay, period) {
  let len = ids.length;
  let p = 0;

  function executeFn() {
    fn();
    p += period;
    ids[len] = setTimeout(executeFn, p + delay);
  }

  ids[len] = setTimeout(executeFn, delay);

  return len; // Return the intervalId directly
}

/**
 * @param {number} id
 * @return {void}
 */
function customClearInterval(id) {
  clearTimeout(ids[id]);
}
