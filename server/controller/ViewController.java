package controller;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import httpEnum.ContentType;
import httpEnum.Header;
import httpEnum.RequestMethod;
import utils.IOUtils;
import utils.ResponseUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class ViewController implements Controller {

    public void handle(final HttpExchange httpExchange) {
        InputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("client/index.html"));
            int size = fileInputStream.available();
            this.setHeaders(httpExchange, size);
            ResponseUtils.writeResponseStream(fileInputStream, httpExchange);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(fileInputStream);
        }
    }

    public List<RequestMethod> getAcceptedMethods() {
        return Collections.singletonList(RequestMethod.GET);
    }

    private void setHeaders(final HttpExchange httpExchange, final int size) {
        final Headers responseHeaders = httpExchange.getResponseHeaders();
        responseHeaders.set(Header.ACCEPT_RANGES, "bytes");
        responseHeaders.set(Header.CONTENT_TYPE, ContentType.TEXT_HTML);
        responseHeaders.set(Header.CONTENT_LENGTH, String.valueOf(size));

    }

}
