class Solution {
    // recursion
    public List<Integer> partitionLabels(String S) {
        char[] chars = S.toCharArray();
        return partitionLabels(chars);
    }

    public List<Integer> partitionLabels(char[] chars) {
        if(chars.length == 1){
            List<Integer> res = new ArrayList<>();
            res.add(1);
            return res;
        }
        char[] subChars = new char[chars.length - 1];
        char target = chars[chars.length - 1];
        for (int i = 0; i < subChars.length; i++) {
            subChars[i] = chars[i];
        }
        List<Integer> subCharsList = partitionLabels(subChars);
        int i = 0;
        for(; i < subChars.length; i++){
            if(target == subChars[i]){
                break;
            }
        }
        if(i == subChars.length){
            List<Integer> res = new ArrayList<>();
            res.addAll(subCharsList);
            res.add(1);
            return res;
        }
        List<Integer> tmp = new ArrayList<>();
        for(int j = 0; j < subCharsList.size(); j++){
            if(j == 0){
                tmp.add(subCharsList.get(0));
            }else{
                tmp.add(tmp.get(j - 1) + subCharsList.get(j));
            }
        }
        int j = 0;
        for(; j < tmp.size(); j++){
            if(i + 1 <= tmp.get(j)){
                break;
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int k = 0;k < subCharsList.size(); k++){
            if(k == j){
                if(k == 0){
                    res.add(tmp.get(tmp.size() - 1) + 1);
                    return res;
                }else{
                    res.add(tmp.get(tmp.size() - 1) - tmp.get(k - 1) + 1);
                    break;
                }
            }else{
                res.add(subCharsList.get(k));
            }
        }
        return res;
    }
}