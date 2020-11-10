class Solution {
    public void nextPermutation(int[] nums) {
        //  [4,5,2,6,3,1]
        // 从后向前查找第一个顺序对 (i,i+1)，满足 a[i] < a[i+1]
        int firstIndex = nums.length - 2;
        for (; firstIndex >= 0; firstIndex--) {
            if (nums[firstIndex] < nums[firstIndex + 1]) {
                break;
            }
        }
        // 如果一直是降序排序的，那么就reverse整个数组
        if (firstIndex == -1) {
            // reverse nums
            reverse(nums,0,nums.length - 1);
            return;
        }
        int secondIndex = nums.length - 1;
        for (; secondIndex > firstIndex; secondIndex--) {
            if(nums[secondIndex] > nums[firstIndex]){
                break;
            }
        }
        swap(nums,firstIndex,secondIndex);
        reverse(nums,firstIndex + 1,nums.length - 1);
    }
    private void reverse(int[] nums,int l,int r){
        while(l < r){
            swap(nums,l++,r--);
        }
    }
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}