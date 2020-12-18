class Solution {
    public char findTheDifference(String s, String t) {
        int[] map = new int[26];
        for(int i = 0; i < s.length(); i++){
            map[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < t.length(); i++){
            char ch = t.charAt(i);
            map[ch - 'a']--;
            if (map[ch - 'a'] < 0) {
                return ch;
            }
        }
        return ' ';
    }
}