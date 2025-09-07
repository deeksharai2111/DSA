class Solution{
    private long MOD = 1_000_000_007;
    private long pow(long base, long exp){
        long result =1;
        while(exp>0){
        if((exp & 1)==1){
            
            result = (result*base)%MOD;
        }
        base = (base*base)%MOD;
        exp>>=1;
        }
        return result;
    }


public int countGoodNumbers(long n){
    long even = (n+1)/2;
    long odd = n/2;
    long res =(pow(5,even)*pow(4,odd))%MOD;
    return (int)res;
    
            }
}
   
       