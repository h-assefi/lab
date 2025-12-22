import calculator_operator

def welcome_message():
    print('Welcome to SIMPLE CALC')

def error_message(message):
    print(f'Error, {message}')

def retry_operation():
    result = input("Retry YES/NO? ")
    if(result.lower() == "yes" or result.lower() == 'y'):
        return True
    else: return False

def accept_number(what_number):
    result = input(f'Enter the {what_number} number: ')
    if not (result.isnumeric):
        error_message("invalid number")
        if(retry_operation() == True):
            accept_number(what_number)
        else: return -1
    else: return int(result)

def operate(selected_option, first_number, second_number):
    if(selected_option == 1):
        return calculator_operator.add(first_value=first_number, second_value=second_number)
    elif(selected_option == 2):
        return calculator_operator.minus(first_value=first_number, second_value=second_number)
    elif(selected_option == 3):
        return calculator_operator.multiply(first_value=first_number, second_value=second_number)
    elif(selected_option == 4):
        return calculator_operator.division(first_value=first_number, second_value=second_number)


def options_index():
    print('______________________Options________________________')
    print('1 - add')
    print('2 - minus')
    print('3 - multiply')
    print('4 - division')
    print('0 - exit')
    selected_value = input('please choose the operation by entering its index number: ')
    if( selected_value.isnumeric()):
        selected_value = int(selected_value)
        if(selected_value>= 1 and selected_value <= 4):
           first_number = accept_number("first")
           second_number = accept_number("second")
           if(first_number == -1 or second_number == -1):
               print("Operation aborted.")
               options_index()
           else:       
                print(f'result: {operate(selected_value, first_number, second_number)}')
                if(retry_operation()):
                    options_index()
        elif(selected_value == 0):
            pass
        else:
            error_message("invalid operation")
            if(retry_operation()):
                options_index()



    else:
        print('ERROR: operation index must be between 1-4.')

welcome_message()
options_index()