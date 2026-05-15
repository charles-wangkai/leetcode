use std::{cmp::Reverse, collections::BinaryHeap};

impl Solution {
    pub fn min_cost(n: i32, prices: Vec<i32>, roads: Vec<Vec<i32>>) -> Vec<i32> {
        (0..n as usize)
            .map(|start| Self::compute_min_cost(n as usize, &prices, &roads, start))
            .collect()
    }

    fn compute_min_cost(n: usize, prices: &[i32], roads: &[Vec<i32>], start: usize) -> i32 {
        let empty_distances = Self::build_distances(n, roads, start, false);
        let return_distances = Self::build_distances(n, roads, start, true);

        (0..n)
            .map(|i| {
                Self::add(
                    Self::add(empty_distances[i], prices[i] as i64),
                    return_distances[i],
                )
            })
            .min()
            .unwrap() as i32
    }

    fn add(x: i64, y: i64) -> i64 {
        if x == i64::MAX || y == i64::MAX {
            i64::MAX
        } else {
            x + y
        }
    }

    fn build_distances(n: usize, roads: &[Vec<i32>], start: usize, is_taxed: bool) -> Vec<i64> {
        let mut edge_vecs = vec![Vec::new(); n];
        for i in 0..roads.len() {
            edge_vecs[roads[i][0] as usize].push(i);
            edge_vecs[roads[i][1] as usize].push(i);
        }

        let mut distances = vec![i64::MAX; n];

        let mut pq = BinaryHeap::new();
        pq.push((Reverse(0), start));

        while let Some((Reverse(distance), node)) = pq.pop() {
            if distances[node] == i64::MAX {
                distances[node] = distance;

                for &e in &edge_vecs[node] {
                    let other = if roads[e][0] as usize == node {
                        roads[e][1]
                    } else {
                        roads[e][0]
                    } as usize;

                    if distances[other] == i64::MAX {
                        pq.push((
                            Reverse(
                                distances[node]
                                    + (roads[e][2] as i64)
                                        * ((if is_taxed { roads[e][3] } else { 1 }) as i64),
                            ),
                            other,
                        ));
                    }
                }
            }
        }

        distances
    }
}
