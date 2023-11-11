// https://leetcode.com/problems/date-range-generator/solutions/3757337/straight-forward/

/**
 * @param {string} start
 * @param {string} end
 * @param {number} step
 * @yields {string}
 */
var dateRangeGenerator = function* (start, end, step) {
  const startDate = new Date(start);
  const endDate = new Date(end);

  // Convert step to milliseconds
  const stepMs = step * 24 * 60 * 60 * 1000;

  let currentDate = startDate;
  while (currentDate <= endDate) {
    yield currentDate.toISOString().split("T")[0];
    currentDate = new Date(currentDate.getTime() + stepMs);
  }
};

/**
 * const g = dateRangeGenerator('2023-04-01', '2023-04-04', 1);
 * g.next().value; // '2023-04-01'
 * g.next().value; // '2023-04-02'
 * g.next().value; // '2023-04-03'
 * g.next().value; // '2023-04-04'
 * g.next().done; // true
 */
