class Solution {
    public void rotate(int[] nums, int k) {
        reverse(nums,0,nums.length - 1);
        reverse(nums,0,k % nums.length - 1);
        reverse(nums,k % nums.length,nums.length - 1);
    }

    private static void reverse(int[] nums,int start,int end){
        while(start < end){
            swap(nums,start++,end--);
        }
    }

    private static void swap(int[] nums,int i,int j){
        if(i == j){
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}