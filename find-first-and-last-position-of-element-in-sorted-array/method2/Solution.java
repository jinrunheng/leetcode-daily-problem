class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[]{-1,-1};
        }
        int l = searchLeftIndex(nums,target);
        int r = searchRightIndex(nums,target) - 1;
        if(l <= r && r < nums.length && nums[l] == target && nums[r] == target){
            return new int[]{l,r};
        }
        return new int[]{-1,-1};
    }

    // 寻找数组中第一个大于等于target的下标
    private static int searchLeftIndex(int[] nums,int target){
        int l = 0;
        int r = nums.length - 1;
        int ans = nums.length;
        while(l <= r){
            int mid = l + ((r - l) >> 1);
            if(nums[mid] >= target){
                r = mid - 1;
                ans = mid;
            }else {
                l = mid + 1;
            }
        }
        return ans;
    }

    // 寻找数组中第一个大于target的下标
    private static int searchRightIndex(int[] nums,int target){
        int l = 0;
        int r = nums.length - 1;
        int ans = nums.length;
        while(l <= r){
            int mid = l + ((r  - l) >> 1);
            if(nums[mid] > target){
                r = mid - 1;
                ans  = mid;
            }else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
