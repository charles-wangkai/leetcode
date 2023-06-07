// https://leetcode.com/problems/execute-asynchronous-functions-in-parallel/solutions/3608110/just-do-it/

/**
 * @param {Array<Function>} functions
 * @return {Promise<any>}
 */
var promiseAll = async function (functions) {
  return new Promise((resolve, reject) => {
    const results = [];
    let resolvedCount = 0;
    let rejected = false;

    for (let i = 0; i < functions.length; ++i) {
      functions[i]()
        .then((result) => {
          if (!rejected) {
            results[i] = result;
            ++resolvedCount;

            if (resolvedCount === functions.length) {
              resolve(results);
            }
          }
        })
        .catch((error) => {
          if (!rejected) {
            rejected = true;
            reject(error);
          }
        });
    }
  });
};

/**
 * const promise = promiseAll([() => new Promise(res => res(42))])
 * promise.then(console.log); // [42]
 */
