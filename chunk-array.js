/**
 * @param {Array} arr
 * @param {number} size
 * @return {Array[]}
 */
var chunk = function (arr, size) {
  const result = [];
  let subarray = [];
  for (x of arr) {
    subarray.push(x);
    if (subarray.length == size) {
      result.push(subarray);
      subarray = [];
    }
  }
  if (subarray.length) {
    result.push(subarray);
  }

  return result;
};
