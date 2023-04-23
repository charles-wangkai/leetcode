/**
 * @return {Generator<number>}
 */
var fibGenerator = function* () {
  yield 0;
  yield 1;

  let prev = 0;
  let curr = 1;
  while (true) {
    let next = prev + curr;
    yield next;

    prev = curr;
    curr = next;
  }
};

/**
 * const gen = fibGenerator();
 * gen.next().value; // 0
 * gen.next().value; // 1
 */
