package com.jiarong.algorithm.lcs

/**
  * 动态规划
  */
object Lcs {

  def main(args: Array[String]): Unit = {
    val value = lcsTmp(3,1,2,10)
    println(value)
    println(this.lcs(10))
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
