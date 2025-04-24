package com.daw.contafin.service;

import com.daw.contafin.dto.UserDto;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.poi.sl.draw.geom.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.sonar.check.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmailServiceTests {

    @InjectMocks
    @Spy
    EmailService emailService;

    @Mock
    JavaMailSender emailSender;

    @Mock
    MimeMessage mimeMessage;

    @Mock
    Configuration freemarkerConfig;



    @Test
    public void sendSimpleMessage() throws IOException, MessagingException, TemplateException {
        //GIVEN
        UserDto userDto = new UserDto();
        userDto.setName("1");
        userDto.setEmail("1@gmail.com");

        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        cfg.setDirectoryForTemplateLoading(new File("./src/main/resources/templates"));
        Template template = cfg.getTemplate("emailTemplate.ftl");

        Mockito.doNothing().when(emailSender).send(any(MimeMessage.class));
        when(emailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(freemarkerConfig.getTemplate(any())).thenReturn(template);


        emailService.sendSimpleMessage(userDto);

        verify(emailSender).send(any(MimeMessage.class));
    }

    @Test
    public void sendSimpleMessageError() {
        //GIVEN
        Exception npe = new NullPointerException();
        when(emailSender.createMimeMessage()).thenThrow(npe);

        try {
            emailService.sendSimpleMessage(new UserDto());
            fail();
        } catch (Exception e) {

        }

    }

}
