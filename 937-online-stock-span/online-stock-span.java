class StockSpanner {
    Stack<int[]> stack = new Stack<>();  // declare + create in one line

    // constructor is now REMOVED because stack is already created above
    
    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.peek()[1];
            stack.pop();
        }
        stack.push(new int[]{price, span});
        return span;
    }
}