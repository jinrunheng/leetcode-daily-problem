class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if(pairs.size() == 0){
            return s;
        }
        UnionFind uf = new UnionFind(s.length());
        for(List<Integer> pair : pairs){
            uf.union(pair.get(0),pair.get(1));
        }

        Map<Integer,PriorityQueue<Character>> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            int root = uf.find(i);
            if(map.containsKey(root)){
                map.get(root).offer(s.charAt(i));
            }else {
                PriorityQueue<Character> minHeap = new PriorityQueue<>();
                minHeap.offer(s.charAt(i));
                map.put(root,minHeap);
            }
        } 

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            int root = uf.find(i);
            sb.append(map.get(root).poll());
        }
        return sb.toString();  
    }

    class UnionFind{
        public Map<Integer,Integer> map;
        public Map<Integer,Integer> size;
        public UnionFind(int n){
            map = new HashMap<>();
            size = new HashMap<>();
            for(int i = 0; i < n; i++){
                map.put(i,i);
                size.put(i,1);
            }
        }

        // 找到节点的头节点
        public int find(int i){
            int root = map.get(i);
            if(i != root){
                root = find(root);
            }
            map.put(i,root);
            return root;
        }

        public void union(int p,int q){
            int pRoot = find(p);
            int qRoot = find(q);
            if(pRoot != qRoot){
                int pSize = size.get(pRoot);
                int qSize = size.get(qRoot);

                if(pSize < qSize){
                    map.put(pRoot,qRoot);
                    size.put(qRoot,pSize + qSize);
                }else {
                    map.put(qRoot,pRoot);
                    size.put(pRoot,qSize + pSize);
                }
            }
        }
    }
}