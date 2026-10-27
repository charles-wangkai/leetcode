use std::{
    cmp::Reverse,
    collections::{BinaryHeap, HashMap},
    iter::FromIterator,
};

impl Solution {
    pub fn count_staircase(nums: Vec<i32>) -> i32 {
        let mut value_to_indices = HashMap::new();
        for i in 0..nums.len() {
            value_to_indices
                .entry(nums[i])
                .or_insert(Vec::new())
                .push(i);
        }

        let mut value_to_pos: HashMap<_, _> =
            HashMap::from_iter(value_to_indices.keys().map(|&value| (value, 0)));

        let mut pq = BinaryHeap::new();
        for &value in value_to_indices.keys() {
            pq.push((Reverse(value_to_indices[&value][0]), value));
        }

        let mut result = 0;
        for begin_index in 0..nums.len() {
            let mut chosen = Vec::new();
            let mut length = 0;
            let mut lower = 0;
            let mut upper = usize::MAX;

            while let Some((Reverse(_), head)) = pq.pop() {
                chosen.push(head);

                let target_freq = chosen.len();
                length += target_freq;

                let rest_indices = &value_to_indices[&head][value_to_pos[&head]..];

                if target_freq > rest_indices.len() {
                    break;
                }

                lower = lower.max(rest_indices[target_freq - 1] - begin_index + 1);
                upper = upper.min(
                    (if target_freq == rest_indices.len() {
                        nums.len()
                    } else {
                        rest_indices[target_freq]
                    }) - begin_index,
                );

                if lower > upper || length > upper {
                    break;
                }

                if length >= lower {
                    result += 1;
                }
            }

            let first = chosen[0];
            if value_to_pos[&first] != value_to_indices[&first].len() - 1 {
                value_to_pos.insert(first, value_to_pos[&first] + 1);
                pq.push((
                    Reverse(value_to_indices[&first][value_to_pos[&first]]),
                    first,
                ));
            }

            for i in 1..chosen.len() {
                pq.push((
                    Reverse(value_to_indices[&chosen[i]][value_to_pos[&chosen[i]]]),
                    chosen[i],
                ));
            }
        }

        result
    }
}
