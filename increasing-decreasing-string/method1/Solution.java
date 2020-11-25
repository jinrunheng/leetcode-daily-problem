import java.util.*;

class Solution {
    public String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        Map<Character, Integer> map = new HashMap<>();
        List<Character> oddList = new ArrayList<>();

        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                oddList.add(chars[i]);
                map.put(chars[i], 1);
            } else {
                map.put(chars[i], map.get(chars[i]) + 1);
            }
        }
        List<Character> evenList = new ArrayList<>();
        for (int i = oddList.size() - 1; i >= 0; i--) {
            evenList.add(oddList.get(i));
        }
        String res = "";
        boolean isOdd = true;
        while (res.length() != s.length()) {

            if (isOdd) {
                for (Character c : oddList) {
                    if (map.get(c) > 0) {
                        res += c;
                        map.put(c, map.get(c) - 1);
                    }
                }
                isOdd = false;
            } else {
                for (Character c : evenList) {
                    if (map.get(c) > 0) {
                        res += c;
                        map.put(c, map.get(c) - 1);
                    }
                }
                isOdd = true;
            }
        }
        return res;
    }
}