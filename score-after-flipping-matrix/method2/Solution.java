class Solution {
    public int matrixScore(int[][] A) {
        int res = A.length * (1 << A[0].length - 1); // 第一位均为1
        for(int i = 1; i < A[0].length; i++){
            int countOf1 = 0;
            for(int j = 0; j < A.length; j++){
                if(A[j][i] == A[j][0]){ // 1的个数
                    countOf1++;
                }
            }
            res += Math.max(countOf1,A.length - countOf1) * (1 << (A[0].length - 1 - i));
        }
        return res;
    }
}