package com.bsmm.pdf.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

public interface PdfGenerator {
    byte[] createItextPdf(final String templateName, final HttpServletRequest request, final HttpServletResponse response);

    byte[] createOpenPdf(final String templateName, final HttpServletRequest request, final HttpServletResponse response);
}
