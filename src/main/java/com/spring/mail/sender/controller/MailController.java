package com.spring.mail.sender.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mail.sender.domain.EmailDTO;
import com.spring.mail.sender.domain.EmailFileDTO;
import com.spring.mail.sender.service.IEmailService;

@CrossOrigin
@RestController
public class MailController {

	@Autowired
	private IEmailService emailService;

	@PostMapping("/sendMessage")
	public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailDTO email) {

		emailService.sendEmail(email.getToUser(), email.getSubject(), email.getMessage());
		return ResponseEntity.ok(Map.of("estado", "Enviado"));
	}

	/**
	 * As file types and json are not compatibles in a certain way, we should
	 * receive this information by FormData
	 * 
	 * @param email
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/sendMessageFile")
	public ResponseEntity<?> receiveRequestEmailWithFile(@ModelAttribute EmailFileDTO email) {

		try {
			String fileName = email.getFile().getOriginalFilename();
			Path path = Paths.get("src/main/resources/files/" + fileName);
			Files.createDirectories(path.getParent());
			Files.copy(email.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			File file = path.toFile();
			emailService.sendEmailWithFile(email.getToUser(), email.getSubject(), email.getMessage(), file);

			Files.delete(path);
			return ResponseEntity.ok(Map.of("estado", "Enviado"));

		} catch (IOException e) {
			throw new RuntimeException("Error al enviar el correo con el archivo adjunto. " + e.getMessage());
		}
	}
}
