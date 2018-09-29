package lishijia.bitwise

object BitWise {

  def main(args: Array[String]): Unit = {
    //0
    println(1&2)
    //1
    println(1&3)
    //2
    println(2&6)

    method1(16)
  }

  /**
    * 按位与（&）
    * 对两个数进行操作，然后返回一个新的数，这个数的每个位都需要两个输入数的同一位都为1时才为1
    */
  //实现一个方法，判断一个正整数是否是2的乘方（比如16是2的4次方，返回True；18不是2的乘方，返回False）。要求性能尽可能高。
  def method1(n:Int): Unit ={
    print(n & n-1)
  }
  def method2(): Unit ={
    println(1&3)
  }
}
