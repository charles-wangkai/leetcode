use std::{cmp::Reverse, collections::BinaryHeap};

impl Solution {
    pub fn min_time_max_power(
        n: i32,
        edges: Vec<Vec<i32>>,
        power: i32,
        cost: Vec<i32>,
        source: i32,
        target: i32,
    ) -> Vec<i64> {
        let mut edge_lists = vec![Vec::new(); n as usize];
        for i in 0..edges.len() {
            edge_lists[edges[i][0] as usize].push(i);
        }

        let mut times = vec![vec![i64::MAX; (power + 1) as usize]; n as usize];
        times[source as usize][power as usize] = 0;

        let mut pq = BinaryHeap::new();
        pq.push((Reverse(0), source, power));

        while let Some((Reverse(time), node, rest_power)) = pq.pop() {
            if times[node as usize][rest_power as usize] == time {
                if rest_power >= cost[node as usize] {
                    for &edge in &edge_lists[node as usize] {
                        let next_node = edges[edge][1];
                        let next_rest_power = rest_power - cost[node as usize];
                        let next_time = time + (edges[edge][2] as i64);
                        if next_time < times[next_node as usize][next_rest_power as usize] {
                            times[next_node as usize][next_rest_power as usize] = next_time;
                            pq.push((Reverse(next_time), next_node, next_rest_power));
                        }
                    }
                }
            }
        }

        let min_time = times[target as usize].iter().min().copied().unwrap();
        if min_time == i64::MAX {
            return vec![-1, -1];
        }

        let max_rest_power = (0..=power)
            .filter(|&rest_power| times[target as usize][rest_power as usize] == min_time)
            .max()
            .unwrap();

        vec![min_time, max_rest_power as i64]
    }
}
