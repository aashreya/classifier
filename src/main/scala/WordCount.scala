import org.apache.lucene.analysis.en.EnglishAnalyzer
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.apache.spark.SparkConf

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object WordCount {
  def main(args: Array[String]) {

    //Create a SparkContext to initialize Spark
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Word Count")
    //    val sc = new SparkContext(conf)

    // Load the text into a Spark RDD, which is a distributed representation of each line of text
    //    val textFile = sc.textFile("src/main/resources/appleNewsSmall.txt")

    val textFile = Source.fromFile("src/main/resources/appleNewsSmall.txt").getLines.toList

    println(textFile.mkString(" "))

    val tokenizedText = tokenize(textFile.toString())

    println(tokenizedText.toString())


  }

  def tokenize(content: String): Seq[String] = {
    val analyzer = new EnglishAnalyzer()
    val tokenStream = analyzer.tokenStream("contents", content)
    val term = tokenStream.addAttribute(classOf[CharTermAttribute])
    tokenStream.reset()
    var result = ArrayBuffer.empty[String]

    while (tokenStream.incrementToken()) {
      val termValue = term.toString
      if (!(termValue matches ".*[\\d\\.].*")) {
        result += term.toString
      }
    }
    tokenStream.end()
    tokenStream.close()
    result
  }
}
