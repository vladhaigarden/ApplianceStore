package com.epam.preprod.tereshkevych.shop.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class GzipResponseWrapper extends HttpServletResponseWrapper {

    private static final String GZIP_TYPE_ENCODING = "gzip";

    private static final String ERROR_PRINT_WRITER = "PrintWriter already defined";

    private ResponseGzipOutputStream gzipStream;
    private ServletOutputStream outputStream;
    private PrintWriter printWriter;

    public GzipResponseWrapper(HttpServletResponse response) {
        super(response);
        response.addHeader(HttpHeaders.CONTENT_ENCODING, GZIP_TYPE_ENCODING);
    }

    public void finish() throws IOException {
        if (printWriter != null) {
            printWriter.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
        if (gzipStream != null) {
            gzipStream.close();
        }
    }

    @Override
    public void flushBuffer() throws IOException {
        if (printWriter != null) {
            printWriter.flush();
        }
        if (outputStream != null) {
            outputStream.flush();
        }
        super.flushBuffer();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (printWriter != null) {
            throw new IllegalStateException(ERROR_PRINT_WRITER);
        }
        if (outputStream == null) {
            initGzip();
            outputStream = gzipStream;
        }
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (outputStream != null) {
            throw new IllegalStateException(ERROR_PRINT_WRITER);
        }
        if (printWriter == null) {
            initGzip();
            printWriter = new PrintWriter(new OutputStreamWriter(gzipStream, getResponse().getCharacterEncoding()));
        }
        return printWriter;
    }

    private void initGzip() throws IOException {
        gzipStream = new ResponseGzipOutputStream(getResponse().getOutputStream());
    }
}