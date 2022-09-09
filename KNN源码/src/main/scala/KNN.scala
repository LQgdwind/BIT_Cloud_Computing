import org.apache.spark.{SparkConf, SparkContext}

object KNN
{
  val K = 20
  val InputPoint: Array[Int] = Array(0,0,0,0)
  def main(args: Array[String]): Unit =
  {
    //----------------------读入数据与初始化-------------------
    //启动spark local模式，建立和spark的连接。
    val SparkConfig = new SparkConf().setMaster("local").setAppName("KNN")
    val sparkContext = new SparkContext(SparkConfig)
    //读入本地输入文件。
    val textLine = sparkContext.textFile("/home/zlq/Desktop/KNN-input.txt").collect()
    //---------------------进行KNN算法---------------------
    val matrix = textLine.map(StringTool.split)

      .map(tuple => ((MathTool.getDistance(tuple,InputPoint)),tuple._2))
      //对数组每个元素依次计算到输入点的距离
      //并且把结果放入tuple中，tuple的第一维是距离，第二维是id
      .sortWith(
        (first,second) =>
          if(first._1 == second._1)
          {
            first._2 < second._2
          }
          else
          {
            first._1 < second._1
          })
      //按照tuple的第一维距离和第二维id进行排序。
      .slice(0,K)
    //选取前K个元素
    //-----------------输出------------------------
    val id=matrix.map(tuple => tuple._2)
    for(i <- 0 to id.length-1)
    {
      if(i!=id.length-1)
      {
        print(id(i)+" ")
      }
      else
      {
        println(id(i))
      }
    }
    var dist = matrix.map(t => t._1)
    printf("%.8f\n",MathTool.getAverage(dist))
    printf("%.8f\n",MathTool.getVariance(dist))
    //--------------------查看结果----------------------
    //用于缓冲，方便查看Spark的Web UI
    System.in.read()
    //停止运行
    sparkContext.stop()
  }
}

