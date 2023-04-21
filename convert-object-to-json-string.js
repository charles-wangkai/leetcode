/**
 * @param {any} object
 * @return {string}
 */
var jsonStringify = function (object) {
  if (typeof object === "string") {
    return `"${object}"`;
  }
  if (Array.isArray(object)) {
    return `[${object.map(jsonStringify).join()}]`;
  }
  if (typeof object === "object" && object !== null) {
    return (
      "{" +
      Object.keys(object)
        .map((key) => `"${key}":${jsonStringify(object[key])}`)
        .join() +
      "}"
    );
  }

  return String(object);
};
