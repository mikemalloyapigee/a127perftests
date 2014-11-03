package com.apigee.perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import com.apigee.perf.common._

class fivequeryparams extends Simulation {

  object Get {
    val get = repeat(config.repeatCount) {
      exec(http("GetFiveQueryParam")
        .get("/five-query")
        .queryParam("""param1""", """0000000000""")
        .queryParam("""param2""", """0000000000""")
        .queryParam("""param3""", """0000000000""")
        .queryParam("""param4""", """0000000000""")
        .queryParam("""param5""", """0000000000""")
        .headers(config.authHeader)
    )}
  }

  val getters = scenario("Getters").exec(Get.get)

  setUp(
    getters.inject(rampUsers(config.concurrentUsers) over (config.warmUpTime seconds))
  ).protocols(config.httpConf)
}
