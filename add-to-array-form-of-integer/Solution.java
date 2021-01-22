class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        int[] kArr = formatNumToArray(K);
        int i = A.length - 1;
        int j = kArr.length - 1;
        int c = 0;
        while(i >= 0 && j >= 0){
            
            res.add((A[i] + kArr[j] + c) % 10);
            if(A[i] + kArr[j] + c >= 10){
                c = 1;
            }else {
                c = 0;
            }
            i--;
            j--;

        }
        if(i == -1 && j == -1){
            if(c == 1){
                res.add(1);
            }
        }else if(i == -1){
            while(j >= 0){
                res.add((kArr[j] + c) % 10);
                if(kArr[j] + c >= 10){
                    c = 1;
                }else {
                    c = 0;
                }
                j--;
            }
            if(c == 1){
                res.add(1);
            }
        }else {
            while(i >= 0){
                res.add((A[i] + c) % 10);
                if(A[i] + c >= 10){
                    c = 1;
                }else {
                    c = 0;
                }
                i--;
            }
            if(c == 1){
                res.add(1);
            }
        }
        Collections.reverse(res);
        return res;
    }
    

    private static int[] formatNumToArray(int num){
        String s = "" + num;
        int[] res = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            res[i] = s.charAt(i) - '0';
        }
        return res;
    }
}