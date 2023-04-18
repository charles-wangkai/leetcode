var TimeLimitedCache = function () {
  this.keyToValue = new Map();
  this.keyToTimeoutId = new Map();
};

/**
 * @param {number} key
 * @param {number} value
 * @param {number} time until expiration in ms
 * @return {boolean} if un-expired key already existed
 */
TimeLimitedCache.prototype.set = function (key, value, duration) {
  let result = this.keyToValue.has(key);
  if (result) {
    clearTimeout(this.keyToTimeoutId.get(key));
  }

  this.keyToValue.set(key, value);
  this.keyToTimeoutId.set(
    key,
    setTimeout(() => {
      this.keyToValue.delete(key);
      this.keyToTimeoutId.delete(key);
    }, duration)
  );

  return result;
};

/**
 * @param {number} key
 * @return {number} value associated with key
 */
TimeLimitedCache.prototype.get = function (key) {
  return this.keyToValue.has(key) ? this.keyToValue.get(key) : -1;
};

/**
 * @return {number} count of non-expired keys
 */
TimeLimitedCache.prototype.count = function () {
  return this.keyToValue.size;
};

/**
 * Your TimeLimitedCache object will be instantiated and called as such:
 * var obj = new TimeLimitedCache()
 * obj.set(1, 42, 1000); // false
 * obj.get(1) // 42
 * obj.count() // 1
 */
