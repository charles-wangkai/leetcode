/**
 * @param {Array} arr1
 * @param {Array} arr2
 * @return {Array}
 */
var join = function (arr1, arr2) {
  arr1.sort((a, b) => a.id - b.id);
  arr2.sort((a, b) => a.id - b.id);

  const result = [];
  let index1 = 0;
  let index2 = 0;
  while (index1 != arr1.length || index2 != arr2.length) {
    if (
      index2 == arr2.length ||
      (index1 != arr1.length && arr1[index1].id < arr2[index2].id)
    ) {
      result.push(arr1[index1]);

      ++index1;
    } else if (
      index1 == arr1.length ||
      (index2 != arr2.length && arr2[index2].id < arr1[index1].id)
    ) {
      result.push(arr2[index2]);

      ++index2;
    } else {
      const merged = arr1[index1];
      for (const k in arr2[index2]) {
        merged[k] = arr2[index2][k];
      }
      result.push(merged);

      ++index1;
      ++index2;
    }
  }

  return result;
};
