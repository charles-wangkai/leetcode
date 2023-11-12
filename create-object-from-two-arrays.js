// https://leetcode.com/problems/create-object-from-two-arrays/solutions/3829338/straightforward-easy-o-n/

/**
 * @param {Array} keysArr
 * @param {Array} valuesArr
 * @return {Object}
 */
var createObject = function (keysArr, valuesArr) {
  const obj = {};
  for (const keyIndex in keysArr) {
    const key = keysArr[keyIndex];
    if (key in obj) {
      continue;
    }
    obj[key] = valuesArr[keyIndex];
  }
  return obj;
};
