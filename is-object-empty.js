/**
 * @param {Object | Array} obj
 * @return {boolean}
 */
var isEmpty = function (obj) {
  return Array.isArray(obj) ? obj.length === 0 : Object.keys(obj).length === 0;
};
