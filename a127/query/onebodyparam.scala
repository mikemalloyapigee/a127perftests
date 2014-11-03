package com.apigee.perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import com.apigee.perf.common._

class onebodyparam extends Simulation {

  object Post {
    val post = repeat(config.repeatCount, "i") {
      exec(http("PostFiveParams")
        .post("/processbody")
        .header(HttpHeaderNames.ContentType, HttpHeaderValues.ApplicationJson)
        .body(StringBody("""[{"size": 2},{"value":"0000000000"}"""))
        .headers(config.authHeader)
      )
    }
  }



  val getters = scenario("Posters").exec(Post.post)

  setUp(
    getters.inject(rampUsers(config.concurrentUsers) over (config.warmUpTime seconds))
  ).protocols(config.httpConf)
}