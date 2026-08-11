class Solution {
    public int lengthOfLongestSubstring(String s) {

        int n = s.length();

        // hash[i] stores the last index where character i was seen
        int[] hash = new int[256];

        // Initially, no character has been seen
        for (int i = 0; i < 256; i++) {
            hash[i] = -1;
        }

        int l = 0;       // Left pointer
        int r = 0;       // Right pointer
        int maxLen = 0;  // Maximum length found

        while (r < n) {

            // If current character was seen before
            if (hash[s.charAt(r)] != -1) {

                // If previous occurrence is inside current window
                if (hash[s.charAt(r)] >= l) {

                    // Move left pointer after previous occurrence
                    l = hash[s.charAt(r)] + 1;
                }
            }

            // Calculate current window length
            int len = r - l + 1;

            // Update maximum length
            maxLen = Math.max(maxLen, len);

            // Store the current index of the character
            hash[s.charAt(r)] = r;

            // Move right pointer
            r++;
        }

        return maxLen;
    }
}