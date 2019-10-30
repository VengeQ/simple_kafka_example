package app

import java.util.{Collections => cols}
import concurrent.duration._
import scala.annotation.tailrec
import scala.concurrent.ExecutionContext
import collection.JavaConverters._

object ConsumerApp extends App {
  @tailrec def needToExit(): Unit = {
    val input = scala.io.StdIn.readLine()
    if ("^:[qQ]$".r findFirstIn input isDefined) println("exit..")
    else needToExit()
  }

  //Отдельный поток, который принимает сообщения и выводит их
  val ectx = ExecutionContext.global //возьмем контекст из глобала для простоты
  ectx.execute(() => {
    while (true) {
      consumer.subscribe(cols.singletonList(TOPIC))
      val a = consumer.poll(Duration(0.1, SECONDS))
      a.records(TOPIC).forEach((x) => println(s"Get message ${x.value}"))
    }
    consumer.close()
  })

  println("type :Q for exit or waiting new messages")
  needToExit()

}
