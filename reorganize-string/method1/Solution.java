class Solution {

    class Node {
        char c;
        int count;

        public Node(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    class myComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            if(o1.count == o2.count){
                return o1.c - o2.c;
            }else{
                return o2.count - o1.count;
            }
        }
    }

    public String reorganizeString(String S) {
        
        // 最大堆
        PriorityQueue<Node> maxHeap = new PriorityQueue<>(new myComparator());
        int[] map = new int[26];
        for (char c : S.toCharArray()) {
            map[c - 'a']++;
        }
        for (int i = 0; i < map.length; i++) {
            if(map[i] != 0){
                maxHeap.add(new Node((char)(97 + i),map[i]));
            }
        }
        String res = "";
        while(res.length() != S.length()){
            Node node = maxHeap.poll();
            if(res.length() > 0 && res.charAt(res.length() - 1) == node.c){
                return "";
            }
            res += node.c;
            node.count--;
            if(!maxHeap.isEmpty()){
                Node next = maxHeap.poll();
                res += next.c;
                next.count--;
                if(next.count != 0){
                    maxHeap.add(next);
                }
            }
            
            if(node.count != 0){
                // 重新加入
                maxHeap.add(node);
            }
            
        }
        return res;
    }
}