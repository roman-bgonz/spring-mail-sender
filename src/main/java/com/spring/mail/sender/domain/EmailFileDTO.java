package com.spring.mail.sender.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // Setter needed due to form data
@NoArgsConstructor
@AllArgsConstructor
public class EmailFileDTO {

	// DTO = GET/SET to represent json
	private String[] toUser;
	private String subject;
	private String message;
	private MultipartFile file;
}
