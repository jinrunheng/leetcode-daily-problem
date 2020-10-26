class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] tmp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(tmp);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = tmp.length - 1; i >= 0; i--) {
            map.put(tmp[i], i);
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = map.get(nums[i]);
        }
        return res;
    }
}