class Solution {
    public int[] nextGreaterElements(int[] nums) {
        
        int n = nums.length;
        int[] nge = new int[n];
        
        // Initialize all values with -1
        for (int i = 0; i < n; i++) {
            nge[i] = -1;
        }
        
        // Outer loop
        for (int i = 0; i < n; i++) {
            
            // Check next n-1 elements circularly
            for (int j = i + 1; j <= i + n - 1; j++) {
                
                int ind = j % n;  // circular index
                
                if (nums[ind] > nums[i]) {
                    nge[i] = nums[ind];
                    break;
                }
            }
        }
        
        return nge;
    }
}