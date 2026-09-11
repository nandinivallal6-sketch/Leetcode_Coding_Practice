class Solution {
    public int totalNumbers(int[] digits) {
        boolean[] seen = new boolean[1000];

        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                for (int k = 0; k < digits.length; k++) {

                    // Cannot use the same array element twice
                    if (i == j || i == k || j == k) {
                        continue;
                    }

                    // No leading zero
                    if (digits[i] == 0) {
                        continue;
                    }

                    // Number must be even
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int num = digits[i] * 100
                            + digits[j] * 10
                            + digits[k];

                    seen[num] = true;
                }
            }
        }

        int count = 0;

        for (int num = 100; num <= 999; num++) {
            if (seen[num]) {
                count++;
            }
        }

        return count;
    }
}