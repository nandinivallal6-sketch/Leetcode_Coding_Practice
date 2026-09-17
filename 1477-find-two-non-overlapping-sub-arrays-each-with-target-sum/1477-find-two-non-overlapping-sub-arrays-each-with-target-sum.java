import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = n + 1;

        // best[i] = minimum length of a valid subarray
        // completely inside arr[0 ... i-1]
        int[] best = new int[n + 1];
        Arrays.fill(best, INF);

        int ans = INF;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // Since all arr[i] are positive, move left
            // while sum is greater than target.
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // We found a subarray [left ... right]
            if (sum == target) {
                int len = right - left + 1;

                // best[left] contains the shortest valid
                // subarray ending before 'left'
                if (best[left] != INF) {
                    ans = Math.min(ans, len + best[left]);
                }

                // Update best for prefix ending at 'right'
                best[right + 1] = Math.min(best[right], len);
            } else {
                // No new valid subarray ending at right
                best[right + 1] = best[right];
            }
        }

        return ans == INF ? -1 : ans;
    }
}