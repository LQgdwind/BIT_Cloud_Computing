import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.mutable._

object Kmer
{
  //-------题目给的input-------
  val K: Int = 2
  val N: Int = 10

  def main(args: Array[String]): Unit = {
    //----------------------读入数据与初始化-------------------
    //启动spark local模式，建立和spark的连接。
    val SparkConfig = new SparkConf().setMaster("local").setAppName("K-Mer-Counting")
    val sparkContext = new SparkContext(SparkConfig)
    //读入本地输入文件。
    val textLine = sparkContext.textFile("/home/zlq/Desktop/KM-input.txt").collect()

    //---------------------进行K-mer算法---------------------
    textLine.foreach(
      string =>
      {
        val list=StringTool.subSort(string,K,N)
        for(i <- 0 until list.length)
        {
          println(list(i)._1+","+list(i)._2)
        }
        val totalList=StringTool.getSub(string,K).
          groupBy(a => a).map(tuple => (tuple._1, tuple._2.length)).toList //将List[String]转换为List[(String,Int)]以方便调用Math.average
        val average=MathTool.getAverage(totalList)
        printf("所有字符串出现次数的平均值:%.8f\n",average)
      })
    //--------------------查看结果----------------------
    //用于缓冲，方便查看Spark的Web UI
    System.in.read()
    //停止运行
    sparkContext.stop()
  }
}


