class Solution {
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> res = new ArrayList<>();
        int t = 0;
        for(int i = 0; i < A.length; i++){
            // 仅仅保留最后一位数
            t = (t + A[i]) % 10;
            res.add(t == 0 || t == 5);
            t = t << 1;
        }
        return res;
    }
}