import java.util.Properties
import java.util.{Collections => cols}

import models.Person
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, RecordMetadata}


package object app {
  val TOPIC = "testTopic"

  val producerProps = new Properties()
  producerProps.put("bootstrap.servers", "localhost:9093, localhost:9094")

  producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  //producerProps.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer")
  producerProps.put("value.serializer", "models.PersonSerializer")
  //producerProps.put("schema.registry.url", "http://localhost:9092")
  val producer = new KafkaProducer[String, Person](producerProps)


  class ProducerSimpleCallback extends Callback {
    override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
      exception match{
        case null => println(metadata+"    asd")
        case _ => println(exception.getMessage)
      }
    }
  }

}
