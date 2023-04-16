package com.tsp.TSP_Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TspSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TspSpringApplication.class, args);
	}

	@RestController
	public class MyController {

		@GetMapping("/my-api") // Define the endpoint URL
		public String myApi() {
			// Call your method to get the output data
			AnotherClass anotherClass = new AnotherClass();
			String outputData = anotherClass.someMethod();

			// Return the output data as the response
			return outputData;
		}
	}

	public class AnotherClass {
		public String someMethod() {
			// Your method implementation here
			return "Hello from AnotherClass!";
		}
	}
}
