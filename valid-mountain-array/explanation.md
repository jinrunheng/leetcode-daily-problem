## 有效的山脉数组

#### 方法一：遍历

遍历数组；使用变量isTop用来表示是否到达了山脉顶峰，false表示未达到，true表示已达到

列举几种case:

1. 山脉数组一开始就是下落的趋势，直接返回false
2. 山脉数组的相邻山脉相等，直接返回false
3. 山脉数组先增后减，则说明已经到达了山脉顶峰，如果再次出现上升的情况，则返回false

##### 代码如下：

##### Java

```java
class Solution {
    public boolean validMountainArray(int[] A) {
        // 用来表示是否到达了山峰，false表示未达到，true表示已达到
        boolean isTop = false;
        int pre = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
            if(A[i] > pre){
                if(isTop){
                    return false;
                }
                pre = A[i];
            } else if(A[i] < pre){
                // 如果最开始就进入递减状态，则返回false
                if(i == 1){
                    return false;
                }
                isTop = true;
                pre = A[i];
            }else {
                return false;
            }
        }
        return isTop;
    }
}
```

##### Go

```go
func validMountainArray(A []int) bool {
	var isTop = false
	var pre = math.MinInt64
	for i := 0; i < len(A); i++ {
		if A[i] > pre {
			if isTop {
				return false
			}
			pre = A[i]
		} else if A[i] < pre {
			if i == 1 {
				return false
			}
			isTop = true
			pre = A[i]
		} else {
			return false
		}
	}
	return isTop
}
```

#### 方法二：双指针

- 使用两个指针，头指针从左至右遍历；尾指针从右至左遍历
- 头指针p1在满足依次递增且不越界的情况下向右移动
- 尾指针p2也一样，在满足依次递增且不越界的情况下向左移动
- 当p1移动到尾部，或者是p2移动到头部，则说明山脉数组是单调递增或递减的，返回false
- 当p1等于p2，说明满足山脉数组的先增再减的趋势，返回true

##### 代码

##### Java

```java
class Solution {
    public boolean validMountainArray(int[] A) {
        int p1 = 0;
        int p2 = A.length - 1;
        while(p1 < A.length - 1 && A[p1] <  A[p1 + 1]){
            p1++;
        }
        while(p2 > 0 && A[p2] < A[p2 - 1]){
            p2--;
        }
        return p1 != A.length-1 && p2 != 0 && p1 == p2;
    }
}
```

##### Go

```go
func validMountainArray(A []int) bool {
	p1 := 0
	p2 := len(A) - 1
	for p1 < len(A)-1 && A[p1] < A[p1+1] {
		p1++
	}
	for p2 > 0 && A[p2] < A[p2-1] {
		p2--
	}
	return p1 != len(A)-1 && p2 != 0 && p1 == p2
}
```

