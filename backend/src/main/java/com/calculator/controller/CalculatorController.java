package com.calculator.controller;

import com.calculator.model.CalculationRequest;
import com.calculator.model.CalculationResponse;
import com.calculator.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@RequestBody CalculationRequest request) {
        try {
            double result;
            String operation = request.getOperation().toUpperCase();

            switch (operation) {
                case "ADD":
                    result = calculatorService.add(request.getNum1(), request.getNum2());
                    break;
                case "SUBTRACT":
                    result = calculatorService.subtract(request.getNum1(), request.getNum2());
                    break;
                case "MULTIPLY":
                    result = calculatorService.multiply(request.getNum1(), request.getNum2());
                    break;
                case "DIVIDE":
                    result = calculatorService.divide(request.getNum1(), request.getNum2());
                    break;
                default:
                    return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Invalid operation. Supported: ADD, SUBTRACT, MULTIPLY, DIVIDE"));
            }

            CalculationResponse response = new CalculationResponse(
                result, operation, request.getNum1(), request.getNum2()
            );
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new ErrorResponse("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Calculator service is running");
    }

    private static class ErrorResponse {
        private final String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
