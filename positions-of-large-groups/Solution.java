class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int end = 0;
        while(start < s.length() && end < s.length()){
            if(s.charAt(start) == s.charAt(end)){
                end++;
            }else{
                if(end - start >= 3){
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(end - 1);
                    res.add(list);
                    start = end;
                    continue;
                }
                start++;
            }
        }
        // 处理边界情况
        // 例如 s = "aaa"
        // end已经超出边界，这时候要进行判断 end - start 是否大于等于3；
        // 如果满足要求，我们需要再加上最后的这组数据
        if(end - start >= 3){
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(end - 1);
            res.add(list);
        }
        return res;
    }
}