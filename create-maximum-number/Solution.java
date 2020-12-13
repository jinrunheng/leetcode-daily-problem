class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        // dp[i] -> 在[0,i) 各位数字都不同都数字
        // dp[i] = dp[i - 1] + isNumberWithUniqueDigist(i - 1) ? 1 : 0
        int[] dp = new int[Math.pow(10,n)];
    }

    public static void main(String[] args) {
        int[] dp = new int[Math.pow(10,3)];
        System.out.println(db.length);
    }
}