class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // BFS
        // 如果字典里没有endWord则返回0
        if (!wordList.contains(endWord)) {
            return 0;
        }

        // BFS需要一个队列和一个Set集合
        // 队列中存放的是当前要访问的节点
        Queue<String> queue = new LinkedList<>();

        // Set用来存放已访问过的节点
        Set<String> visited = new HashSet<>();

        queue.offer(beginWord);
        visited.add(beginWord);

        int res = 0;
        while(queue.size() > 0){
            int size = queue.size();
            res++;
            for(int i = 0; i < size; i++){
                String compareWord = queue.poll();
                for(String s : wordList){
                    if (visited.contains(s)){
                        continue;
                    }
                    if(!canConvert(compareWord,s)){
                        continue;
                    }
                    if(s.equals(endWord)){
                        return ++res;
                    }
                    visited.add(s);
                    queue.offer(s);
                }

            }
        }
        return 0;
    }

    private static boolean canConvert(String s1, String s2) {
        int c = 0;
        for (int i = 0; i < s1.length() && c <= 1; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                c++;
            }
        }
        return c == 1;
    }
}