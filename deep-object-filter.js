// https://leetcode.com/problems/deep-object-filter/solutions/3924565/simple-straightfoward/

/**
 * @param {Object|Array} obj
 * @param {Function} fn
 * @return {Object|Array|undefined}
 */
var deepFilter = function (obj, fn) {
  if (Array.isArray(obj)) {
    const filteredArray = obj
      .map((item) => deepFilter(item, fn))
      .filter((item) => item !== undefined);
    return filteredArray.length > 0 ? filteredArray : undefined;
  }

  if (typeof obj === "object" && obj !== null) {
    const filteredObj = {};
    let isEmpty = true;

    for (const key in obj) {
      if (obj.hasOwnProperty(key)) {
        const filteredValue = deepFilter(obj[key], fn);
        if (filteredValue !== undefined) {
          filteredObj[key] = filteredValue;
          isEmpty = false;
        }
      }
    }

    return isEmpty ? undefined : filteredObj;
  }

  return fn(obj) ? obj : undefined;
};
