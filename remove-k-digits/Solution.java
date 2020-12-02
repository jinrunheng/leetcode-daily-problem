class Solution {
    public String removeKdigits(String num, int k) {
        // 单调栈:维护一个单调递增的栈
        if(k == num.length()){
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < num.length(); i++){
            while(!stack.isEmpty() && k > 0 && num.charAt(i) < stack.peek()){
                stack.pop();
                k--;
            }
            if(stack.isEmpty() && num.charAt(i) == '0'){
                continue;
            }
            stack.push(num.charAt(i));
        }
        while(k > 0){
            stack.pop();
            k--;
        }
        // num = "10"
        if(stack.isEmpty()){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}