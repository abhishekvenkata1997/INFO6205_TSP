package com.tsp.TSP_Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class TspSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TspSpringApplication.class, args);
	}

	@RestController
	public class MyController {

		@CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/two-opt")
		// Define the endpoint URL
		public List<Integer> myApi() {
			// Call your method to get the output data
			List<Integer> outputData = new TSP().tsp(1);

			// Return the output data as the response
			return outputData;
		}

		@CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/distance")
		public double[][] myApii() {
			double[][] outputData = new TSP().getGraph();
			return outputData;
		}

		@CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/three-opt")
		public List<Integer> myApi2() {

			// Call your three opt method
			List<Integer> outputData = new TSP().tsp(2);

			// return data from three opt
			return outputData;
		}

		@CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/christofides")
		public List<Integer> myApi3() {

			// call christofides method
			List<Integer> outputData = new TSP().tsp(0);

			// return data from christofides
			return outputData;
		}

		@CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/aco")
		public List<Integer> myApi4() {

			// call aco method
			List<Integer> outputData = new TSP().tsp(3);

			// return data from aco
			return outputData;
		}

		@CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/sa")
		public List<Integer> myApi5() {

			// call sa method
			List<Integer> outputData = new TSP().tsp(4);

			// return data from simulated annealing

			return outputData;
		}

	}

}
