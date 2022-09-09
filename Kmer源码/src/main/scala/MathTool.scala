object MathTool
{
  def getAverage(a:List[(String,Int)]):Double=
  {
    var s = 0.0
    for(i <- 0 to a.length-1)
    {
      s+=a(i)._2
    }
    return s/a.length
  }
}
