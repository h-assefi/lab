def add(first_value, second_value):
    return first_value + second_value

def minus(first_value, second_value):
    return first_value - second_value

def multiply(first_value, second_value):
    return first_value * second_value

def division(first_value, second_value):
    try:
        return first_value / second_value
    except ZeroDivisionError:
        return 0
