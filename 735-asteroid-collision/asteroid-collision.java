import java.util.*;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < asteroids.length; i++) {
            int curr = asteroids[i];

            // Case 1: moving right → push directly
            if (curr > 0) {
                st.push(curr);
            } 
            else {
                // Case 2: moving left → collision possible
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(curr)) {
                    st.pop(); // smaller asteroid explodes
                }

                // If equal size → both explode
                if (!st.isEmpty() && st.peek() == Math.abs(curr)) {
                    st.pop();
                }
                // If stack empty OR top is negative → push current
                else if (st.isEmpty() || st.peek() < 0) {
                    st.push(curr);
                }
                // Else: current asteroid gets destroyed (do nothing)
            }
        }

        // Convert stack to array
        int[] result = new int[st.size()];
        for (int i = st.size() - 1; i >= 0; i--) {
            result[i] = st.pop();
        }

        return result;
    }
}