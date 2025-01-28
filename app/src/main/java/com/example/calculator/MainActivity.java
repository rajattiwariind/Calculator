package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        View.OnClickListener listener = view -> {
            Button button = (Button) view;
            String buttonText = button.getText().toString();

            switch (buttonText) {
                case "C":
                    clear();
                    break;
                case "DEL":
                    deleteLastCharacter();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    setOperator(buttonText);
                    break;
                case "=":
                    calculateResult();
                    break;
                default: // Numbers and dot
                    appendToInput(buttonText);
                    break;
            }
        };

        // Attach listener to all buttons
        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnDot, R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply,
                R.id.btnDivide, R.id.btnEquals, R.id.btnClear, R.id.btnDelete
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void clear() {
        currentInput = "";
        operator = "";
        firstOperand = 0;
        tvDisplay.setText("0");
    }

    private void deleteLastCharacter() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            tvDisplay.setText(currentInput.isEmpty() ? "0" : currentInput);
        }
    }

    private void setOperator(String newOperator) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            currentInput = "";
            operator = newOperator;
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    result = secondOperand != 0 ? firstOperand / secondOperand : 0;
                    break;
            }

            tvDisplay.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            operator = "";
        }
    }

    private void appendToInput(String value) {
        currentInput += value;
        tvDisplay.setText(currentInput);
    }
}
