class Solution {
    public int firstUniqChar(String s) {
        int[] map1 = new int[26]; // 存放每个字符的位置
        int[] map2 = new int[26]; // 存放每个字符出现的个数

        for(int i = 0; i < s.length(); i++){
            map1[s.charAt(i) - 'a'] = i;
            map2[s.charAt(i) - 'a']++;
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < map1.length; i++){
            if(map2[i] == 1){
                res = Math.min(map1[i],res);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;

    }
}