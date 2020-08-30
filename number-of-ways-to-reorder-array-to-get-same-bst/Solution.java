import java.math.BigInteger;

class Solution {
    static final int MODULUS = 1_000_000_007;

    static int wayNum;

    public int numOfWays(int[] nums) {
        Node root = new Node(nums[0]);
        for (int i = 1; i < nums.length; ++i) {
            insert(root, nums[i]);
        }

        wayNum = 1;
        search(root);

        return subtractMod(wayNum, 1);
    }

    void insert(Node node, int value) {
        if (value < node.value) {
            if (node.left == null) {
                node.left = new Node(value);
            } else {
                insert(node.left, value);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(value);
            } else {
                insert(node.right, value);
            }
        }
    }

    int search(Node node) {
        if (node == null) {
            return 0;
        }

        int size = 1;
        int leftSize = search(node.left);
        int rightSize = search(node.right);

        wayNum = multiplyMod(wayNum, C(leftSize + rightSize, leftSize));

        return leftSize + rightSize + 1;
    }

    int subtractMod(int x, int y) {
        return (x - y + MODULUS) % MODULUS;
    }

    int multiplyMod(int x, int y) {
        return (int) ((long) x * y % MODULUS);
    }

    int C(int n, int r) {
        int result = 1;
        for (int i = 0; i < r; ++i) {
            result = multiplyMod(result,
                    multiplyMod(n - i, BigInteger.valueOf(i + 1).modInverse(BigInteger.valueOf(MODULUS)).intValue()));
        }

        return result;
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
}