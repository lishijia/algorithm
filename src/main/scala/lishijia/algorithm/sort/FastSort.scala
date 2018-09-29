package lishijia.algorithm.sort

//有一个无序整型数组，如何求出这个数组排序后的任意两个相邻元素的最大差值？
// 要求时间和空间复杂度尽可能低。（例如：无序数组 2,3,1,4,6，排序后是1,2,3,4,6，最大差值是6-4=2）
//如果   ，即a的x次方等于N（a>0，且a≠1），那么数x叫做以a为底N的对数（logarithm），
// 记作  。其中，a叫做对数的底数，N叫做真数，x叫做“以a为底N的对数”。
object FastSort {

  def main(args: Array[String]): Unit = {
    //排序算法
    method1()
    //计数排序
    method2()
    //桶排序
    method3()
  }

  //解法三：
  //1.利用桶排序的思想，先求出原数组从最小值Min到最大值Max的单位区间长度d，d=(Max-Min)/n。算出d的作用是为了后续确定各个桶的区间范围划分。
  //2.创建一个长度是N+1的数组Array，数组的每一个元素都是一个List，代表一个桶。
  //3.遍历原数组，把原数组每一个元素插入到新数组Array对应的桶当中，进入各个桶的条件是根据不同的数值区间。
  //  由于桶的总数量是N+1，所以至少有一个桶是空的。
  //4.遍历新数组Array，计算每一个空桶右端非空桶中的最小值，与空桶左端非空桶的最大值的差，数值最大的差即为原数组排序后的相邻最大差值。
  //该解法的时间复杂度为O（n），空间复杂度同样是O（n）。
  def method3(): Unit ={
    val data = List(2,3,1,4,6,10,9,20,25,34)
    var min=Int.MaxValue
    var max=Int.MinValue
    for (x <- data){
      if(x < min){
        min = x
      }
      if(x>max){
        max=x
      }
    }
    println("min:="+min)
    println("max:="+max)

    val n = data.length;

    var d = (max-min)/n
    if((max-min)%n>0){
      d += 1
    }
    println("d := " + d)
    var newData = new Array[List[Int]](n+1)

    for (x <- data){
      var index =  x / d
      if(index==n+1){
        index = n
      }
      val list = newData(index)
      if(list == null){
        newData(index) = List(x)
      }else{
        newData(index) = x :: list
      }
    }

    var tmp:List[Int] = null
    var isNeed = false
    var result = 0;
    for(list <- newData){
      if(list!=null){

        if(isNeed){
          var resultTmp = list.sorted.head - tmp.reverse.head
          if(result<resultTmp){
            result = resultTmp;
          }
        }
        tmp = list
      }else{
        isNeed = true
      }
    }

    println("method3 result :=" + result)

  }

  //解法二：
  //1.利用计数排序的思想，先求出原数组的最大值Max与最小值Min的区间长度k（k=Max-Min+1）。
  //2.创建一个长度为k的新数组Array。
  //3.遍历原数组，把原数组每一个元素插入到新数组Array对应的位置，比如元素的值为n，则插入到Array[n-min]当中。此时Array的部分位置为空，部分位置填充了数值。
  //4.遍历新数组Array，统计出Array中最大连续出现空值的次数+1，即为相邻元素最大差值。
  //例如给定无序数组 { 2, 6, 3, 4, 5, 10, 9 }，处理过程如下图：
  //该解法的时间复杂度为O（2n+k），空间复杂度同样是O（2n+k）。
  def method2(): Unit ={
    val data = List(2,3,1,4,6,10,9,20,25,34)
    var min=Int.MaxValue
    var max=Int.MinValue
    for (x <- data){
      if(x < min){
        min = x
      }
      if(x>max){
        max=x
      }
    }
    println("min:="+min)
    println("max:="+max)
    val k= max - min + 1
    val arr = new Array[Integer](k)
    for (x <- data){
      arr(x-min)=x
    }

    var lastCount = 1
    var curCount = 1
    for(x <- arr){
      if(x == null){
        curCount += 1
      }else{
        if(curCount>lastCount){
          lastCount = curCount
        }
        curCount=1
      }
    }
    if(lastCount>curCount){
      curCount = lastCount
    }
    println("method2 result:="+curCount)
  }

  //解法一：
  //用一种较快的稳定排序算法（比如归并算法，时间复杂度N*logN）给原数组排序，然后遍历排好序的数组，每两个相邻元素求差，最终得到最大差值。
  //该解法的时间复杂度是O（N*logN），在不改变原数组的情况下，空间复杂度是O（N）。
  def method1(): Unit ={
    val data = List(2,3,1,4,6,10,9,20,25,34)
    var tmp = 0
    var result = 0
    for ( x <- data.sorted){
      val xx = x - tmp
      if(xx>result){
        result = xx
      }
      tmp =x
    }
    println("method1 result :="+result)
  }


}
