package com.daw.contafin;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.apache.poi.util.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.daw.contafin.user.User;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Configuration freemarkerConfig;

    public void sendSimpleMessage(User user) throws MessagingException, IOException, TemplateException {
    	
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        
        //Process the template and add the attributes to the model
        Template t = freemarkerConfig.getTemplate("emailTemplate.ftl");
        Map < String, Object > model = new HashMap < String, Object > ();
        model.put("name", user.getName());
        //Process the template
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        	//Added logo as attachment
        helper.addAttachment("Logo.png",new FileDataSource(".//src//main//resources//static/img/Logo.png"));
        //Add sender, recipient, subject and body to the message
        helper.setTo(user.getEmail());
        helper.setText(html, true);
        helper.setSubject("Bienvenido a CONTaFIN ");
        helper.setFrom("no-reply@hotmail.es");

        //Send the message
        emailSender.send(message);
    }

}
