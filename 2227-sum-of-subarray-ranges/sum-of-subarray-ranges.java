class Solution{
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long minSum = 0, maxSum = 0;
        Stack<Integer> st = new Stack<>();
        // ---------- MINIMUM ----------
        int[] pse = new int[n];
        int[] nse = new int[n];
        // PSE (Previous Smaller Element)
        
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] > nums[i]) {
                st.pop();
            }
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        // NSE (Next Smaller Element)
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
       // Calculate min contribution
        for (int i = 0; i < n; i++) {
            long left = i - pse[i];
            long right = nse[i] - i;
            minSum += left * right * nums[i];
        }
        // ---------- MAXIMUM ----------
        int[] pge = new int[n];
        int[] nge = new int[n];
        // PGE (Previous Greater Element)
        st.clear();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
                st.pop();
            }
            pge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        // NGE (Next Greater Element)
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] <= nums[i]) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        // Calculate max contribution
        for (int i = 0; i < n; i++) {
            long left = i - pge[i];
            long right = nge[i] - i;
            maxSum += left * right * nums[i];
        }
         return maxSum - minSum;
    }
}