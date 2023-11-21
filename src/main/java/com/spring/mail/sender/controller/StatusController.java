package com.spring.mail.sender.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/status")
public class StatusController {

	/** Application name. */
	private String name;

	/** Application version. */
	private String version;

	/**
	 * Instantiates a new status controller.
	 *
	 * @param name    Application name
	 * @param version Version
	 */
	StatusController(@Value("${spring.application.name}") String name,
			@Value("${spring.application.version}") String version) {
		super();
		this.name = name;
		this.version = version;
	}

	@GetMapping
	public ResponseEntity<?> getStatus() {
		return ResponseEntity.ok(Map.of("appName", name, "appVersion", version));
	}

}
