class Solution {
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0){
            return 0;
        }
        int[] candies = new int[ratings.length];
        Arrays.fill(candies,1);

        // 先正序遍历，如果后一位比前一位的分数高，那么就给后一位比前一位多一个糖果
        for(int i = 0; i < ratings.length - 1; i++){
            if(ratings[i + 1] > ratings[i]){
                candies[i + 1] = candies[i] + 1;
            }
        }
        // 倒序遍历，如果前一位比后一位的分数高，并且糖果数量还没有后一位多，就给前一位比后一位多一个糖果
        for(int i = ratings.length - 1; i > 0; i--){
            if(ratings[i - 1] > ratings[i] && candies[i - 1] <= candies[i]){
                candies[i - 1] = candies[i] + 1;
            }
        }

        int res = 0;
        for(int c : candies){
            res += c;
        }
        return res;
    }
}