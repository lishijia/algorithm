package lishijia.algorithm.skiplist

import scala.util.Random

/**
  * 跳跃链表：是一种有序的链表扩展
  *   增加节点
  *   新节点和各层索引节点逐一比较，确定原链表的插入位置
  *   把索引插入到原链表
  *   利用抛硬币的随机方式，决定新节点是否提升为上一级索引。结果为“正”则提升并继续抛硬币，结果为“负”则停止
  *   总体上，跳跃表插入操作的时间复杂度是O（logN），而这种数据结构所占空间是2N，既空间复杂度是 O（N）。
  *
  *   删除节点
  *   自上而下，查找第一次出现节点的索引，并逐层找到每一层对应的节点。O（logN）
  *   删除每一层查找到的节点，如果该层只剩下1个节点，删除整个一层（原链表除外）。O（logN）
  *   总体上，跳跃表删除操作的时间复杂度是O（logN）。
  *
  * 目前经常使用的平衡数据结构有：B树，红黑树，AVL树，Splay Tree, Treep等。
  */
object SkipList {

  def main(args: Array[String]): Unit = {
    var r = new Random() // Coin toss
    println(r.nextDouble())
    println(r.nextDouble())
    println(r.nextDouble())
    println(r.nextDouble())
    println(r.nextDouble())

    val skipList = new SkipList(1)
    skipList.put("1", 1)
    skipList.put("2", 2)
    skipList.put("3", 3)
    println(skipList.get("2"))
  }


}

class SkipList{

  // special// special

  val negInf = "-infinity"
  val posInf = "+infinity"


  var head:SkipListEntry = null // First element of the top level

  var tail:SkipListEntry = null // Last element of the top level


  var n = 0 // number of entries in the Skip List

  var h = 0 // Height

  var r = new Random() // Coin toss

  // constructor
  def this(capcity:Int){
    this()
    var p1: SkipListEntry = null
    var p2: SkipListEntry = null

    // 创建一个 -oo 和一个 +oo 对象
    p1 = new SkipListEntry(negInf, null)
    p2 = new SkipListEntry(posInf, null)

    // 将 -oo 和 +oo 相互连接
    p1.right = p2
    p2.left = p1

    // 给 head 和 tail 初始化
    head = p1
    tail = p2

    n = 0
    h = 0
  }

  private def findEntry(key: String) : SkipListEntry ={
    var p:SkipListEntry = null
    // 从head头节点开始查找
    p = head
    while (true){
      // 从左向右查找，直到右节点的key值大于要查找的key值
      while ((p.right.key ne posInf) && p.right.key.compareTo(key) <= 0) {
        p = p.right
      }
      // 如果有更低层的节点，则向低层移动
      if (p.down != null) {
        p = p.down
      }else{
        return p
      }
      //todo: break is not supported
    }
    // 返回p，！注意这里p的key值是小于等于传入key的值的（p.key <= key）
    return p
  }

  def put(key: String, value: Integer): Integer = {
    var p:SkipListEntry = null
    var q:SkipListEntry = null
    var i = 0
    // 查找适合插入的位子
    p = findEntry(key)
    // 如果跳跃表中存在含有key值的节点，则进行value的修改操作即可完成
    if (p.key.equals(key)) {
      val oldValue = p.value
      p.value = value
      return oldValue
    }
    // 如果跳跃表中不存在含有key值的节点，则进行新增操作
    q = new SkipListEntry(key, value)
    q.left = p
    q.right = p.right
    p.right.left = q
    p.right = q
    // 再使用随机数决定是否要向更高level攀升
    while (r.nextDouble < 0.5) {
      // 如果新元素的级别已经达到跳跃表的最大高度，则新建空白层
      if (i >= h) {
        addEmptyLevel()
      }
      // 从p向左扫描含有高层节点的节点
      while (p.up == null) {
        p = p.left
      }
      p = p.up
      // 新增和q指针指向的节点含有相同key值的节点对象
      // 这里需要注意的是除底层节点之外的节点对象是不需要value值的
      val z = new SkipListEntry(key, null)
      z.left = p
      z.right = p.right
      p.right.left = z
      p.right = z
      z.down = q
      q.up = z
      q = z
      i = i + 1
    }
    n = n + 1
    // 返回null，没有旧节点的value值
    null
  }

  private def addEmptyLevel(): Unit = {
    val p1 = new SkipListEntry(negInf, null)
    val p2 = new SkipListEntry(posInf, null)
    p1.right = p2
    p1.down = head
    p2.left = p1
    p2.down = tail
    head.up = p1
    tail.up = p2
    head = p1
    tail = p2
    h = h + 1
  }

  def get(key: String): Integer = {
    var p = findEntry(key)
    if (p.key.equals(key)) p.value
    else null
  }




}
