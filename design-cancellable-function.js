// https://leetcode.com/problems/design-cancellable-function/solutions/3440923/simple-solution-with-async-and-promise-race/

/**
 * @param {Generator} generator
 * @return {[Function, Promise]}
 */
var cancellable = function (generator) {
  let cancel;
  const cancelPromise = new Promise((_, reject) => {
    cancel = () => reject("Cancelled");
  });

  const promise = (async () => {
    let next = generator.next();
    while (!next.done) {
      try {
        next = generator.next(await Promise.race([next.value, cancelPromise]));
      } catch (e) {
        next = generator.throw(e);
      }
    }

    return next.value;
  })();

  return [cancel, promise];
};

/**
 * function* tasks() {
 *   const val = yield new Promise(resolve => resolve(2 + 2));
 *   yield new Promise(resolve => setTimeout(resolve, 100));
 *   return val + 1;
 * }
 * const [cancel, promise] = cancellable(tasks());
 * setTimeout(cancel, 50);
 * promise.catch(console.log); // logs "Cancelled" at t=50ms
 */
