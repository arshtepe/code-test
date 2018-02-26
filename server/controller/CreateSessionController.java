package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import controller.reponse.SessionResponse;
import httpEnum.Header;
import httpEnum.ContentType;
import httpEnum.RequestMethod;
import utils.ResponseUtils;

import java.io.IOException;
import java.util.*;

public class CreateSessionController implements Controller {

    public void handle(final HttpExchange httpExchange) throws IOException {
        final String sessionId = UUID.randomUUID().toString();
        ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(new SessionResponse(sessionId));
        httpExchange.getResponseHeaders().set(Header.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        httpExchange.getResponseHeaders().set(Header.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        ResponseUtils.writeResponseString(json, httpExchange);
    }

    public List<RequestMethod> getAcceptedMethods() {
        return Collections.singletonList(RequestMethod.GET);
    }
}
