import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        int n1 = nums1.length;
        int n2 = nums2.length;
        
        int[] ans = new int[n1];
        
        for (int i = 0; i < n1; i++) {
            
            int current = nums1[i];
            int nextGreater = -1;
            
            // Step 1: Find index of nums1[i] in nums2
            int index = -1;
            for (int j = 0; j < n2; j++) {
                if (nums2[j] == current) {
                    index = j;
                    break;
                }
            }
            
            // Step 2: Search for next greater element
            for (int j = index + 1; j < n2; j++) {
                if (nums2[j] > current) {
                    nextGreater = nums2[j];
                    break;
                }
            }
            
            ans[i] = nextGreater;
        }
        
        return ans;
    }
}
