package webservice.gatling.simulation

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration._
import org.kohsuke.randname.RandomNameGenerator
import scala.util.Random

class WebServiceCallSimulation extends Simulation {

    val rampUpTimeSecs = 5
    val testTimeSecs = 20
    val noOfUsers = 10
    val minWaitMs = 1000 milliseconds
    val maxWaitMs = 3000 milliseconds

    val baseURL = "http://localhost:8080"
    val baseName = "webservice-call-greeting"
    val requestName = baseName + "-request"
    val scenarioName = baseName + "-scenario"
    val URI = "/hello"

    val httpConf = http
            .baseURL(baseURL)
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")


    val rnd = new RandomNameGenerator(0)
    val feeder = Iterator.continually(Map("word" -> rnd.next()))

    val scn = scenario(scenarioName)
            .during(testTimeSecs) {
                feed(feeder)
                .exec(
                    http(requestName)
                      .post("/create")
                      .body(StringBody("""{ "data": "${word}" }""")).asJSON
                      .check(status.is(200))
                )
                .pause(minWaitMs, maxWaitMs)
                .exec(
                  http("get")
                    .get("/names")
                    .check(status.is(200))
                )
            }

    setUp(
            scn.inject(rampUsers(noOfUsers) over (rampUpTimeSecs))
    ).protocols(httpConf)
}