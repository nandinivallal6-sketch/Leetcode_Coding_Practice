class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;

        long dp = 1; // empty subsequence

        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int idx = c - 'a';

            long newDp = (2 * dp % MOD - last[idx] + MOD) % MOD;

            last[idx] = dp;
            dp = newDp;
        }

        return (int) ((dp - 1 + MOD) % MOD);
    }
}