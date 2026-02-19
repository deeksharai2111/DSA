import java.util.*;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        
        int n = nums.length;
        int[] nge = new int[n];
        Stack<Integer> st = new Stack<>();
        
        // Traverse from 2n-1 to 0
        for (int i = 2 * n - 1; i >= 0; i--) {
            
            // Maintain decreasing stack
            while (!st.isEmpty() && st.peek() <= nums[i % n]) {
                st.pop();
            }
            
            // Fill answer only for first n elements
            if (i < n) {
                if (st.isEmpty()) {
                    nge[i] = -1;
                } else {
                    nge[i] = st.peek();
                }
            }
            
            // Push current element
            st.push(nums[i % n]);
        }
        
        return nge;
    }
}