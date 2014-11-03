package com.apigee.perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import com.apigee.perf.common._

class fivepathparam extends Simulation {

  object Get {
	  // repeat is a loop resolved at RUNTIME
	  val get = repeat(config.repeatCount) { // Note how we force the counter name so we can reuse it
		  exec(http("GetFivePathParams")
			  .get("/five-path/0000000000/0000000000/0000000000/0000000000/0000000000")
        .headers(config.authHeader)
			)
    }
  }

  val getters = scenario("Getters").exec(Get.get)

  setUp(
    getters.inject(rampUsers(config.concurrentUsers) over (config.warmUpTime seconds))
  ).protocols(config.httpConf)
}