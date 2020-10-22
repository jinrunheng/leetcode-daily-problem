class Solution {
    public List<Integer> partitionLabels(String S) {
        // 贪心算法
        int[] last_idx = new int[26];
        for(int i = 0; i < S.length(); i++){
            last_idx[S.charAt(i) - 'a'] = i;
        }

        // 双指针start,end分别记录每个片段的首尾位置
        int start = 0;
        int end = 0;
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < S.length(); i++){
            end = Math.max(last_idx[S.charAt(i) - 'a'],end);
            if(i == end){
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }
}