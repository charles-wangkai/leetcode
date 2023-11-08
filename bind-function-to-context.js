// https://leetcode.com/problems/bind-function-to-context/solutions/3706578/js-easy-solution/

/**
 * @param {Object} obj
 * @return {Function}
 */
Function.prototype.bindPolyfill = function (obj) {
  const context = this;
  return function (...newArgs) {
    return context.call(obj, ...newArgs);
  };
};
