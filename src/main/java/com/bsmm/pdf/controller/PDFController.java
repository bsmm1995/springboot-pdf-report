package com.bsmm.pdf.controller;

import com.bsmm.pdf.service.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class PDFController {
    private final PdfGenerator pdfGenerator;

    @GetMapping("/itext")
    public ResponseEntity<Object> itext(final HttpServletRequest request, final HttpServletResponse response) {
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=file.pdf").contentType(MediaType.APPLICATION_PDF).body(pdfGenerator.createItextPdf("order.html", request, response));
    }

    @GetMapping("/open")
    public ResponseEntity<ByteArrayResource> openPDF(final HttpServletRequest request, final HttpServletResponse response) {
        ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(pdfGenerator.createOpenPdf("templatePDF.html", request, response));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=file.pdf").contentType(MediaType.APPLICATION_PDF).contentLength(inputStreamResourcePDF.contentLength()).body(inputStreamResourcePDF);
    }
}
