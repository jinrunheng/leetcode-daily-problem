class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        String[] sortStrs = new String[strs.length];
        for(int i = 0; i < strs.length; i++){
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            sortStrs[i] = s;
        }
        Map<String,Integer> map = new HashMap<>();
        int j = 0;
        for(int i = 0; i < sortStrs.length; i++){
            if(map.isEmpty()){
                map.put(sortStrs[i],j++);
                res.add(new ArrayList<>());
            }else {
                if(!map.containsKey(sortStrs[i])){
                    map.put(sortStrs[i],j++);
                    res.add(new ArrayList<>());
                }
            }
        }
        for(int i = 0; i < sortStrs.length; i++){
            res.get(map.get(sortStrs[i])).add(strs[i]);
        }
        return res;
    }
}