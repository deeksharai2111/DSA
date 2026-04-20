import java.util.*;

class Solution {
    public int largestRectangleArea(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {

            // Important: handle last remaining elements
            int currHeight = (i == n) ? 0 : arr[i];

            while (!st.isEmpty() && arr[st.peek()] > currHeight) {

                int element = st.peek();
                st.pop();

                int nse = i;  // next smaller element index
                int pse = st.isEmpty() ? -1 : st.peek(); // previous smaller

                int width = nse - pse - 1;
                int area = arr[element] * width;

                maxArea = Math.max(maxArea, area);
            }

            st.push(i);
        }

        return maxArea;
    }
}