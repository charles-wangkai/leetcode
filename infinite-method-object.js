// https://leetcode.com/problems/infinite-method-object/solutions/3574392/proxy-solution/

/**
 * @return {Object}
 */
var createInfiniteObject = function () {
  const name_handler = {
    get(_, prop) {
      return () => String(prop);
    },
  };

  const name_proxy = new Proxy({}, name_handler);

  return name_proxy;
};

// const obj = createInfiniteObject();
// obj['abc123'](); // "abc123"
