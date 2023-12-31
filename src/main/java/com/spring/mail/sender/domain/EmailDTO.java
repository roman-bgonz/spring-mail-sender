package com.spring.mail.sender.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

	// DTO = GET/SET to represent json
	private String[] toUser;
	private String subject;
	private String message;
}
