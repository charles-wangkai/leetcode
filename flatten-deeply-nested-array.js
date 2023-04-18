/**
 * @param {any[]} arr
 * @param {number} depth
 * @return {any[]}
 */
var flat = function (arr, n) {
  if (n == 0) {
    return arr;
  }

  const result = [];
  for (const x of arr) {
    if (Array.isArray(x)) {
      for (const y of flat(x, n - 1)) {
        result.push(y);
      }
    } else {
      result.push(x);
    }
  }

  return result;
};
