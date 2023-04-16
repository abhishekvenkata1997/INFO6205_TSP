package com.tsp.TSP_Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.*;

@SpringBootApplication
public class TspSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TspSpringApplication.class, args);
	}

	@RestController
	public class MyController {

		@GetMapping("/two-opt") // Define the endpoint URL
		public List<Integer> myApi() {
			// Call your method to get the output data
			List<Integer> outputData = TSP.tsp();

			// Return the output data as the response
			return outputData;
		}

	}

}
