class Solution {
    public int minBitFlips(int start, int goal) {
        int xorValue = start ^ goal;
        
        // Count how many 1s are present → number of flips needed
        return Integer.bitCount(xorValue);
    }
}