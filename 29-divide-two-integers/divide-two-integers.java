class Solution{
     public int divide(int dividend, int divisor){
     if(dividend == divisor)
     return 1;
     boolean sign = true;
     if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0))
        sign =  false;
        
        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long quotient = 0;
        while (n >= d){
            int cnt = 0;
            while (n >= (d << (cnt + 1))) {
                cnt++;
            }
        quotient += (1L << cnt);
        n -= (d << cnt);
        }
        if (quotient > Integer.MAX_VALUE) {
        return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return (int) (sign ? quotient : - quotient);
     
}
}