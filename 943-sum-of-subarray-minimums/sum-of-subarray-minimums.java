import java.util.*;

class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long mod = 1000000007;

        int[] prevLess = new int[n];
        int[] nextLess = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Previous Less Element
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            prevLess[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Next Less Element
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            nextLess[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        // Calculate answer
        long sum = 0;

        for (int i = 0; i < n; i++) {
            long left = i - prevLess[i];
            long right = nextLess[i] - i;
            long contribution = (left * right) % mod * arr[i] % mod;
            sum = (sum + contribution) % mod;
        }

        return (int) sum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] arr1 = {3, 1, 2, 4};
        System.out.println(sol.sumSubarrayMins(arr1)); // Output: 17

        int[] arr2 = {11, 81, 94, 43, 3};
        System.out.println(sol.sumSubarrayMins(arr2)); // Output: 444
    }
}