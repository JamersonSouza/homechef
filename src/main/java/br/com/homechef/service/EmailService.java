package br.com.homechef.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.homechef.enuns.TipoEmail;



@Service
public class EmailService {

	 @Autowired
	JavaMailSender mailSender;
	
	public void enviar(String destinatario, String assunto, String corpo, TipoEmail tipo) {
		new Thread() {
			
			@Override
			public void run() {
				if (tipo.equals(TipoEmail.TEXTO)) {
					SimpleMailMessage message = new SimpleMailMessage();
					message.setSubject(assunto);
					message.setText(corpo);
					message.setTo(destinatario);
					message.setFrom("Jolteam <jolteam.ifpe@gmail.com>");
					
					try {
						mailSender.send(message);
					} catch (Exception e) {
						throw new RuntimeException("Erro ao enviar e-mail: "+e.getMessage());
					}
				} else {
					MimeMessage mail = mailSender.createMimeMessage();

		            MimeMessageHelper helper = new MimeMessageHelper( mail );
		            try {
		            	helper.setSubject(assunto);
		            	helper.setText(corpo, true);
						helper.setTo(destinatario);
						helper.setFrom("Jolteam <jolteam.ifpe@gmail.com>");
						
						mailSender.send(mail);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						throw new RuntimeException("Erro ao enviar e-mail: "+e.getMessage());
					}
				}
			}
			
		}.start();
	}
	
}
