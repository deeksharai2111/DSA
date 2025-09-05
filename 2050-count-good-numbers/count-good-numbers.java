class Solution {
    static final int MOD = 1_000_000_007;

    // Fast power function (binary exponentiation)
    public long pow(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {   // if exp is odd
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD; // square the base
            exp >>= 1; // divide exp by 2
        }
        return result;
    }

    // Function to count good numbers of length n
    public int countGoodNumbers(long n) {
        long evenCount = (n + 1) / 2; // even index positions (0, 2, 4, ...)
        long oddCount = n / 2;        // odd index positions (1, 3, 5, ...)
        long res = (pow(5, evenCount) * pow(4, oddCount)) % MOD;
        return (int) res;
    }
}
