// https://leetcode.com/problems/call-function-with-custom-context/solutions/3539867/use-symbol-with-context/

/**
 * @param {Object} context
 * @param {any[]} args
 * @return {any}
 */
Function.prototype.callPolyfill = function (context, ...args) {
  const functionId = Symbol();
  context[functionId] = this;

  return context[functionId](...args);
};

// function increment() { this.count++; return this.count; }
// increment.callPolyfill({count: 1}); // 2
