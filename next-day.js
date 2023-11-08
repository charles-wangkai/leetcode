// https://leetcode.com/problems/next-day/solutions/3703647/easy-peasy/

/**
 * @return {string}
 */
Date.prototype.nextDay = function () {
  return new Date(this.getTime() + 86400000).toISOString().split("T")[0];
};

/**
 * const date = new Date("2014-06-20");
 * date.nextDay(); // "2014-06-21"
 */
