class Solution {
    public List<String> summaryRanges(int[] nums) {

        List<String> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            StringBuilder sb = new StringBuilder();
            while(i < nums.length - 1 && nums[i] + 1 == nums[i + 1]){
                if(sb.length() == 0){
                    sb.append(nums[i]);
                    sb.append("->");
                }
                i++;
            }
            sb.append(nums[i]);
            res.add(sb.toString());
        }
        return res;
    }
}