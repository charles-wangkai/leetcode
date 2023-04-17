// https://leetcode.com/problems/array-prototype-last/solutions/3421043/array-prototype-last-level-up-your-javascript-skills-with-these-intuitive-implementations/

Array.prototype.last = function () {
  return this.length ? this[this.length - 1] : -1;
};

/**
 * const arr = [1, 2, 3];
 * arr.last(); // 3
 */
