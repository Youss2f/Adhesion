package ma.fm6.membership.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    
    @Autowired
    private JavaMailSender emailSender;
    
    @Value("${spring.mail.username:noreply@fm6.ma}")
    private String fromEmail;
    
    public void sendApplicationConfirmation(String to, Long applicationId) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject("FM6 - Confirmation de soumission de demande d'adhésion");
            message.setText("Bonjour,\n\n" +
                    "Votre demande d'adhésion a été soumise avec succès.\n" +
                    "Numéro de demande: " + applicationId + "\n\n" +
                    "Vous recevrez une notification par email dès que votre demande sera traitée.\n\n" +
                    "Cordialement,\n" +
                    "L'équipe FM6");
            
            emailSender.send(message);
            logger.info("Email de confirmation envoyé à {}", to);
        } catch (Exception e) {
            logger.error("Erreur lors de l'envoi de l'email de confirmation: {}", e.getMessage());
        }
    }
    
    public void sendApplicationValidation(String to, Long applicationId) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject("FM6 - Demande d'adhésion validée");
            message.setText("Bonjour,\n\n" +
                    "Félicitations! Votre demande d'adhésion a été validée.\n" +
                    "Numéro de demande: " + applicationId + "\n\n" +
                    "Vous pouvez maintenant bénéficier des services de la Fondation Mohammed VI.\n\n" +
                    "Cordialement,\n" +
                    "L'équipe FM6");
            
            emailSender.send(message);
            logger.info("Email de validation envoyé à {}", to);
        } catch (Exception e) {
            logger.error("Erreur lors de l'envoi de l'email de validation: {}", e.getMessage());
        }
    }
    
    public void sendApplicationRejection(String to, Long applicationId, String reason) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject("FM6 - Demande d'adhésion rejetée");
            message.setText("Bonjour,\n\n" +
                    "Nous regrettons de vous informer que votre demande d'adhésion a été rejetée.\n" +
                    "Numéro de demande: " + applicationId + "\n\n" +
                    "Motif: " + (reason != null ? reason : "Non spécifié") + "\n\n" +
                    "Vous pouvez soumettre une nouvelle demande après avoir pris en compte les remarques.\n\n" +
                    "Cordialement,\n" +
                    "L'équipe FM6");
            
            emailSender.send(message);
            logger.info("Email de rejet envoyé à {}", to);
        } catch (Exception e) {
            logger.error("Erreur lors de l'envoi de l'email de rejet: {}", e.getMessage());
        }
    }
}