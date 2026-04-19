impl Solution {
    pub fn count_good_integers_on_path(l: i64, r: i64, directions: String) -> i64 {
        Self::compute_good_num(&directions, r) - Self::compute_good_num(&directions, l - 1)
    }

    fn compute_good_num(directions: &str, limit: i64) -> i64 {
        let offsets = Self::build_offsets(directions);
        let limit_digits: Vec<usize> = format!("{:016}", limit)
            .chars()
            .map(|c| (c as usize) - ('0' as usize))
            .collect();

        let mut dp = [[[[[0i64; 2]; 10]; 10]; 10]; 10];
        for d in 0..=limit_digits[0] {
            dp[d][0][0][0][if d == limit_digits[0] { 1 } else { 0 }] = 1;
        }

        for i in 1..offsets.len() {
            let mut next_dp = [[[[[0i64; 2]; 10]; 10]; 10]; 10];
            for d1 in 0..10 {
                for d2 in 0..10 {
                    for d3 in 0..10 {
                        for d4 in 0..10 {
                            for strict in 0..2 {
                                if dp[d1][d2][d3][d4][strict] != 0 {
                                    let min_digit = match offsets[i] {
                                        1 => d1,
                                        2 => d2,
                                        3 => d3,
                                        4 => d4,
                                        _ => 0,
                                    };
                                    let max_digit = if strict == 1 { limit_digits[i] } else { 9 };
                                    for d in min_digit..=max_digit {
                                        next_dp[d][d1][d2][d3][if strict == 1
                                            && d == limit_digits[i]
                                        {
                                            1
                                        } else {
                                            0
                                        }] += dp[d1][d2][d3][d4][strict];
                                    }
                                }
                            }
                        }
                    }
                }
            }

            dp = next_dp;
        }

        let mut result = 0;
        for d1 in 0..10 {
            for d2 in 0..10 {
                for d3 in 0..10 {
                    for d4 in 0..10 {
                        for strict in 0..2 {
                            result += dp[d1][d2][d3][d4][strict];
                        }
                    }
                }
            }
        }

        result
    }

    fn build_offsets(directions: &str) -> [usize; 16] {
        let mut result = [0; 16];
        let mut index = 0;
        for direction in directions.chars() {
            let offset = if direction == 'R' { 1 } else { 4 };

            index += offset;
            result[index] = offset;
        }

        result
    }
}
