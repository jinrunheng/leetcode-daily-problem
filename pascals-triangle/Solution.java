class Solution {
    public List<List<Integer>> generate(int numRows) {
        if(numRows == 0){
            List<List<Integer>> res = new ArrayList<>();
            return res;
        }
        if(numRows == 1){
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            list.add(1);
            res.add(list);
            return res;
        }
        // recursion
        List<List<Integer>> parent = generate(numRows - 1);
        List<Integer> lastList = parent.get(parent.size() - 1);
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            if(i == 0 || i == numRows - 1){
                list.add(1);
            }else{
                list.add(lastList.get(i - 1) + lastList.get(i));
            }
        }
        parent.add(list);
        return parent;
    }
}