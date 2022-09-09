object StringTool
{
  def split(string : String):(Array[Int],Int) =
  {
    val a = string.split(",")
      .map(x_ => x_.toInt)
    val point=a.slice(1,a.length)
    val id=a(0)
    return (point,id)
    //将结果放入tuple中，第一维是坐标，第二维是id
  }
}
