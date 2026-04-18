import java.util.*;

class Solution {
    public int largestRectangleArea(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;

        // main loop
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                
                int element = st.pop();   // index
                int nse = i;
                int pse = st.isEmpty() ? -1 : st.peek();

                int area = arr[element] * (nse - pse - 1);
                maxArea = Math.max(maxArea, area);
            }
            st.push(i);
        }

        // remaining elements in stack
        while (!st.isEmpty()) {
            int element = st.pop();
            int nse = n;
            int pse = st.isEmpty() ? -1 : st.peek();

            int area = arr[element] * (nse - pse - 1);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}