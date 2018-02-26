import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import controller.Controller;
import factory.RoutesFactory;
import httpEnum.RequestMethod;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

public class HTTPServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);
        server.createContext("/", new MyHandler(RoutesFactory.create()));
        server.setExecutor(null);
        server.start();
    }

    public static class MyHandler implements HttpHandler {

        private Map<String, Controller> routes;

        private MyHandler(Map<String, Controller> routes) {
            this.routes = routes;
        }

        public void handle(HttpExchange httpExchange) throws IOException {
            final String requestURI = httpExchange.getRequestURI().getPath();
            final String method = httpExchange.getRequestMethod();

            if (routes.containsKey(requestURI)) {
                final List<RequestMethod> acceptedMethods = routes.get(requestURI).getAcceptedMethods();
                boolean isAcceptedMethod = acceptedMethods.stream()
                    .anyMatch(acceptedMethod -> acceptedMethod.toString().equals(method));
                if (isAcceptedMethod) {
                    try {
                        routes.get(requestURI).handle(httpExchange);
                    } catch (Exception e) {
                        e.printStackTrace();
                        httpExchange.sendResponseHeaders(500, 0);
                    }
                    return;
                }

            }
            httpExchange.sendResponseHeaders(404, 0);
        }
    }
}
