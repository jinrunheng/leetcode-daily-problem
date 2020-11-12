class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int[] res = new int[A.length];
        int evenIndex = 0;
        int oddIndex = 1;
        for(int i = 0; i < A.length; i++){
            if(A[i] % 2 == 0){
                res[evenIndex] = A[i];
                evenIndex += 2;
            }else{
                res[oddIndex] = A[i];
                oddIndex += 2;
            }
        }
        return res;
    }
}