class Solution {
    public boolean lemonadeChange(int[] bills) {

        int five_amount = 0;
        int ten_amount = 0;
        for(int i = 0; i < bills.length; i++){
            if(bills[i] == 5){
                five_amount++;
            }
            if(bills[i] == 10){
                if(five_amount < 1){
                    return false;
                }else{
                    five_amount--;
                    ten_amount++;
                }
            }
            if(bills[i] == 20){
                if(five_amount < 1){
                    return false;
                }else {
                    if(ten_amount < 1){
                        if(five_amount < 3){
                            return false;
                        }else {
                            five_amount -= 3;
                        }
                    }else {
                        ten_amount--;
                        five_amount--;
                    }
                }
            }
        }
        return true;
    }
}