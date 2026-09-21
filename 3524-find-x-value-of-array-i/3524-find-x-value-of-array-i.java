class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];

        // dp[r] = number of subarrays ending at previous position
        // whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            long[] newDp = new long[k];

            int rem = num % k;

            // Start a new subarray with nums[i]
            newDp[rem]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {

                int newRem = (r * rem) % k;

                newDp[newRem] += dp[r];
            }

            // Add all subarrays ending at current position
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
    }
}