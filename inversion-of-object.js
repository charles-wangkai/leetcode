// https://leetcode.com/problems/inversion-of-object/solutions/4017485/inversion-of-object-in-js/

/**
 * @param {Object|Array} obj
 * @return {Object}
 */
var invertObject = function (obj) {
  let result = {};
  let k = Object.keys(obj),
    v = Object.values(obj);
  for (var i = 0; i < k.length; i++) {
    if (result.hasOwnProperty(v[i])) {
      if (!Array.isArray(result[v[i]])) {
        result[v[i]] = [result[v[i]]];
      }
      result[v[i]].push(k[i]);
    } else {
      result[v[i]] = k[i];
    }
  }
  return result;
};
