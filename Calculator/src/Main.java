import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //создаем сканнер для считывания ввода
        Scanner inputScanner = new Scanner(System.in);
        //выводим в консоль результат работы метода calc()
        System.out.println(calc(inputScanner.nextLine()));
    }

    private static String  calc(String input) {

        //разбиваем на отдельные токены строку ввода
        String[] tokens = input.split("\\s+");

        //проверяем ввод на валидность
        checkIfValidInput(tokens);

        //если ввод был арабскими цифрами, то
        if(checkIfValidArabic(tokens[0])){
            //вызываем метод calculte() для проведения арифм. операции и возвращаем результат
            String result = calculate(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[2]), tokens[1]);
            return result;
            //если же ввод был римскими цифрами, то
        } else {
            //вызываем метод calculte() для проведения арифм. операции и передаем сконвертированные в арабские цифры операнды
            String result = calculate(convertRomanToArabic(tokens[0]),convertRomanToArabic(tokens[2]),tokens[1]);
            //конвертируем результат в римские цифры и возвращаем
            return convertArabicToRoman(result);
        }
    }
    private static void checkIfValidInput(String[] inputValue){

        //проверка наличия оператора
        if(inputValue.length < 3){
            throw new RuntimeException("Cтрока не является математической операцией");
        }

        //проверка формата два операнда и один оператор
        if(!(inputValue.length == 3 && checkIfValidOperator(inputValue[1]))){
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        //проверка что оба операнда - либо арабские, либо римские
        if(!(checkIfValidArabic(inputValue[0]) && checkIfValidArabic(inputValue[2]) || checkIfValidRoman(inputValue[0]) && checkIfValidRoman(inputValue[2]))) {
            throw new RuntimeException("Некорректный ввод операндов: используйте арабские числа от 1 - 10 либо римские от I до X");
        }

    }
    private static boolean checkIfValidArabic(String value){

        switch (value){
            case "0" , "1" , "2", "3", "4", "5", "6", "7", "8", "9", "10":
                return true;
            default:
                return false;
        }
    }
    private static boolean checkIfValidRoman(String value){

        switch (value){
            case "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X":
                return true;
            default:
                return false;
        }
    }
    private static boolean checkIfValidOperator(String value){

        switch (value){
            case "+", "-", "*", "/":
                return true;
            default:
                return false;
        }
    }
    private static int convertRomanToArabic(String value){
        switch (value){
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                return -1;
        }
    }
    private static String convertArabicToRoman(String value){

        int intValue = Integer.parseInt(value);

       if (intValue <= 0) {
           throw new RuntimeException("В римской системе нет отрицательных чисел и ноля");
       }

       if (intValue == 100 ){
           return "C";
       }else{

           String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
           String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};

           if (value.length() > 1){
               return tens[Character.getNumericValue(value.charAt(0))] + ones[Character.getNumericValue(value.charAt(1))];
           } else {
               return ones[Character.getNumericValue(value.charAt(0))];
           }

       }
    }
    private static String calculate(int firstOperand, int secondOperand, String operator){

        switch (operator){
            case "+":
                return Integer.toString(firstOperand + secondOperand);
            case "-":
                return Integer.toString(firstOperand - secondOperand);
            case "*":
                return Integer.toString(firstOperand * secondOperand);
            case "/":
                if(secondOperand != 0){
                    return Integer.toString(firstOperand / secondOperand);
                }
                else {
                    throw new RuntimeException("На ноль делить нельзя");
                }
            default:
                return "Ошибка вычисления";
        }

    }
}