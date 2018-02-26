package controller;

import com.sun.net.httpserver.HttpExchange;
import httpEnum.RequestMethod;

import java.io.IOException;
import java.util.List;

public interface Controller {

    void handle(HttpExchange httpExchange) throws IOException;

    List<RequestMethod> getAcceptedMethods();
}
