class Solution {
    public String predictPartyVictory(String senate) {
        // RDDRDRD
        // [0] -> [1] 失权  RXDRDRD
        // [2] -> [3] 失权  RXDXDRD
        // [4] -> [5] 失权  RXDXDXD
        // [5] -> [0] 失权  XXDXDXD
        // 第二轮Dire直接宣布胜利

        // 贪心原则：
        // 优先让下一个非自己一组的人失权
        // 例如:DDRR [0] -> [2] 失权
        
        Queue<Integer> radiant_queue = new LinkedList<>();
        Queue<Integer> dire_queue = new LinkedList<>();

        for(int i = 0; i < senate.length(); i++){
            if(senate.charAt(i) == 'R'){
                radiant_queue.offer(i);
            }else {
                dire_queue.offer(i);
            }
        }

        while(!radiant_queue.isEmpty() && !dire_queue.isEmpty()){
            if(radiant_queue.peek() < dire_queue.peek()){
                dire_queue.poll();
                radiant_queue.offer(radiant_queue.poll() + senate.length());
            }else {
                radiant_queue.poll();
                dire_queue.offer(dire_queue.poll() + senate.length());
            }
        }

        return radiant_queue.isEmpty() ? "Dire" : "Radiant";
    }
}