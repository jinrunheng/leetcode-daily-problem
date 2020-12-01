class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[]{-1,-1};
        }
        int l = -1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                l = i;
                break;
            }
        }
        // 如果数组中没有等于target的数字
        if(l == -1){
            return new int[]{-1,-1};
        }
        int r =  l;
        for(int i =  l; i < nums.length; i++){
            if(nums[i] != target){
                r = i - 1;
                break;
            }else  {
                r = i;
            }
        }
        return new int[]{l,r};
    }
}