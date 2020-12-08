class Solution {
    
    public List<Integer> splitIntoFibonacci(String S) {
        int len = S.length();
        List<Integer> list = new ArrayList<>();
        return dfs(S,list,0,len) ? list : new ArrayList<>();
    }

    private boolean dfs(String S,List<Integer> list,int curIndex,int len) {
        if(curIndex == len){
            return list.size() > 2;
        }
        int num = 0;
        for(int i = curIndex; i < len; i++){
            num = 10 * num + S.charAt(i) - '0';
            // 判断num是否溢出
            if(num < 0){
                return false;
            }

            if(list.size() < 2 || num == list.get(list.size() - 1) + list.get(list.size() - 2)){
                list.add(num);
                if(dfs(S,list,i + 1,len)){
                    return true;
                }
                list.remove(list.size() - 1);
            }

            // 判断是否以0开头
            if(S.charAt(i) - '0' == 0 && i == curIndex){
                return false;
            }
        }
        return false;
    }
}