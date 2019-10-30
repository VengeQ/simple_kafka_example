import java.util.Properties
import java.util.{Collections => cols}
import java.time.{Duration => JDuration}

import models.Person

import concurrent.duration.{Duration => SDuration}
import org.apache.kafka.clients.consumer.KafkaConsumer

package object app {
  val TOPIC = "testTopic"
  val consumerProps = new Properties()
  consumerProps.put("bootstrap.servers", "localhost:9093, localhost:9094")
  consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  consumerProps.put("value.deserializer", "models.PersonDeserializer")
  consumerProps.put("group.id", "something")
  val consumer = new KafkaConsumer[String, Person](consumerProps)

  consumer.subscribe(cols.singletonList(TOPIC))


  implicit def sToJDuration(sDuration: SDuration) = JDuration.ofSeconds(sDuration.toSeconds)
}
