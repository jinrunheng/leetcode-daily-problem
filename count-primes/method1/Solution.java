class Solution {
    public int countPrimes(int n) {
        int res = 0;
        for(int i = 1; i < n; i++) {
            if(isPrime(i)){
                res++;
            }
        }
        return res;
    }
    private static boolean isPrime(int num){
        if(num <= 1){
            return false;
        }
        for(int i = 2; i * i <= num; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}