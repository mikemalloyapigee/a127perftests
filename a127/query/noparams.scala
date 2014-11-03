package com.apigee.perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import com.apigee.perf.common._

class noqueryparams extends Simulation {
  object Get {
	  val get = repeat(config.repeatCount) { 
		  exec(http("Get")
			  .get("/no-query")
			  .headers(config.authHeader)
			)
	  }
  }
 
  val getters = scenario("Getters").exec(Get.get)

  setUp(
    getters.inject(rampUsers(config.concurrentUsers) over (config.warmUpTime seconds))
  ).protocols(config.httpConf)
}