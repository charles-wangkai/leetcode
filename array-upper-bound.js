/**
 * @param {number} target
 * @return {number}
 */
Array.prototype.upperBound = function (target) {
  let result = -1;
  let lower = 0;
  let upper = this.length - 1;
  while (lower <= upper) {
    const middle = Math.floor((lower + upper) / 2);
    if (this[middle] <= target) {
      if (this[middle] === target) {
        result = middle;
      }
      lower = middle + 1;
    } else {
      upper = middle - 1;
    }
  }

  return result;
};

// [3,4,5].upperBound(5); // 2
// [1,4,5].upperBound(2); // -1
// [3,4,6,6,6,6,7].upperBound(6) // 5
