class Solution {
    public int monotoneIncreasingDigits(int N) {
        int p = findIncreasingPosition(N);
        if(p == -1){
            return N;
        }
        char[] chars = String.valueOf(N).toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean find = false;
        for(int i = 0; i < chars.length; i++){
            if(i == p){
                sb.append(chars[i] - '1');
                find = true;
                continue;
            }
            if(find){
                sb.append('9');
            }else {
                sb.append(chars[i]);
            }
        }
        if(sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }
        
        return Integer.parseInt(sb.toString());
    }

    private static int findIncreasingPosition(int N){
        char[] chars = String.valueOf(N).toCharArray();
        
        for(int i = 0; i < chars.length - 1; i++){
            
            if(chars[i] > chars[i + 1]){
                int j = i;
                while(j > 0 && chars[j] == chars[j - 1]){
                    j--;
                }
                return j;
            }
            
        }
        return -1;
    }
}