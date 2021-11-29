package perf

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.util.Random

class DIVOCSimulation extends Simulation {
  private val env: String = System.getenv.getOrDefault("targetEnv", "dev")
  var baseUrl = System.getenv.getOrDefault("baseUrl", "http://localhost")
  val token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJmaWwzSWNOZjlMRFV5WU9ZNlhQbmVjTC1QeURwUjBqajdqQk50QkpqRjBVIn0.eyJleHAiOjE2NTU0NDY5NTQsImlhdCI6MTYzODE2Njk1NSwianRpIjoiZWJhM2Q4OGUtYTc5Ni00ODNlLWEzZGUtMjRjZTBhMTIwYjIwIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdC9hdXRoL3JlYWxtcy9kaXZvYyIsImF1ZCI6WyJyZWFsbS1tYW5hZ2VtZW50IiwiYWNjb3VudCIsImZhY2lsaXR5LWFkbWluLXBvcnRhbCJdLCJzdWIiOiJkOWU2OGJlNC0yMDVhLTRiNDQtODMwMS0xZmVhMjU1N2YxY2YiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhZG1pbi1hcGkiLCJzZXNzaW9uX3N0YXRlIjoiOTAxOThlYmItMGQ4Ny00YTUzLWE2NTUtOTQ2NWI4MjdlOGYwIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwczovL2Rpdm9jLnhpdi5pbiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7InJlYWxtLW1hbmFnZW1lbnQiOnsicm9sZXMiOlsibWFuYWdlLXVzZXJzIl19LCJhZG1pbi1hcGkiOnsicm9sZXMiOlsiYXBpIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX0sImZhY2lsaXR5LWFkbWluLXBvcnRhbCI6eyJyb2xlcyI6WyJmYWNpbGl0eS1zdGFmZiIsImFkbWluIiwibW9uaXRvcmluZyIsImZhY2lsaXR5LWFkbWluIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiY2xpZW50SWQiOiJhZG1pbi1hcGkiLCJjbGllbnRIb3N0IjoiMTcyLjE4LjAuMTciLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6InNlcnZpY2UtYWNjb3VudC1hZG1pbi1hcGkiLCJjbGllbnRBZGRyZXNzIjoiMTcyLjE4LjAuMTcifQ.L7gCY94V2tiW44Mm9DTs54tIOuMYIxJn_sIgRHAKa0DgHiOYaSd0KpHSer-oX9k9WGkKlxsELkeEbbiLbF7tdMO8vZwZcUMne0b5x9ubQYjIfl5XCfIp7KhMM2rFD_ho2O6-ikDlW1Qohi4ls0O6Umysy3ZrmjkrkKeMiFtbg49jS6uUZ2fbtoS3gi-ColXWQFXlf3G377KyLbCmibGafeLDMk6Wo_XbuRDVskVNvJ9UWjYBhu4lQftPWEhaDYudSAmFNiq5up2v0nmXshAz3DF9THJ01dTeJylK59lJBSSRXUBLs3IOHO7LXiFDwmunlVTGQU9K2KnPQGsqZeoLbA"
  println(s"Using env ${baseUrl}")

  val httpProtocol = http
    .warmUp(baseUrl)
    .baseUrl(baseUrl)
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val headers = Map(
    "accept" -> "application/json",
    "Content-Type" -> "application/json",
    "Authorization" -> s"Bearer ${token}"
  )

//  val requestBody = RawFileBody("certificate-request.json")

  val feeder = Iterator.continually(Map("id" -> Random.nextInt(Integer.MAX_VALUE)))

  val req = http("Create certificate")
    .post(s"${baseUrl}/divoc/api/v3/certify")
    .headers(headers)
    .body(StringBody("""[
                       |  {
                       |     "preEnrollmentCode": "12345${r}",
                       |     "programId": "VCC001",
                       |     "comorbidities": [
                       |     
                       |     ],
                       |     "recipient": {
                       |       "name": "John ${r}",
                       |       "dob": "1987-04-21",
                       |       "gender": "Male",
                       |       "nationality": "Sri Lankan",
                       |       "identity": "872550100V",
                       |       "contact": [
                       |       "tel:${r}"
                       |     ],
                       |     "address": {
                       |       "addressLine1": "Tharaka, Kudawella South, Nakulugala, Tangalle",
                       |       "addressLine2": "",
                       |       "district": "Hambanthota",
                       |       "state": "ceylon",
                       |       "pincode": "123456"
                       |     }
                       |     },
                       |     "vaccination": {
                       |       "name": "Covishield",
                       |       "batch": "41202025",
                       |       "manufacturer": "india",
                       |       "date": "2021-10-17T05:30:28.187Z",
                       |       "effectiveStart": "2021-05-21",
                       |       "effectiveUntil": "2022-05-21",
                       |       "dose": 2,
                       |       "totalDoses": 2
                       |     },
                       |     "vaccinator": {
                       |       "name": "ss"
                       |     },
                       |     "facility": {
                       |       "name": "MOH Gothatuwa",
                       |       "address": {
                       |       "addressLine1": "df",
                       |       "addressLine2": "",
                       |       "district": "wew",
                       |       "state": "w",
                       |       "pincode": "w"
                       |     }
                       |     },
                       |     "meta": {
                       |       "partialEnabled": true,
                       |       "totalDoses": 2,
                       |       "cit": "00000074",
                       |       "name": "Covishield",
                       |       "batch": "41202025",
                       |       "manufacturer": "india",
                       |       "date": "2021-07-17T05:30:28.187Z"
                       |     }
                       |  }
                       |]""".stripMargin))
    .check(status.in(200))


  val scn = scenario("Certificate")
    .feed(feeder)
    .forever(
      pace(1 seconds)
        .exec({s=>
          s.set("r", Random.nextInt(Integer.MAX_VALUE).toString)
        }
        )
        .exec(req)
    )

//  setUp(scn.inject(atOnceUsers(1)))

  setUp(scn.inject(
    atOnceUsers(1),
    rampUsers(300).during((300/5).seconds)
  )).protocols(httpProtocol).assertions(
    global.responseTime.max.lt(800),
    global.successfulRequests.percent.gt(98)
  ).maxDuration(8 hours)
}
