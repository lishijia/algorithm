package lishijia.algorithm

/**
  * lishijia
  * 时间复杂度
  */
object AlgorithmTime {

  def main(args: Array[String]): Unit = {
//    constantTime()
//    log2n()
    n2()
    n3()
  }

  def constantTime(): Unit ={
    //时间复杂度O(1)
    var sum = 0
    val n = 1000

    /*执行一次*/
    sum = (1 + n) * n / 2;
    /*执行一次*/
    sum = (1 + n) * n / 2;

    printf("%d",sum);
  }

  def log2n(): Unit ={
    //时间复杂度O(log2n)
    var count = 1
    val n = 1000
    while(count < n){
      count *= 2
    }
  }


  def n(): Unit ={
    //时间复杂度O(n)
    val n = 1000
    for (i <- 1 until n){
      printf("%d ",i);
    }
  }

  def nlog2n(): Unit ={
    //时间复杂度O(n * log2n)
    val n = 1000
    for (i <- 1 until n){
      var count = 1
      while(count < n){
        count *= 2
        printf("%d ",i);
      }
    }
  }

  def n2(): Unit ={
    //时间复杂度O(n^2)
    val n=10
    for (i <- 0 until n){
      for(j <- 0 until n){
        printf("%d", i)
      }
    }
  }

  def n3(): Unit ={
    //时间复杂度O(n^3)
    val n=10
    for (i <- 0 to n){
      for(j <- 0 to n){
        for(k <- 0 to(n))
        printf("%d", i)
      }
    }
  }

  def n2n(): Unit ={
    //时间复杂度O(2^n)
    val n=10
    for (i <- 0 to n){
      for(j <- 0 to n){
        for(k <- 0 to(n))
          printf("%d", i)
      }
    }
  }


}
