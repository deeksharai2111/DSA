class Solution {
    public int minBitFlips(int start, int goal) {
        int xorValue = start ^ goal;
        
        // Count how many 1s are present → number of flips needed
        int count = 0;

        // check all 32 bits of an integer
        for (int i = 0; i < 32; i++) {
            // mask to check ith bit
            if ((xorValue & (1 << i)) != 0) {
                count++;
            }
        }

        return count;
    }
}