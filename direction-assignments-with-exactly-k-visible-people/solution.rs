const MOD_INT: ModInt = ModInt {
    modulus: 1_000_000_007,
};

impl Solution {
    pub fn count_visible_people(n: i32, pos: i32, k: i32) -> i32 {
        let mut factorial_mods = vec![0; n as usize];
        factorial_mods[0] = 1;
        for i in 1..factorial_mods.len() {
            factorial_mods[i] = MOD_INT.multiply_mod(factorial_mods[i - 1], i as i32);
        }

        MOD_INT.multiply_mod(
            (0..=k)
                .map(|left| {
                    MOD_INT.multiply_mod(
                        Self::c_mod(&factorial_mods, pos, left),
                        Self::c_mod(&factorial_mods, n - 1 - pos, k - left),
                    )
                })
                .reduce(|acc, x| MOD_INT.add_mod(acc, x))
                .unwrap(),
            2,
        )
    }

    fn c_mod(factorial_mods: &[i32], n: i32, k: i32) -> i32 {
        if k <= n {
            MOD_INT.divide_mod(
                factorial_mods[n as usize],
                MOD_INT.multiply_mod(factorial_mods[k as usize], factorial_mods[(n - k) as usize]),
            )
        } else {
            0
        }
    }
}

struct ModInt {
    modulus: i32,
}

#[allow(dead_code)]
impl ModInt {
    fn modulo(&self, x: i64) -> i32 {
        x.rem_euclid(self.modulus as i64) as i32
    }

    fn mod_inv(&self, x: i32) -> i32 {
        self.pow_mod(x, (self.modulus - 2) as i64)
    }

    fn add_mod(&self, x: i32, y: i32) -> i32 {
        self.modulo((x + y) as i64)
    }

    fn multiply_mod(&self, x: i32, y: i32) -> i32 {
        self.modulo((x as i64) * (y as i64))
    }

    fn divide_mod(&self, x: i32, y: i32) -> i32 {
        self.multiply_mod(x, self.mod_inv(y))
    }

    fn pow_mod(&self, base: i32, exponent: i64) -> i32 {
        if exponent == 0 {
            return 1;
        }

        self.multiply_mod(
            if exponent % 2 == 0 { 1 } else { base },
            self.pow_mod(self.multiply_mod(base, base), exponent / 2),
        )
    }
}
