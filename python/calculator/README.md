# Simple Calculator

## Purpose

Simple Calculator is a command-line learning project that demonstrates basic Python
application structure and arithmetic operations. It allows a user to add, subtract,
multiply, or divide two numbers through an interactive terminal menu.

The project also separates calculation logic from user interaction and includes
unit tests for the arithmetic functions.

## Tech Stack

- **Language:** Python 3
- **Interface:** Standard input/output in the terminal
- **Testing:** Python `unittest`
- **Project structure:** Standard Python modules and packages

No third-party libraries or external services are required.

## Features

- Addition
- Subtraction
- Multiplication
- Division
- Division-by-zero handling
- Interactive operation and retry prompts

## Running the Project

From this directory, run:

```bash
python calculator.py
```

Choose an operation from the menu, enter two numbers, and review the result.
Enter `0` to exit.

## Running the Tests

```bash
python -m unittest calculator_operator_test.py
```

## Project Structure

```text
calculator/
├── calculator.py                 # Interactive command-line entry point
├── calculator_operator_test.py   # Unit tests
└── calc_operator/
    ├── __init__.py
    └── calculator_operator.py    # Arithmetic operations
```
