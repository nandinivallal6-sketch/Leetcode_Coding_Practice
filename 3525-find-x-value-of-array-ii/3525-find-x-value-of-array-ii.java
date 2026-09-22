class Solution {

    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int n;
    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update nums[index]
            update(1, 0, n - 1, index, value % k);

            // Query [start, n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            answer[i] = res.cnt[x];
        }

        return answer;
    }

    // Build segment tree
    void build(int node, int left, int right, int[] nums) {

        if (left == right) {

            int rem = nums[left] % k;

            tree[node] = new Node(k);

            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = (left + right) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two nodes
    Node merge(Node a, Node b) {

        if (a == null)
            return b;

        if (b == null)
            return a;

        Node result = new Node(k);

        // Product of complete segment
        result.prod = (int) ((long) a.prod * b.prod % k);

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            result.cnt[r] = a.cnt[r];
        }

        // Prefixes containing all of left + prefix of right
        for (int r = 0; r < k; r++) {

            int newRem = (int) ((long) a.prod * r % k);

            result.cnt[newRem] += b.cnt[r];
        }

        return result;
    }

    // Point update
    void update(int node, int left, int right,
                int index, int value) {

        if (left == right) {

            tree[node] = new Node(k);

            tree[node].prod = value;
            tree[node].cnt[value] = 1;

            return;
        }

        int mid = (left + right) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Range query
    Node query(int node, int left, int right,
               int ql, int qr) {

        if (qr < left || right < ql) {
            return null;
        }

        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = (left + right) / 2;

        Node leftNode =
            query(node * 2, left, mid, ql, qr);

        Node rightNode =
            query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(leftNode, rightNode);
    }
}