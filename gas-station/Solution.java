class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] left = new int[gas.length];
        for(int i = 0; i < left.length; i++){
            left[i] = gas[i] - cost[i];
        }
         
        int sum = 0;
        int res = 0;
        int totalSum = 0;
        for(int i = 0; i < left.length; i++){
            totalSum += left[i];
            sum += left[i];
            if(sum < 0){
                sum = 0;
                res = i + 1;
            }
        }
        return totalSum < 0 ? -1 : res;
    }
}