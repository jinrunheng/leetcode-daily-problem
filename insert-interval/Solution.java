class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i++]);
        }
        int mergeStart = newInterval[0];
        int mergeEnd = newInterval[1];
        // 寻找重合部分的开始值和结束值
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            mergeStart = Math.min(mergeStart, intervals[i][0]);
            mergeEnd = Math.max(mergeEnd, intervals[i][1]);
            i++;
        }
        res.add(new int[]{mergeStart, mergeEnd});
        while (i < intervals.length) {
            res.add(intervals[i++]);
        }
        int[][] resArr = new int[res.size()][2];
        for (int j = 0; i < res.size(); j++) {
            resArr[j] = res.get(j);
        }
        return resArr;
    }
}