/**
 * @param {Array} arr
 * @return {Matrix}
 */
var jsonToMatrix = function (arr) {
  const rowMaps = arr.map((row) => {
    const rowMap = new Map();
    search(rowMap, row, []);

    return rowMap;
  });

  const columnNames = [
    ...new Set(rowMaps.flatMap((rowMap) => [...rowMap.keys()])),
  ].sort();

  return [
    columnNames,
    ...rowMaps.map((rowMap) =>
      columnNames.map((columnName) =>
        rowMap.has(columnName) ? rowMap.get(columnName) : ""
      )
    ),
  ];
};

function search(rowMap, obj, path) {
  for (const key in obj) {
    path.push(key);

    if (typeof obj[key] === "object" && obj[key] !== null) {
      search(rowMap, obj[key], path);
    } else {
      rowMap.set(path.join("."), obj[key]);
    }

    path.pop();
  }
}
