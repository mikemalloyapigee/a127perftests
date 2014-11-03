package com.apigee.perf.common
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object config {
  val repeatCount = 1000
  val concurrentUsers = 1
  val warmUpTime = 1
  val authHeader = Map("Authorization" -> """placeholder""")

  val httpConf = http
    .baseURL("http://api-connectors-test.apigee.net/benchmark")
    //.baseURL("http://a127perf-env.elasticbeanstalk.com")
    //.baseURL("http://localhost:10010")
    //.baseURL("http://mikemalloy-test.apigee.net/benchmark")
  
}