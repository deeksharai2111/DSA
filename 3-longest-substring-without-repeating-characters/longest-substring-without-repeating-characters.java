class Solution {
    public int lengthOfLongestSubstring(String s) {

        int[] hash = new int[256];

        // Initialize all values to -1
        for (int i = 0; i < 256; i++) {
            hash[i] = -1;
        }

        int left = 0;
        int right = 0;
        int maxLen = 0;

        while (right < s.length()) {

            // If character was seen before
            if (hash[s.charAt(right)] != -1) {

                // If previous occurrence is inside current window
                if (hash[s.charAt(right)] >= left) {
                    left = hash[s.charAt(right)] + 1;
                }
            }

            // Store latest index of current character
            hash[s.charAt(right)] = right;

            // Calculate current window length
            int len = right - left + 1;

            // Update maximum length
            maxLen = Math.max(maxLen, len);

            // Move right pointer
            right++;
        }

        return maxLen;
    }
}