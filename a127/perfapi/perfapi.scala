package com.apigee.perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class perfapi extends Simulation {
  val repeatCount = 1000
  val concurrentUsers = 10
  object Get {
	  val get = repeat(repeatCount) { 
		  exec(http("Get")
			  .get("/perfapi")
			)
	  }
  }
 
  val getters = scenario("Getters").exec(Get.get)
  val httpConf = http
    //.baseURL("http://a127perf-env.elasticbeanstalk.com")
    .baseURL("http://localhost:10010")
    //.baseURL("http://mikemalloy-test.apigee.net/benchmark")
    //.baseURL("http://perfapi-env.elasticbeanstalk.com")

  setUp(
    getters.inject(rampUsers(concurrentUsers) over (1 seconds))
  ).protocols(httpConf)
}