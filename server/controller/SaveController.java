package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import controller.request.MonitoringRequest;
import httpEnum.Header;
import httpEnum.RequestMethod;
import utils.IOUtils;
import utils.ResponseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class SaveController implements Controller {

    public void handle(final HttpExchange httpExchange) throws IOException {
        InputStreamReader inputStreamReader = null;
        InputStream requestBody = null;

        httpExchange.getResponseHeaders().set(Header.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        try {
            requestBody = httpExchange.getRequestBody();
            inputStreamReader = new InputStreamReader(requestBody, "utf-8");
            final ObjectMapper objectMapper = new ObjectMapper();
            final MonitoringRequest monitoringRequest = objectMapper.readValue(inputStreamReader, MonitoringRequest.class);

            if (isValidResizeRequest(monitoringRequest)) {
                System.out.println(monitoringRequest);

            } else if (isValidCopyAndPasteRequest(monitoringRequest)) {
                System.out.println(monitoringRequest);
            } else if (isValidTimeTakenRequest(monitoringRequest)) {
                System.out.println(monitoringRequest);
            } else {
                sendBadRequestResponse(httpExchange);
                return;
            }

            ResponseUtils.writeResponseString("Ok", httpExchange);

        } catch (IOException e) {
            e.printStackTrace();
            sendBadRequestResponse(httpExchange);
        } finally {
            IOUtils.closeStream(requestBody);
            IOUtils.closeStream(inputStreamReader);
        }
    }

    public List<RequestMethod> getAcceptedMethods() {
        return Arrays.asList(RequestMethod.POST, RequestMethod.OPTIONS);
    }

    private void sendBadRequestResponse(final HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(400, 0);
    }

    private boolean isValidRequest(final MonitoringRequest monitoringRequest) {
        return monitoringRequest.getEventType() != null &&
            monitoringRequest.getSessionId() != null &&
            monitoringRequest.getWebsiteUrl() != null;
    }

    private boolean isValidResizeRequest(final MonitoringRequest monitoringRequest) {
        return isValidRequest(monitoringRequest) &&
            monitoringRequest.getEventType().equals("resize") &&
            monitoringRequest.getResizeFrom() != null &&
            monitoringRequest.getResizeTo() != null;
    }

    private boolean isValidCopyAndPasteRequest(final MonitoringRequest monitoringRequest) {
        return isValidRequest(monitoringRequest) &&
            monitoringRequest.getEventType().equals("copyAndPaste") &&
            monitoringRequest.getPaste() != null;
    }

    private boolean isValidTimeTakenRequest(final MonitoringRequest monitoringRequest) {
        return isValidRequest(monitoringRequest) &&
            monitoringRequest.getEventType().equals("timeTaken") &&
            monitoringRequest.getFormCompletionTime() != null;
    }

}
