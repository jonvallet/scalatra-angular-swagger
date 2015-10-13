package com.jonvallet.scalatra.controller

import org.scalatra.ScalatraServlet
import org.scalatra.swagger.{ApiInfo, NativeSwaggerBase, Swagger}

class ResourcesApp(implicit val swagger: Swagger) extends ScalatraServlet with NativeSwaggerBase

object UsersApiInfo extends ApiInfo(
    "The Users API",
    "Docs for the Users API",
    "http://jonvallet.com",
    "j.vallet@gmail.com",
    "MIT",
    "http://opensource.org/licenses/MIT")


class UsersSwagger extends Swagger(Swagger.SpecVersion, "1.0.0", UsersApiInfo)
