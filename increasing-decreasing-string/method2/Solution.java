class Solution {
    public String sortString(String s) {
        int[] bucket = new int[26];
        for(int i = 0; i < s.length(); i++){
            bucket[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        boolean isOdd = true;
        while(sb.length() != s.length()){
            if(isOdd){
                for(int i = 0; i < 26; i++){
                    if(bucket[i] > 0){
                        sb.append((char)(i + 'a'));
                        bucket[i]--;
                    }
                }
                isOdd = false;
            }else {
                for(int i = 25; i >= 0; i--){
                    if(bucket[i] > 0){
                        sb.append((char)(i + 'a'));
                        bucket[i]--;
                    }
                }
                isOdd = true;
            }
        }
        return sb.toString();
    }
}