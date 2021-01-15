class Solution {
    public int removeStones(int[][] stones) {
        UnionFind uf = new UnionFind();
        for(int[] stone : stones){
            // 横坐标和纵坐标要区分开	
            uf.union(stone[0] + 10001,stone[1]);
        }
        return stones.length - uf.getCount();
    }

    class UnionFind{
        private Map<Integer,Integer> map;
        private int count;  // 联通分量的个数

        public UnionFind(){
            map = new HashMap<>();
            count = 0;
        }

        public int find(int i){
            if(!map.containsKey(i)){
                map.put(i,i);
                count++;
                return i;
            }
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
                map.put(pRoot,qRoot);
                count--;
            }
        }

        public int getCount(){
            return count;
        }
    }
}