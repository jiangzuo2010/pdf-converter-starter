package com.inforhub.pdfconverterstarter.config;

import jakarta.annotation.PreDestroy;
import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.office.OfficeException;
import org.jodconverter.local.LocalConverter;
import org.jodconverter.local.office.LocalOfficeManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * PDF转换器自动配置
 *
 * @author zorro
 */
@Configuration
public class PdfConverterAutoConfiguration {

    private LocalOfficeManager officeManager;

    @Value("${libreoffice.path}")
    private String libreofficePath;

    @Bean
    public DocumentConverter documentConverter() {
        officeManager = LocalOfficeManager.builder().install().build();
        try {
            officeManager.start();
        } catch (OfficeException e) {
            throw new RuntimeException(e);
        }

        return LocalConverter.builder().officeManager(officeManager).build();
    }

    @PreDestroy
    public void preDestroy() {
        if (officeManager != null) {
            try {
                officeManager.stop();
            } catch (OfficeException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
