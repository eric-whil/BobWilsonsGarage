package com.bobwilsonsgarage.carrepair

import akka.actor.ActorSystem
import akka.cluster.singleton.{ClusterSingletonProxy, ClusterSingletonProxySettings}
import com.bobwilsonsgarage.carrepair.CarRepairServiceEndpointOverseerProtocol.CreateCarRepairServiceEndpoint
import com.typesafe.config.ConfigFactory

/**
 * Car Repair Service Hosting Akka Cluster Node.
 
 * @author dbolene
 */
object CarRepairServiceNode {
  def main(args: Array[String]): Unit = {
    // Override the configuration of the port when specified as program argument
    val config = ConfigFactory.parseString("akka.cluster.roles = [carrepairservice]").
      withFallback(ConfigFactory.load())

    val system = ActorSystem("ClusterSystem", config)

    val registry = system.actorOf(ClusterSingletonProxy.props(
      singletonManagerPath = "/user/registry",
      settings = ClusterSingletonProxySettings(system)),
      name = "registryProxy")

    val carRepairServiceEndpointOverseer = system.actorOf(CarRepairServiceEndpointOverseer.props())

    carRepairServiceEndpointOverseer ! CreateCarRepairServiceEndpoint(registry = registry)
  }
}