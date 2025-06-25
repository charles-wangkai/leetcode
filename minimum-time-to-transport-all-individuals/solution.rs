use std::{cmp::Reverse, collections::BinaryHeap};

const FACTOR: f64 = 1e9;

impl Solution {
    pub fn min_time(n: i32, k: i32, m: i32, time: Vec<i32>, mul: Vec<f64>) -> f64 {
        let mut cross_mask_vecs = vec![Vec::new(); 1 << n];
        for mask in 0..cross_mask_vecs.len() {
            for cross_mask in 1usize..1 << n {
                if cross_mask.count_ones() <= (k as u32) && (mask & cross_mask) == 0 {
                    cross_mask_vecs[mask].push(cross_mask);
                }
            }
        }

        let mut max_times = vec![0; 1 << n];
        for mask in 1..max_times.len() {
            for i in 0..(n as usize) {
                if ((mask >> i) & 1) != 0 {
                    max_times[mask] = max_times[mask].max(time[i]);
                }
            }
        }

        let mut min_times = vec![vec![vec![None; mul.len()]; 2]; 1 << n];

        let mut pq = BinaryHeap::new();
        pq.push((Reverse(Self::encode(0.0)), 0, 0, 0));
        while let Some((Reverse(encoded_min_time), mask, boat_crossed, stage_index)) = pq.pop() {
            let min_time = Self::decode(encoded_min_time);

            if min_times[mask][boat_crossed][stage_index].is_none() {
                min_times[mask][boat_crossed][stage_index] = Some(min_time);

                if boat_crossed == 1 {
                    for i in 0..(n as usize) {
                        if ((mask >> i) & 1) != 0 {
                            let next_mask = mask - (1 << i);

                            let t = (time[i] as f64) * mul[stage_index];
                            let next_min_time = min_time + t;
                            let next_stage_index = (stage_index + (t.floor() as usize)) % mul.len();

                            if min_times[next_mask][0][next_stage_index].is_none() {
                                pq.push((
                                    Reverse(Self::encode(next_min_time)),
                                    next_mask,
                                    0,
                                    next_stage_index,
                                ));
                            }
                        }
                    }
                } else {
                    for &cross_mask in &cross_mask_vecs[mask] {
                        let next_mask = mask + cross_mask;

                        let t = (max_times[cross_mask] as f64) * mul[stage_index];
                        let next_min_time = min_time + t;
                        let next_stage_index = (stage_index + (t.floor() as usize)) % mul.len();

                        if min_times[next_mask][1][next_stage_index].is_none() {
                            pq.push((
                                Reverse(Self::encode(next_min_time)),
                                next_mask,
                                1,
                                next_stage_index,
                            ));
                        }
                    }
                }
            }
        }

        let mut result = None;
        for stage_index in 0..mul.len() {
            if let Some(min_time) = min_times[(1 << n) - 1][1][stage_index] {
                if result.is_none() || min_time < result.unwrap() {
                    result = Some(min_time);
                }
            }
        }

        result.unwrap_or(-1.0)
    }

    fn encode(x: f64) -> i64 {
        (x * FACTOR).round() as i64
    }

    fn decode(x: i64) -> f64 {
        (x as f64) / FACTOR
    }
}
