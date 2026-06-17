// https://leetcode.com/problems/maximum-subarray-sum-after-at-most-k-swaps/solutions/8332912/step-by-step-derivation-brute-force-gree-vd59/

use std::collections::BTreeMap;

impl Solution {
    pub fn max_sum(nums: Vec<i32>, k: i32) -> i64 {
        let n = nums.len();

        let mut sorted = nums.clone();
        sorted.sort_unstable();

        let mut result = i32::MIN;
        for start in 0..n {
            let mut candidate_to_count = BTreeMap::new();
            let mut other_to_count = BTreeMap::new();

            for i in 0..n {
                Self::update_map(
                    if i < n - (k as usize) {
                        &mut other_to_count
                    } else {
                        &mut candidate_to_count
                    },
                    sorted[i],
                    1,
                );
            }

            let mut current_sum = 0;
            for end in start..n {
                if !other_to_count.is_empty() {
                    let val = if other_to_count.contains_key(&nums[end]) {
                        nums[end]
                    } else {
                        *other_to_count.last_key_value().unwrap().0
                    };

                    Self::update_map(&mut other_to_count, val, -1);
                    Self::update_map(&mut candidate_to_count, val, 1);
                }

                let max_candidate = *candidate_to_count.last_key_value().unwrap().0;
                current_sum += max_candidate;
                Self::update_map(&mut candidate_to_count, max_candidate, -1);

                result = result.max(current_sum);
            }
        }

        result as i64
    }

    fn update_map(map: &mut BTreeMap<i32, i32>, key: i32, delta: i32) {
        map.entry(key)
            .and_modify(|count| *count += delta)
            .or_insert(delta);

        if map[&key] == 0 {
            map.remove(&key);
        }
    }
}
