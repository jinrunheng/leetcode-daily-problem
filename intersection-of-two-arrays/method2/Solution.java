class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        // 排序 + 双指针思路
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0;
        int p2 = 0;
        int i = 0;

        int[] intersection = new int[nums1.length + nums2.length];
        while(p1 < nums1.length && p2 < nums2.length){
            if(nums1[p1] == nums2[p2]){
                if(i == 0 || intersection[i - 1] != nums1[p1]){
                    intersection[i++] = nums1[p1];
                }
                p1++;
                p2++;
            }else if(nums1[p1] < nums2[p2]){
                p1++;
            }else{
                p2++;
            }
        }
        return Arrays.copyOfRange(intersection,0,i);
    }
}