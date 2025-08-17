use std::{cmp::Reverse, collections::BinaryHeap};

impl Solution {
    pub fn min_cost(grid: Vec<Vec<i32>>, k: i32) -> i32 {
        let m = grid.len();
        let n = grid[0].len();

        let mut points = Vec::new();
        for r in 0..m {
            for c in 0..n {
                points.push(Point { r, c });
            }
        }
        points.sort_by_key(|point| grid[point.r][point.c]);

        let mut indices = vec![0; k as usize];

        let mut costs = vec![vec![vec![i32::MAX; (k + 1) as usize]; n]; m];
        let mut pq = BinaryHeap::new();
        Self::update(&mut costs, &mut pq, 0, 0, k as usize, 0);

        while let Some((Reverse(cost), r, c, teleport_rest)) = pq.pop() {
            if costs[r][c][teleport_rest] == cost {
                if r != m - 1 {
                    Self::update(
                        &mut costs,
                        &mut pq,
                        r + 1,
                        c,
                        teleport_rest,
                        cost + grid[r + 1][c],
                    );
                }
                if c != n - 1 {
                    Self::update(
                        &mut costs,
                        &mut pq,
                        r,
                        c + 1,
                        teleport_rest,
                        cost + grid[r][c + 1],
                    );
                }

                if teleport_rest != 0 {
                    while indices[teleport_rest - 1] != points.len()
                        && grid[points[indices[teleport_rest - 1]].r]
                            [points[indices[teleport_rest - 1]].c]
                            <= grid[r][c]
                    {
                        Self::update(
                            &mut costs,
                            &mut pq,
                            points[indices[teleport_rest - 1]].r,
                            points[indices[teleport_rest - 1]].c,
                            teleport_rest - 1,
                            cost,
                        );

                        indices[teleport_rest - 1] += 1;
                    }
                }
            }
        }

        costs[m - 1][n - 1].iter().min().copied().unwrap()
    }

    fn update(
        costs: &mut [Vec<Vec<i32>>],
        pq: &mut BinaryHeap<(Reverse<i32>, usize, usize, usize)>,
        r: usize,
        c: usize,
        teleport_rest: usize,
        cost: i32,
    ) {
        if cost < costs[r][c][teleport_rest] {
            costs[r][c][teleport_rest] = cost;
            pq.push((Reverse(cost), r, c, teleport_rest));
        }
    }
}

struct Point {
    r: usize,
    c: usize,
}
