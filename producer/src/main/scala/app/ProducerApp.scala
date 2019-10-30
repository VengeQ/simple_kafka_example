package app

import models.{Person}
import org.apache.kafka.clients.producer.ProducerRecord
import collection.JavaConverters._

import scala.annotation.tailrec

object ProducerApp extends App {

  @tailrec def readMyLine(): Unit = {
    println("type something for send or :Q for exit")
    val input = scala.io.StdIn.readLine()
    if ("^:[qQ]$".r findFirstIn input isDefined) println("exit..")
    else {
      val p = Person(input)
      producer.send(new ProducerRecord(TOPIC, p),new ProducerSimpleCallback)
      readMyLine()
    }
  }

  readMyLine()
  producer.close()
}

