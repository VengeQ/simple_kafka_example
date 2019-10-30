package models

import java.util
import java.util.concurrent.atomic.AtomicInteger

import org.apache.kafka.common.serialization.Serializer

import scala.io.Source

case class Person(val name: String) {
  val id: Int = Person.id.addAndGet(1)

  override def toString: java.lang.String = id + " " + name
}

object Person {
  val id = new AtomicInteger(357)
}

@SerialVersionUID(100L)
class PersonSerializer extends Serializer[Person] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: Person): Array[Byte] = {
    def id: Array[Byte] = Array.emptyByteArray.+:((data.id / 256 - Byte.MaxValue).toByte).+:((data.id % 256 - Byte.MaxValue).toByte)

    id ++ data.name.getBytes("UTF-8")
  }

  override def close(): Unit = {}

}

