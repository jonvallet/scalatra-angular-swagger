package com.jonvallet.scalatra.controller

import org.scalatra._
import org.scalatra.json._
import org.scalatra.swagger._
import org.json4s.{DefaultFormats, Formats}


class UsersController(implicit val swagger: Swagger) extends ScalatraServlet with JacksonJsonSupport with SwaggerSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  val getUsers =
    (apiOperation[List[User]]("getUsers")
      summary "Show all users"
      notes "Shows all the users. You can search too."
      parameter queryParam[Option[String]]("name").description("A name to search for"))

  get("/", operation(getUsers)) {
    params.get("name") match {
      case Some(name) => UserData.all filter (_.name.toLowerCase contains name.toLowerCase)
      case None => UserData.all
    }
  }

  val findByName =
    (apiOperation[User]("findByName")
      summary "Find by name"
      parameters (
      pathParam[String]("name").description("name of user to get fetched")
      ))
  
  get("/:name", operation(findByName)) {
    UserData.all find (_.name.toLowerCase == params("name").toLowerCase) match {
      case Some(u) => u
      case None => halt(404)
    }
  }

  override protected def applicationDescription: String = "The users API. It exposes operations for browsing and searching list of users, " +
    "and retrieving single users."
}


case class User(name: String, surname: String)

object  UserData {
  var all = List(
    User("Jon", "Vallet"),
    User("Laura", "Olchansky"),
    User("David", "Vallet")
  )
}