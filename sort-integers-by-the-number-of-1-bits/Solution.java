import java.util.*;

public class Solution {
    class MyNum {
        int original;
        int count;

        MyNum(int original, int count) {
            this.original = original;
            this.count = count;
        }
    }

    public int[] sortByBits(int[] arr) {
        Arrays.sort(arr);
        int[] res = new int[arr.length];
        List<MyNum> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new MyNum(arr[i], bitCount(arr[i])));
        }
        // Collections.sort 是稳定排序算法
        // 将map的value按照升序排序
        Collections.sort(list, new Comparator<MyNum>() {
            @Override
            public int compare(MyNum o1, MyNum o2) {
                return o1.count - o2.count;
            }
        });

        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i).original;
        }
        return res;
    }

    /*private static int bitCount(int num) {
        int res = 0;
        String s = Integer.toBinaryString(num);
        for (char c : s.toCharArray()) {
            if (c == '1') {
                res++;
            }
        }
        return res;
    }*/

    /*private static int bitCount(int num){
        int count = 0; // 计数器
        while (num > 0) {
            if((num & 1) == 1)  count++;  // 当前位是1，count++
            num >>= 1 ; // n向右移位
        }
        return count;
    }*/
    
    private static int bitCount(int num) {
        int count = 0;
        while (num != 0) {
            num &= (num - 1); // 清除最低位的1
            count++;
        }
        return count;
    }
}

