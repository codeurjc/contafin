package com.daw.contafin;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.daw.contafin.user.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Configuration freemarkerConfig;

    public void sendSimpleMessage(User user) throws MessagingException, IOException, TemplateException {
    		
    		//Create a MimeMessage
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        
        //Process the template and add the attributes to the model
        Template t = freemarkerConfig.getTemplate("emailTemplate.ftl");
        Map < String, Object > model = new HashMap < String, Object > ();
        
        //Decode the image to insert it into the template.
        Path path = Paths.get(".//src//main//resources//static/img/Logo.png");
		byte[]logo = Files.readAllBytes(path);
		byte[]encodeLogo = Base64.encodeBase64(logo);
		String logoDataAsBase64 = new String(encodeLogo);
		String logoAsBase64 = "data:image/png;base64," + logoDataAsBase64;
        model.put("name", user.getName());
        model.put("logoAsBase64", logoAsBase64);
        
        //Process the template
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        //Add sender, recipient, subject and body to the message
        helper.setTo(user.getEmail());
        helper.setText(html, true);
        helper.setSubject("Bienvenido a CONTaFIN ");
        helper.setFrom("no-reply@hotmail.es");

        //Send the message
        emailSender.send(message);
    }

}
