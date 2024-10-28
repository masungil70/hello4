package org.kosa.hello;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestHelloController {

	@GetMapping("/foo")
	public ResponseEntity<Object> foo() {
		return ResponseEntity.ok(
				Map.of(
					"status", "success", 
					"message" ,"안녕하세요"));
	}
}
