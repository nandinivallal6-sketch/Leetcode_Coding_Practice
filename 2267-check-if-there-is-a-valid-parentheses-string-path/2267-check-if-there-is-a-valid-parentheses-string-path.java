class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // A valid parentheses string must have even length
        if ((m + n - 1) % 2 == 1) {
            return false;
        }

        // dp[j][balance] = whether we can reach current cell (i,j)
        // with this balance of '(' minus ')'
        boolean[][] dp = new boolean[n][m + n];

        // Starting cell must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        dp[0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                int change = (grid[i][j] == '(') ? 1 : -1;

                boolean[] current = new boolean[m + n];

                // From top
                if (i > 0) {
                    for (int balance = 0; balance < m + n - 1; balance++) {
                        if (dp[j][balance]) {
                            int newBalance = balance + change;

                            if (newBalance >= 0) {
                                current[newBalance] = true;
                            }
                        }
                    }
                }

                // From left
                if (j > 0) {
                    for (int balance = 0; balance < m + n - 1; balance++) {
                        if (dp[j - 1][balance]) {
                            int newBalance = balance + change;

                            if (newBalance >= 0) {
                                current[newBalance] = true;
                            }
                        }
                    }
                }

                dp[j] = current;
            }
        }

        // At the destination, balance must be exactly 0
        return dp[n - 1][0];
    }
}