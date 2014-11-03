package com.apigee.perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import com.apigee.perf.common._

class tenqueryparams extends Simulation {

  object Get {
    val get = repeat(config.repeatCount) {
      exec(http("GetTenQueryParam")
        .get("/ten-query")
        .queryParam("""param1""", """0000000000""")
        .queryParam("""param2""", """0000000000""")
        .queryParam("""param3""", """0000000000""")
        .queryParam("""param4""", """0000000000""")
        .queryParam("""param5""", """0000000000""") 
        .queryParam("""param6""", """0000000000""")
        .queryParam("""param7""", """0000000000""")
        .queryParam("""param8""", """0000000000""")
        .queryParam("""param9""", """0000000000""")
        .queryParam("""param10""", """0000000000""")
        .headers(config.authHeader)
      )}
  }

  val getters = scenario("Getters").exec(Get.get)

  setUp(
    getters.inject(rampUsers(config.concurrentUsers) over (config.warmUpTime seconds))
  ).protocols(config.httpConf)
}
