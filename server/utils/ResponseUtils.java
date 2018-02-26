package utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResponseUtils {

    public static void writeResponseStream(final InputStream inputStream, final HttpExchange httpExchange) {

        ByteArrayOutputStream baos = null;
        OutputStream outputStream = null;
        try {
            int length;
            final byte[] buffer = new byte[inputStream.available() == 0 ? 1024 : inputStream.available()];
            outputStream = httpExchange.getResponseBody();
            baos = new ByteArrayOutputStream(buffer.length);

            while ((length = inputStream.read(buffer, 0, buffer.length)) >= 0) {
                baos.write(buffer, 0, length);
            }

            httpExchange.sendResponseHeaders(200, buffer.length);
            baos.writeTo(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(baos);
            IOUtils.closeStream(outputStream);
        }

    }

    public static void writeResponseString(final String string, final HttpExchange httpExchange) throws IOException {
        byte[] response = string.getBytes();
        httpExchange.sendResponseHeaders(200, response.length);
        final OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(response);
        IOUtils.closeStream(responseBody);
    }

}
