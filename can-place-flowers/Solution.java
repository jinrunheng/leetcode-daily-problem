class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int max = 0;
        if(n == 0){
            return true;
        }
        if(flowerbed.length == 1){
            return flowerbed[0] == 0 && n == 1;
        }
        
        for(int i = 0; i < flowerbed.length; i++){
            if(i == 0){
                if(flowerbed[0] == 0 && flowerbed[1] == 0){
                    flowerbed[0] = 1;
                    max++;
                }
            }else if(i == flowerbed.length - 1){
                if(flowerbed[i] == 0 && flowerbed[i - 1] == 0){
                    flowerbed[i] = 1;
                    max++;
                }
            }else {
                if(flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0){
                    flowerbed[i] = 1;
                    max++;
                }
            }

            if(max >= n){
                return true;
            }
        }
        return max >= n;
    }
}