class Solution {
    public String removeKdigits(String num, int k) {
        
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < num.length(); i++) {
            char digit = num.charAt(i);

            // Maintain increasing stack
            while (!st.isEmpty() && k > 0 && st.peek() > digit) {
                st.pop();
                k--;
            }

            st.push(digit);
        }

        // If k still remains, remove from end
        while (k > 0) {
            st.pop();
            k--;
        }

        // Build result
        StringBuilder res = new StringBuilder();
        while (!st.isEmpty()) {
            res.append(st.pop());
        }

        res.reverse();

        // Remove leading zeros
        while (res.length() > 0 && res.charAt(0) == '0') {
            res.deleteCharAt(0);
        }

        // Edge case
        return res.length() == 0 ? "0" : res.toString();
    
        
    }
}