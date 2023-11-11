// https://leetcode.com/problems/undefined-to-null/solutions/3776664/clean-solution/

/**
 * @param {Object|Array} obj
 * @return {Object|Array}
 */
var undefinedToNull = function (obj) {
  if (obj === null || obj === undefined) return null;
  if (typeof obj !== "object") return obj;
  if (Array.isArray(obj)) return obj.map(undefinedToNull);

  let res = {};
  for (let key in obj) {
    res[key] = undefinedToNull(obj[key]);
  }
  return res;
};

/**
 * undefinedToNull({"a": undefined, "b": 3}) // {"a": null, "b": 3}
 * undefinedToNull([undefined, undefined]) // [null, null]
 */
