class Solution {
    public boolean validMountainArray(int[] A) {
        int p1 = 0;
        int p2 = A.length - 1;
        while(p1 < A.length - 1 && A[p1] <  A[p1 + 1]){
            p1++;
        }
        while(p2 > 0 && A[p2] < A[p2 - 1]){
            p2--;
        }
        return p1 != A.length-1 && p2 != 0 && p1 == p2;
    }
}