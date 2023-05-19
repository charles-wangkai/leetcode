class EventEmitter {
  constructor() {
    this.eventToCallbacks = new Map();
  }

  subscribe(event, cb) {
    if (!this.eventToCallbacks.has(event)) {
      this.eventToCallbacks.set(event, []);
    }
    const callbacks = this.eventToCallbacks.get(event);

    const index = callbacks.length;
    callbacks.push(cb);

    return {
      unsubscribe: () => {
        callbacks[index] = null;
      },
    };
  }

  emit(event, args = []) {
    return (
      this.eventToCallbacks.has(event) ? this.eventToCallbacks.get(event) : []
    )
      .filter((callback) => callback !== null)
      .map((callback) => callback(...args));
  }
}

// const emitter = new EventEmitter();
//
// // Subscribe to the onClick event with onClickCallback
// function onClickCallback() { return 99 }
// const sub = emitter.subscribe('onClick', onClickCallback);
//
// emitter.emit('onClick'); // [99]
// sub.unsubscribe(); // undefined
// emitter.emit('onClick'); // []
