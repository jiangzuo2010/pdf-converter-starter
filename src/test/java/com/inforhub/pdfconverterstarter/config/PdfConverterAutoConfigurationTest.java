package com.inforhub.pdfconverterstarter.config;

import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.document.DefaultDocumentFormatRegistry;
import org.jodconverter.core.office.OfficeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Description
 *
 * @author zorro
 */
@SpringBootTest
@ContextConfiguration(classes = PdfConverterAutoConfiguration.class)
public class PdfConverterAutoConfigurationTest {

    @Autowired
    private DocumentConverter documentConverter;

    @Test
    public void contextLoads() {
        assertThat(documentConverter).isNotNull();
    }

    @Test
    public void convert() throws OfficeException, IOException {
        File inputFile = new File("/Users/zorro/Downloads/保密协议范本.doc");
//        File outputFile = File.createTempFile("output", ".pdf");
        File outputFile = new File("/Users/zorro/Downloads/output.pdf");

        documentConverter.convert(inputFile)
                .as(DefaultDocumentFormatRegistry.DOCX)
                .to(outputFile)
                .as(DefaultDocumentFormatRegistry.PDF)
                .execute();

        assertThat(outputFile.exists()).isTrue();
    }
}