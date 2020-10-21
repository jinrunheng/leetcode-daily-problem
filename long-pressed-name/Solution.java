class Solution {
    public boolean isLongPressedName(String name, String typed) {
        // 双指针
        char[] nameChars = name.toCharArray();
        char[] typedChars = typed.toCharArray();
        int nameCharsIndex = 0;
        int typedCharsIndex = 0;

        while(nameCharsIndex < nameChars.length && typedCharsIndex < typedChars.length){
            
            if(nameCharsIndex + 1 < nameChars.length
                && nameChars[nameCharsIndex] != nameChars[nameCharsIndex + 1]
            ){
                while(typedCharsIndex + 1 < typedChars.length
                    && typedChars[typedCharsIndex] == typedChars[typedCharsIndex + 1]
                ){
                    typedCharsIndex++;
                }
                if(nameChars[nameCharsIndex] != typedChars[typedCharsIndex]){
                    return false;
                }
            }else{
                // 当nameCharsIndex到最后的时候,要保证typedCharsIndex也到最后
                if(nameCharsIndex == nameChars.length - 1){
                    while(typedCharsIndex + 1 < typedChars.length
                        && typedChars[typedCharsIndex] == typedChars[typedCharsIndex + 1]
                    ){
                        typedCharsIndex++;
                    }
                }
                if(nameChars[nameCharsIndex] != typedChars[typedCharsIndex]){
                    return false;
                }
            }
            typedCharsIndex++;
            nameCharsIndex++;
        }
        return nameCharsIndex == nameChars.length 
                && typedCharsIndex == typedChars.length;
    }
}