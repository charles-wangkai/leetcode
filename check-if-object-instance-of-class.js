// https://leetcode.com/problems/check-if-object-instance-of-class/solutions/3406546/basic-javascript-solution-easy-to-understand-beginner-friendly/

/**
 * @param {Object} object
 * @param {Function} classFunction
 * @return {boolean}
 */
var checkIfInstanceOf = function (obj, classFunction) {
  while (true) {
    if (obj == null) {
      return false;
    }
    if (obj.constructor === classFunction) {
      return true;
    }

    obj = Object.getPrototypeOf(obj);
  }
};

/**
 * checkIfInstanceOf(new Date(), Date); // true
 */
