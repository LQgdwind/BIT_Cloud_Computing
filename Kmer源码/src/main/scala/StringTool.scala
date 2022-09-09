import scala.collection.mutable.ListBuffer

object StringTool
{
  //获取字串List
  def getSub(string: String, subLength: Int): List[String] =
  {
    val matrix: ListBuffer[String] = ListBuffer()
    for (i <- 0 to string.length - subLength)
      matrix += string.substring(i, i + subLength)
    return matrix.toList
  }

  //排序算法
  def subSort(string : String, length : Int, num : Int) :List[(String,Int)]=
  {
    return getSub(string,length)
      .groupBy(a => a)
      .map(tuple => (tuple._1, tuple._2.length)).toList
      //将子串本身作为第一维，出现次数作为第二维放入元组中，并把元组装入列表。
      .sortWith(
        (first,second)
        =>
          if(first._2 == second._2)
          {
            first._1 > second._1
          }
          else
          {
            first._2 > second._2
          })
      //以元组的第二维作为第一关键字，元组的第一维作为第二关键字进行排序。
      .slice(0,num)
    //选取前num个元组组成子列表返回。
  }
}
