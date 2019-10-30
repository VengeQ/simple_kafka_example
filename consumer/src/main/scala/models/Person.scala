package models

import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util
import java.util.concurrent.atomic.AtomicInteger

import org.apache.kafka.common.serialization.{Deserializer, Serializer}

case class Person(id:Int, name:String) {

}

class PersonDeserializer extends Deserializer[Person]{
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}


  override def close(): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): Person = {

    val id = (data.head.toInt+Byte.MaxValue) + (data.tail.head.toInt+Byte.MaxValue) * 256

    Person(id, new String(data.drop(2),"UTF-8"))

  }
}