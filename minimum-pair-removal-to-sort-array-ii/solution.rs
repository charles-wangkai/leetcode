use std::collections::BTreeSet;

impl Solution {
    pub fn minimum_pair_removal(nums: Vec<i32>) -> i32 {
        let mut inversion_num = (0..nums.len() - 1)
            .filter(|&i| nums[i] > nums[i + 1])
            .count();

        let mut nodes: Vec<_> = (0..nums.len())
            .map(|i| Node {
                value: nums[i] as i64,
                prev: if i == 0 { usize::MAX } else { i - 1 },
                next: if i == nums.len() - 1 {
                    usize::MAX
                } else {
                    i + 1
                },
            })
            .collect();

        let mut elements = BTreeSet::new();
        for i in 0..nodes.len() - 1 {
            elements.insert((nodes[i].value + nodes[i + 1].value, i));
        }

        let mut result = 0;
        while inversion_num != 0 {
            let curr = elements.first().unwrap().1;
            let prev = nodes[curr].prev;
            let next = nodes[curr].next;
            let next_next = nodes[next].next;

            if nodes[curr].value > nodes[next].value {
                inversion_num -= 1;
            }
            if prev != usize::MAX && nodes[prev].value > nodes[curr].value {
                inversion_num -= 1;
            }
            if next_next != usize::MAX && nodes[next].value > nodes[next_next].value {
                inversion_num -= 1;
            }

            elements.remove(&(nodes[curr].value + nodes[next].value, curr));
            if prev != usize::MAX {
                elements.remove(&(nodes[prev].value + nodes[curr].value, prev));
            }
            if next_next != usize::MAX {
                elements.remove(&(nodes[next].value + nodes[next_next].value, next));
            }

            nodes[curr].value += nodes[next].value;
            nodes[curr].next = next_next;
            if next_next != usize::MAX {
                nodes[next_next].prev = curr;
            }

            if prev != usize::MAX && nodes[prev].value > nodes[curr].value {
                inversion_num += 1;
            }
            if next_next != usize::MAX && nodes[curr].value > nodes[next_next].value {
                inversion_num += 1;
            }

            if prev != usize::MAX {
                elements.insert((nodes[prev].value + nodes[curr].value, prev));
            }
            if next_next != usize::MAX {
                elements.insert((nodes[curr].value + nodes[next_next].value, curr));
            }

            result += 1;
        }

        result
    }
}

struct Node {
    value: i64,
    prev: usize,
    next: usize,
}
