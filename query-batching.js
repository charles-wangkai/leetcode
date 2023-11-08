// https://leetcode.com/problems/query-batching/solutions/3695740/a-generalization-of-lc2676/

/**
 * @param {Function} queryMultiple
 * @param {number} t
 * @return {void}
 */
var QueryBatcher = function (queryMultiple, t) {
  this.queryMultiple = queryMultiple;
  this.t = t;
  this.batchedKeys = [];
  this.timerId = null;
  this.lastCallTime = 0;
};

/**
 * @param {string} key
 * @return {Promise<string>}
 */
QueryBatcher.prototype.getValue = async function (key) {
  return new Promise((resolve) => {
    const currentTime = Date.now();
    const timeSinceLastCall = currentTime - this.lastCallTime;
    const timeRemaining = Math.max(0, this.t - timeSinceLastCall);

    this.lastCallTime = currentTime + timeRemaining;

    this.batchedKeys.push({ key, resolve });

    if (!this.timerId) {
      clearTimeout(this.timerId);
      this.timerId = setTimeout(() => this.processBatchedKeys(), timeRemaining);
    }
  });
};

QueryBatcher.prototype.processBatchedKeys = async function () {
  const currentBatchedKeys = this.batchedKeys.slice();
  this.batchedKeys = [];
  this.timerId = null;

  const keys = currentBatchedKeys.map((obj) => obj.key);
  const resolves = currentBatchedKeys.map((obj) => obj.resolve);

  this.lastCallTime = Date.now();
  const resolvedValues = await this.queryMultiple(keys);

  for (let i = 0; i < keys.length; i++) {
    resolves[i](resolvedValues[i]);
  }
};

/**
 * async function queryMultiple(keys) {
 *   return keys.map(key => key + '!');
 * }
 *
 * const batcher = new QueryBatcher(queryMultiple, 100);
 * batcher.getValue('a').then(console.log); // resolves "a!" at t=0ms
 * batcher.getValue('b').then(console.log); // resolves "b!" at t=100ms
 * batcher.getValue('c').then(console.log); // resolves "c!" at t=100ms
 */
