package com.jiarong.algorithm.lcs

/**
  * 动态规划
  */
object Lcs {

  def main(args: Array[String]): Unit = {
    val value = lcsTmp(3,1,2,10)
    println(value)
    println(this.lcs(10))
    lcs1()


  }


  def lcs1(): Unit ={
    val a = "BDCABA"
    val b = "ABCBDAB"
    val n = a.length
    val m = b.length

    val data = Array.ofDim[Int](n+1, m+1)
    for(i <- 0 until data.length){
      for (j <- 0 until data(i).length){
        print(data(i)(j))
      }
      println()
    }
    println("kkkk")
    for(i <- 1 until data.length){
      for (j <- 1 until data(i).length){
        if(a(i-1)==b(j-1)){
          data(i)(j) = data(i-1)(j-1) + 1
        }else{
          data(i)(j) = data(i-1)(j).max(data(i)(j-1))
        }
      }
    }
    for(i <- 0 until data.length){
      for (j <- 0 until data(i).length){
        print(data(i)(j))
      }
      println()
    }
    println (data(n)(m)*2/(m+n).toDouble)
    println(data(n)(m)/(m+n/2.0))
    println(data(n)(m))
  }

  //多维数组
  def dim2f(rows : Int, cols : Int):Array[Array[Float]]={
    val d2 :Array[Array[Float]] = new Array(rows)
    for (k <-0 until rows ) {
      d2(k) = new Array[Float](cols)
    }
    d2
  }


  def lcs(n:Int): Int ={
    if(n<=0){
      return 0
    }
    if(n==1){
      return 1
    }
    if(n==2){
      return 2
    }
    var first=1
    var second=2
    var cur = 0
    for(start <- 3 to n){
      cur= first + second;
      first = second;
      second = cur
    }
    cur
  }

  def lcsTmp(cur:Int, curFirst:Int, curSecond:Int, end:Int): Int ={

    if(end<=0){
      return 0
    }

    if(end==1){
      return 1
    }

    if(end==2){
      return 2
    }

    var tmpCurFirst=0
    var tmpCurSecond=0
    if(cur==end){
      return curFirst+curSecond
    }else{
      tmpCurFirst=curSecond
      tmpCurSecond=curFirst+curSecond
    }
    this.lcsTmp(cur+1, tmpCurFirst, tmpCurSecond, end)
  }

}
