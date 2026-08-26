class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();

        int left = 0;
        int ones = 0;

        int minLen = Integer.MAX_VALUE;
        String answer = "";

        for (int right = 0; right < n; right++) {

            // Add current character
            if (s.charAt(right) == '1') {
                ones++;
            }

            // If window has more than k ones,
            // move left until it has at most k ones
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // Now window has exactly k ones
            if (ones == k) {

                // Remove leading zeros because they make
                // the substring longer without changing number of ones
                while (s.charAt(left) == '0') {
                    left++;
                }

                int len = right - left + 1;
                String current = s.substring(left, right + 1);

                // Shorter is better
                if (len < minLen) {
                    minLen = len;
                    answer = current;
                }

                // Same length -> lexicographically smaller
                else if (len == minLen && current.compareTo(answer) < 0) {
                    answer = current;
                }
            }
        }

        return answer;
    }
}