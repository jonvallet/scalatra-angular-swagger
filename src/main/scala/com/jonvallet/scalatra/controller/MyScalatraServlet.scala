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
    params.get("name") match {
      case Some(name) => UserData.all filter (_.name.toLowerCase contains name.toLowerCase)
      case None => UserData.all
    }
  }

  get("/:name") {
    UserData.all find (_.name.toLowerCase == params("name").toLowerCase) match {
      case Some(u) => u
      case None => halt(404)
    }
  }

}


case class User(name: String, surname: String)

object  UserData {
  var all = List(
    User("Jon", "Vallet"),
    User("Laura", "Olchansky"),
    User("David", "Vallet")
  )
}