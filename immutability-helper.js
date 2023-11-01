// https://leetcode.com/problems/immutability-helper/solutions/3620366/passing-code/

const isObj = (o) => typeof o === "object" && o !== null;

var ImmutableHelper = function (obj) {
  this.obj = obj;
};

const setMapConstructor = () => {
  return {
    hasValue: false,
    value: null,
    map: new Map(),
  };
};

/**
 * @param {Function} mutator
 * @return {JSON} clone of obj
 */
ImmutableHelper.prototype.produce = function (mutator) {
  function createProxy(obj, setMap) {
    return new Proxy(obj, {
      set(_, prop, value) {
        if (!setMap.map.has(prop)) {
          setMap.map.set(prop, setMapConstructor());
        }
        setMap.map.get(prop).hasValue = true;
        setMap.map.get(prop).value = value;
      },
      get(_, prop, value) {
        if (setMap.hasValue) {
          return setMap.value;
        }

        if (!setMap.map.has(prop)) {
          setMap.map.set(prop, setMapConstructor());
        }
        if (setMap.map.get(prop).hasValue) {
          return setMap.map.get(prop).value;
        }
        if (isObj(obj[prop])) {
          return createProxy(obj[prop], setMap.map.get(prop));
        }
        return obj[prop];
      },
    });
  }

  function simplify(setMap) {
    if (setMap.hasValue) return true;
    let hasMut = false;
    for (const [key, value] of [...setMap.map]) {
      if (!simplify(value)) {
        setMap.map.delete(key);
      } else {
        hasMut = true;
      }
    }
    return hasMut;
  }

  function transform(obj, setMap) {
    if (setMap.hasValue) {
      return setMap.value;
    }
    if (setMap.map.size === 0) return obj;
    let clone;
    if (isObj(obj)) {
      if (Array.isArray(obj)) {
        clone = [...obj];
      } else {
        clone = { ...obj };
      }
    } else {
      return obj;
    }
    for (const [key, value] of [...setMap.map]) {
      clone[key] = transform(obj[key], value);
    }
    return clone;
  }

  const setMap = setMapConstructor();
  const proxy = createProxy(this.obj, setMap);

  mutator(proxy);
  simplify(setMap);
  return transform(this.obj, setMap);
};

// const originalObj = {"x": 5};
// const mutator = new ImmutableHelper(originalObj);
// const newObj = mutator.produce((proxy) => {
//   proxy.x = proxy.x + 1;
// });
// console.log(originalObj); // {"x: 5"}
// console.log(newObj); // {"x": 6}
