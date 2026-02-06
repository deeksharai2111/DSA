import java.util.Stack;

class Solution {
    public boolean isValid(String s) {

        Stack<Character> st = new Stack<>();

        // loop from i = 0 to n-1
        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // if opening bracket → push into stack
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            }
            // else it is a closing bracket
            else {

                // if stack empty → invalid
                if (st.isEmpty()) {
                    return false;
                }

                // pop top element
                char top = st.peek();
                st.pop();

                // check matching pair
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // stack should be empty at the end
        return st.isEmpty();
    }
}
