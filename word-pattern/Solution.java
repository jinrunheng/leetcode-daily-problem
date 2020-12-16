class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] strings = s.split(" ");
        Map<Character,String> map1 = new HashMap<>();
        Map<String,Character> map2 = new HashMap<>();
        
        if(strings.length != pattern.length()){
            return false;
        }
        
        for(int i = 0; i < strings.length; i++){

            if(!map1.containsKey(pattern.charAt(i)) && !map2.containsKey(strings[i])){
                map1.put(pattern.charAt(i),strings[i]);
                map2.put(strings[i],pattern.charAt(i));
            }else if(!map1.containsKey(pattern.charAt(i)) && map2.containsKey(strings[i])
            || !map2.containsKey(strings[i]) && map1.containsKey(pattern.charAt(i))){
                return false;
            }else {
                if(!map1.get(pattern.charAt(i)).equals(strings[i])){
                    return false;
                }
            }
        }
        return true;
    }
}