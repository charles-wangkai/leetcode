/**
 * @param {Function} fn
 * @return {Function<Promise<number>>}
 */
var promisify = function (fn) {
  return async function (...args) {
    return new Promise((resolve, reject) => {
      const handleValueAndError = (value, error) => {
        if (error) {
          reject(error);
        } else {
          resolve(value);
        }
      };

      fn(handleValueAndError, ...args);
    });
  };
};

/**
 * const asyncFunc = promisify(callback => callback(42));
 * asyncFunc().then(console.log); // 42
 */
