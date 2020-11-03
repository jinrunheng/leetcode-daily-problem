class Solution {
    public boolean validMountainArray(int[] A) {
        // 用来表示是否到达了山峰，false表示未达到，true表示已达到
        boolean isTop = false;
        int pre = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
            if(A[i] > pre){
                if(isTop){
                    return false;
                }
                pre = A[i];
            } else if(A[i] < pre){
                // 如果最开始就进入递减状态，则返回false
                if(i == 1){
                    return false;
                }
                isTop = true;
                pre = A[i];
            }else {
                return false;
            }
        }
        return isTop;
    }
}