class Solution {
    public int matrixScore(int[][] A) {
        for(int i = 0; i < A.length; i++){
            if(A[i][0] == 0){
                reverse(A[i]);
            }
        }

        for(int i = 1; i < A[0].length; i++){
            reverseCol(A,i);
        }
        int res = 0;
        for(int i = 0; i < A.length; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < A[0].length; j++){
                sb.append(A[i][j]);
            }
            res += Integer.parseInt(sb.toString(),2);
        }
        return res;
    }

    private static void reverseCol(int[][] A,int colIndex ){
        int res = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i][colIndex] == 0){
                res++;
            }
        }
        if(res > A.length / 2){
            for(int i = 0; i < A.length; i++){
                if(A[i][colIndex] == 0){
                    A[i][colIndex] = 1;
                }else {
                    A[i][colIndex] = 0;
                }
            }
        }
    }

    private static void reverse(int[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 0){
                arr[i] = 1;
            }else {
                arr[i] = 0;
            }
        }
    }
}