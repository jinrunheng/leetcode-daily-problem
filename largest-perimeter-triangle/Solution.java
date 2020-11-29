class Solution {
    public int largestPerimeter(int[] A) {
        // sort
        // 能组成三角形的必要条件：任意两边之和都大于第三边
        Arrays.sort(A);
        for(int i = A.length - 1; i >= 2; i--){
            if(A[i - 1] + A[i - 2] > A[i]){
                return A[i] + A[i - 1] + A[i - 2];
            }
        }
        return 0;
    }
}