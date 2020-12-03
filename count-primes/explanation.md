## 计数质数

[204. 计数质数](https://leetcode-cn.com/problems/count-primes/)

#### 思路一：遍历

首先定义一个方法用来判断一个数字是否为质数

首先质数的定义为：

在大于1的自然数中，除了1和该数自身以外，无法被其他自然数整除的数。

不难写出判断数字是否为质数的方法：

```java
public static boolean isPrime(int num){
    if(num <= 1){
        return false;
    }
    for(int i = 2; i < n; i++){
        if(n % i == 0){
            return false;
        }
    }
    return true;
}
```

不过对于这样的方法很显然可以进行优化，在我们判断一个数的时候，只需要判断它的约数p在小于等于`Math.sqrt(n)` 即可；这样我们就将判断一个数字是否为质数这个算法的时间复杂度从`O(N)`优化到了`O(√N)`

代码如下：

```java
public static boolean isPrime(int num){
    if(num <= 1){
        return false;
    }
    for(int i = 2; i * i <= n; i++){
        if(n % i == 0){
            return false;
        }
    }
    return true;
}
```

完整代码如下：

#### 代码

```java
class Solution {
    public int countPrimes(int n) {
        int res = 0;
        for(int i = 1; i < n; i++) {
            if(isPrime(i)){
                res++;
            }
        }
        return res;
    }
    private static boolean isPrime(int num){
        if(num <= 1){
            return false;
        }
        for(int i = 2; i * i <= num; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}
```

#### 思路二：埃拉托斯特尼筛法

埃式筛法：

![Sieve_of_Eratosthenes_animation.gif](https://pic.leetcode-cn.com/1606932458-HgVOnW-Sieve_of_Eratosthenes_animation.gif)

算法流程如下：

- 声明一个长度为最大限制数的布尔数组
- 在我们遍历到这个数字的时候，就将它的所有倍数全部筛除，因为它的倍数必然不是质数

#### 代码

```java
class Solution {
    public int countPrimes(int n) {
        if(n <= 1){
            return 0;
        }
        int count = 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime,true);
        for(int i = 2; i < n; i++){
            if(isPrime[i]){
                count++;
                for(int j = i + i; j < n; j = j + i){
                    isPrime[j] = false;
                }
            }
        }
        return count;
    }
}
```



