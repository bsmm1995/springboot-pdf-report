package com.bsmm.pdf.service.impl;

import com.bsmm.pdf.service.PdfGenerator;
import com.bsmm.pdf.util.OrderUtil;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfGeneratorImpl implements PdfGenerator {
    private final TemplateEngine templateEngine;
    private final ServletContext servletContext;

    @Override
    public byte[] createItextPdf(String templateName, HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(templateName, "The templateName can not be null");

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("orderEntry", OrderUtil.getOrder());
        String processedHtml = templateEngine.process(templateName, context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        HtmlConverter.convertToPdf(processedHtml, target, converterProperties);

        return target.toByteArray();
    }

    @Override
    public byte[] createOpenPdf(final String templateName, final HttpServletRequest request, final HttpServletResponse response) {
        Assert.notNull(templateName, "The templateName can not be null");

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("orderEntry", OrderUtil.getOrder());
        String processedHtml = templateEngine.process(templateName, context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(processedHtml, "http://localhost:8080");

        renderer.layout();
        renderer.createPDF(target, true);
        renderer.finishPDF();

        return target.toByteArray();
    }
}
