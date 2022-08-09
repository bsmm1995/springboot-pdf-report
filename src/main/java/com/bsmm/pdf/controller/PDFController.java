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
    public ResponseEntity<ByteArrayResource> iText(final HttpServletRequest request, final HttpServletResponse response) {
        ByteArrayResource resource = new ByteArrayResource(pdfGenerator.createItextPdf("order", request, response));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=iText.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(resource.contentLength())
                .body(resource);
    }

    @GetMapping("/open")
    public ResponseEntity<ByteArrayResource> openPDF(final HttpServletRequest request, final HttpServletResponse response) {
        ByteArrayResource resource = new ByteArrayResource(pdfGenerator.createOpenPdf("order", request, response));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=openPDF.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}
