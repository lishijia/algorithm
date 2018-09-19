package com.jiarong.data.structure.skiplist

/**
  * skipList数据存储结构
  */
class SkipListEntry{

  // data
  var key:String = null
  var value:Integer = null

  // links
  var up:SkipListEntry = null
  var down:SkipListEntry = null
  var left:SkipListEntry = null
  var right:SkipListEntry = null

  def this(key: String, value: Integer){
    this()
    this.key=key
    this.value=value
  }


}
