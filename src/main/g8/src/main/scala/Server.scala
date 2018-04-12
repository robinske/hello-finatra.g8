package $name$

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter


object HelloApp extends HelloServer

class HelloServer extends HttpServer {

  override def configureHttp(router: HttpRouter) {
    router
      .filter[CommonFilters]
      .add[HelloController]
  }

}

class HelloController extends Controller {

  get("/hello") { request: Request =>
    s"Hello, \${request.params.getOrElse("name", "friend")}!"
  }

}
