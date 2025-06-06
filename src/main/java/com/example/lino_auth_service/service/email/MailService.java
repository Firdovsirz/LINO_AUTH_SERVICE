package com.example.lino_auth_service.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public MailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendOtpEmail(String email, int otpCode) throws MessagingException {
        String otpFormatted = String.format("%06d", otpCode);
        otpFormatted = otpFormatted.substring(0, 3) + "-" + otpFormatted.substring(3);

        Context context = new Context();
        context.setVariable("otp", otpFormatted);

        String htmlContent = templateEngine.process("otp-mail", context);
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Lino Verification Code");
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}