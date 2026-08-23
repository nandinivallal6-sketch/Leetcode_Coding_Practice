class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int leftSum = 0;
        int rightSum = 0;
        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < half; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        for (int i = half; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        // Alice gets an extra move if total '?' is odd.
        if ((leftQ + rightQ) % 2 == 1) {
            return true;
        }

        // Difference between known sums
        int diff = leftSum - rightSum;

        // Difference in number of '?' on each side
        int qDiff = leftQ - rightQ;

        // Bob can force equality only when this condition holds.
        return diff * 2 != -9 * qDiff;
    }
}