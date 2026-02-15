class MinStack{
Stack<Long>st;
long min;
public MinStack(){
st = new Stack<>();
}
public void push(int val){
    long value = val;
    if(st.isEmpty()){
       st.push(value);
       min = value;
        }else{
            if (value >= min){
            st.push(value);
           } else{
            st.push(2*value - min);
                min = value;
            }
        }
    }
public void pop(){
    long top = st.pop();
    if (top < min){
       min = 2*min - top;
      }  
    }
public int top(){
    long top = st.peek();
    if(top >= min){
 return (int) top;
    }else{
         return (int) min;

    }
}
public int getMin(){
    return (int) min;
}
}
 
