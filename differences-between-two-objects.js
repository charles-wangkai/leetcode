// https://leetcode.com/problems/differences-between-two-objects/solutions/3553545/simple-recursive-solution/

/**
 * @param {object} obj1
 * @param {object} obj2
 * @return {object}
 */
function objDiff(obj1, obj2) {
  function isObject(x) {
    return typeof x === "object" && x !== null && !Array.isArray(x);
  }

  function compare(obj1, obj2, path, diff) {
    if (isObject(obj1) && isObject(obj2)) {
      for (const key in obj1) {
        if (key in obj2) {
          compare(obj1[key], obj2[key], [...path, key], diff);
        }
      }
    } else if (Array.isArray(obj1) && Array.isArray(obj2)) {
      for (let i = 0; i < Math.min(obj1.length, obj2.length); ++i) {
        compare(obj1[i], obj2[i], [...path, i], diff);
      }
    } else if (obj1 !== obj2) {
      let currentObj = diff;
      for (let i = 0; i < path.length - 1; ++i) {
        const key = path[i];
        if (!(key in currentObj)) {
          currentObj[key] = {};
        }
        currentObj = currentObj[key];
      }

      currentObj[path[path.length - 1]] = [obj1, obj2];
    }
  }

  const diff = {};
  compare(obj1, obj2, [], diff);

  return diff;
}
