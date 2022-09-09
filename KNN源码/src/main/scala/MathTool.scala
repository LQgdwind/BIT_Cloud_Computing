object MathTool
{
  //计算平均数
  def getAverage(a:Array[Double]):Double=
  {
    var s = 0.0
    for(i <- 0 to a.length-1)
    {
      s+=a(i)
    }
    return s/a.length
  }
  //计算距离
  def getDistance(tuple : (Array[Int], Int), p : Array[Int]):Double=
  {
    var sum: Int = 0
    for(i <- 0 to 3)
    {
      sum += (tuple._1(i) - p(i)) * (tuple._1(i) - p(i))
    }
    return math.sqrt(sum)
  }
  //计算方差
  def getVariance(a: Array[Double]):Double =
  {
    val average: Double = getAverage(a)
    var sum: Double = 0
    for(i <- 0 to a.length-1)
    {
      sum += (a(i) - average) * (a(i) - average)
    }
    return sum/a.length
  }
}
