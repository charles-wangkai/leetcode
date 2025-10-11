use std::collections::BinaryHeap;

impl Solution {
    pub fn min_elimination_time(mut time_req: Vec<i32>, split_time: i32) -> i64 {
        time_req.sort_unstable();

        let mut result = -1;
        let mut lower = 0;
        let mut upper = time_req.iter().map(|&x| x as i64).sum::<i64>()
            + ((time_req.len() - 1) as i64) * (split_time as i64);
        while lower <= upper {
            let middle = (lower + upper) / 2;
            if Self::check(&time_req, split_time, middle) {
                result = middle;
                upper = middle - 1;
            } else {
                lower = middle + 1;
            }
        }

        result
    }

    fn check(time_req: &[i32], split_time: i32, time_limit: i64) -> bool {
        let mut pq = BinaryHeap::new();
        pq.push((time_limit, time_limit));

        for i in (0..time_req.len()).rev() {
            if pq.is_empty() || pq.peek().unwrap().0 < time_req[i] as i64 {
                return false;
            }

            let (max_rest, min_rest) = pq.pop().unwrap();
            if max_rest != min_rest {
                pq.push((max_rest - (split_time as i64), min_rest));
            }
            if max_rest - (split_time as i64) >= time_req[i] as i64 {
                pq.push((
                    max_rest - (split_time as i64),
                    max_rest
                        - (max_rest - (time_req[i] as i64)) / (split_time as i64)
                            * (split_time as i64),
                ));
            }
        }

        true
    }
}
