import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        // Step 1: Map to store next greater of each element in nums2
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Step 2: Stack for monotonic decreasing stack
        Stack<Integer> st = new Stack<>();
        
        // Step 3: Traverse nums2 from right to left
        for (int i = nums2.length - 1; i >= 0; i--) {
            
            // Pop smaller elements
            while (!st.isEmpty() && st.peek() <= nums2[i]) {
                st.pop();
            }
            
            // If stack empty → no greater element
            if (st.isEmpty()) {
                map.put(nums2[i], -1);
            } else {
                map.put(nums2[i], st.peek());
            }
            
            // Push current element
            st.push(nums2[i]);
        }
        
        // Step 4: Build result for nums1
        int[] ans = new int[nums1.length];
        
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        
        return ans;
    }
}
