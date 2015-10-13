package com.jonvallet.scalatra.controller

import org.scalatra._
import org.scalatra.json._
import org.json4s.{DefaultFormats, Formats}


class MyScalatraServlet extends ScalatraServlet with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    Map("name"->"Jon",
        "surname"->"Vallet")
  }

}
