package factory;

import controller.Controller;
import controller.CreateSessionController;
import controller.SaveController;
import controller.ViewController;

import java.util.HashMap;
import java.util.Map;

public class RoutesFactory {

    public static Map<String, Controller> create() {
        final Map<String, Controller> routes = new HashMap<>();
        routes.put("/", new ViewController());
        routes.put("/save", new SaveController());
        routes.put("/session", new CreateSessionController());
        return routes;
    }
}
