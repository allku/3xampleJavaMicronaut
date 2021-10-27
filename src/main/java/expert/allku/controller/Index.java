package expert.allku.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/")
public class Index {

  @Get
  public Application index() {
    var app = new Application();
    app.application.name = "3xampleJavaMicronaut";

    return app;
  }
}

class Application {
  public Properties application = new Properties();
  public Boolean success = true;
}

class Properties {
  public String name;
}