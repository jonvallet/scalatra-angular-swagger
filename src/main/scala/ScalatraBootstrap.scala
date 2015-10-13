import com.jonvallet.scalatra.controller._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {

  implicit val swagger = new UsersSwagger

  override def init(context: ServletContext) {
    context.mount(new UsersController, "/users/*", "users")
    context.mount (new ResourcesApp, "/api-docs")
  }
}
