package com.epam.preprod.tereshkevych.shop.wrapper;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPOutputStream;

public class ResponseGzipOutputStream extends ServletOutputStream {

    private static final String ERROR_STREAM_CLOSED = "Stream closed!";

    private final AtomicBoolean open = new AtomicBoolean(true);

    private GZIPOutputStream gzipStream;

    OutputStream output;

    public ResponseGzipOutputStream(OutputStream output) throws IOException {
        this.output = output;
        gzipStream = new GZIPOutputStream(output);
    }

    @Override
    public void close() throws IOException {
        if (open.compareAndSet(true, false)) {
            gzipStream.close();
        }
    }

    @Override
    public void flush() throws IOException {
        gzipStream.flush();
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (!open.get()) {
            throw new IOException(ERROR_STREAM_CLOSED);
        }
        gzipStream.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        if (!open.get()) {
            throw new IOException(ERROR_STREAM_CLOSED);
        }
        gzipStream.write(b);
    }
}