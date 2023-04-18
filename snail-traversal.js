/**
 * @param {number} rowsCount
 * @param {number} colsCount
 * @return {Array<Array<number>>}
 */
Array.prototype.snail = function (rowsCount, colsCount) {
  if (rowsCount * colsCount !== this.length) {
    return [];
  }

  const result = new Array(rowsCount);
  for (let r = 0; r < result.length; ++r) {
    result[r] = new Array(colsCount);
  }

  let index = 0;
  for (let c = 0; c < colsCount; ++c) {
    if (c % 2 === 0) {
      for (let r = 0; r < rowsCount; ++r) {
        result[r][c] = this[index];
        ++index;
      }
    } else {
      for (let r = rowsCount - 1; r >= 0; --r) {
        result[r][c] = this[index];
        ++index;
      }
    }
  }

  return result;
};

/**
 * const arr = [1,2,3,4];
 * arr.snail(1,4); // [[1,2,3,4]]
 */
