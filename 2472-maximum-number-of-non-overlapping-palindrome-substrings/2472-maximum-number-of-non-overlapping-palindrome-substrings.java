class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int ans = 0;
        int lastEnd = -1; // Tracks the end index of the last selected palindrome
        
        // Total 2*n - 1 centers: center / 2 and center / 2 + center % 2
        for (int center = 0; center < 2 * n - 1; center++) {
            int l = center / 2;
            int r = l + (center % 2);
            
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                int length = r - l + 1;
                
                // Check if we hit a valid length (at least k)
                if (length >= k) {
                    if (l > lastEnd) {
                        ans++;
                        lastEnd = r;
                        break; // Stop expanding greedily to keep the substring as short as possible
                    }
                }
                
                l--;
                r++;
            }
        }
        
        return ans;
    }
}