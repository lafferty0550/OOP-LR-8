package com.company;

import java.util.*;

public class Task6 {
    public static void main(String[] args) {
        System.out.println("====================TASK1");
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));

        System.out.println("====================TASK2");
        System.out.println(collect("intercontinentalisationalism", 6));
        System.out.println(collect("strengths", 3));

        System.out.println("====================TASK3");
        nicoCipher("mubashirhassan", "crazy");
        nicoCipher("myworldevolvesinhers", "tesh");
        nicoCipher("edabitisamazing", "matt");

        System.out.println("====================TASK4");
        int[] array = {1, 2, 3, 9, 4, 5, 15, 3};
        twoProduct(array, 45);
        int[] array2 = {1, 2, 3, 9, 4, 15, 3, 5};
        twoProduct(array2, 45);
        int[] array3 = {1, 2, -1, 4, 5, 6, 10, 7};
        twoProduct(array3, 20);

        System.out.println("====================TASK5");
        int[] array10 = new int[]{};
        array10 = isExact(6);
        int[] array11 = new int[]{};
        isExact(7);
        for (int d : array10)
            System.out.print(d + " ");
        System.out.println();
        for (int d : array11)
            System.out.print(d + " ");
        System.out.println();


        System.out.println("====================TASK6");
        fractions("0.(6)");
        fractions("1.(1)");
        fractions("3.(142857)");
        fractions("0.19(2367)");
        fractions("0.1097(3)");

        System.out.println("====================TASK7");
        pilish_string("HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHAINCSANDALLTHESECRETSOFTHEUNIVERSE");
        pilish_string("FORALOOP");
        pilish_string("");
        pilish_string("CANIMAKEAGUESSNOW");

        System.out.println("====================TASK9");
        System.out.println(isValid("aabbccc"));
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));

        System.out.println("====================TASK10");
        sumsUp(new int[]{1, 2, 3, 4, 5});
        sumsUp(new int[]{1, 2, 3, 7, 9});
        sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7});
        sumsUp(new int[]{10, 9, 7, 2, 8});
    }

    /**
     * Создайте функцию, которая принимает две строки. Первая строка содержит предложение, содержащее буквы второй
     * строки в последовательной последовательности, но в другом порядке. Скрытая анаграмма должна содержать все буквы,
     * включая дубликаты, из второй строки в любом порядке и не должна содержать никаких других букв алфавита.
     * <p>
     * Напишите функцию, чтобы найти анаграмму второй строки, вложенную где-то в первую строку. Вы должны игнорировать
     * регистр символов, любые пробелы и знаки препинания и возвращать анаграмму в виде строчной строки без пробелов или
     * знаков препинания.
     */
    public static String hiddenAnagram(String sentence, String key) {
        sentence = sentence.toLowerCase(Locale.ROOT);
        key = key.toLowerCase(Locale.ROOT);
        sentence = sentence.replaceAll(" ", "");
        sentence = sentence.replaceAll("[^A-Za-zА-Яа-я0-9]", "");
        key = key.replaceAll("[^A-Za-zА-Яа-я0-9]", "");
        String key2 = key;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sentence.length(); i++) {
            if (result.length() == key.length()) break;
            Character currChar = sentence.charAt(i);
            if (key2.contains(currChar.toString()))
                result.append(currChar);
            else {
                key2 = key;
                result = new StringBuilder();
            }
        }

        return (result.length() != key.length()) ? "not found" : result.toString();
    }


    /**
     * Напишите функцию, которая возвращает массив строк, заполненных из срезов символов n-длины данного слова
     * (срез за другим, в то время как n-длина применяется к слову)
     */
    public static ArrayList<String> collect(String s, int a) {
        ArrayList<String> list = new ArrayList();
        int count = 0;
        StringBuilder resultWord = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            resultWord.append(s.charAt(i));
            if (resultWord.length() == a) {
                list.add(resultWord.toString());
                resultWord = new StringBuilder();
                count++;
            } else if (count == s.length() / a)
                break;
        }

        return list;
    }

    /**
     * В шифре Nico кодирование осуществляется путем создания цифрового ключа и присвоения каждой буквенной позиции
     * сообщения с помощью предоставленного ключа.
     * Создайте функцию, которая принимает два аргумента, message и key, и возвращает закодированное сообщение.
     */
    public static String nicoCipher(String message, String key) {
        ArrayList<String> list = new ArrayList<>();
        char[] keySort = key.toCharArray();
        StringBuilder digits = new StringBuilder();
        Arrays.sort(keySort);

        for (int i = 0; i < keySort.length; i++) {
            for (int j = 0; j < keySort.length; j++) {
                if (key.charAt(i) == keySort[j]) {
                    digits.append(j + 1);
                    keySort[j] = '0';
                }
            }
        }

        for (int i = 0; i < digits.length(); i++) {
            list.add("");
        }
        for (int i = 0; i < message.length(); i++) {
            String nLine = list.get(i % digits.length());
            nLine += Character.toString(message.charAt(i));
            list.remove(i % digits.length());
            list.add(i % digits.length(), nLine);
        }
        int count = 0;
        for (String line : list) {
            if (line.length() < list.get(0).length())
                for (int i = 0; i < list.get(0).length() - line.length(); i++) {
                    line += " ";
                    String nLine = line;
                    list.remove(count);
                    list.add(count, nLine);
                }
            count++;
        }

        count = 1;
        ArrayList<String> listN = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            for (int j = 0; j < digits.length(); j++) {
                if (count == Integer.parseInt(Character.toString(digits.charAt(j)))) {
                    listN.add(list.get(j));
                    count++;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < listN.get(0).length(); i++) {
            for (String line : listN) {
                result.append(line.charAt(i));
            }
        }
        System.out.println(result);
        return result.toString();
    }

    /**
     * Создайте метод, который принимает массив arr и число n и возвращает массив из двух целых чисел из arr,
     * произведение которых равно числу n
     */
    public static int[] twoProduct(int[] array, int digit) {
        int valLowerIndex = 0, valHigherIndex = 0, gap = array.length + 1;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] * array[j] == digit && i - j < gap) {
                    valLowerIndex = array[j];
                    valHigherIndex = array[i];
                    gap = i - j;
                }
            }
        }
        System.out.println(valLowerIndex + " " + valHigherIndex);

        if (gap == array.length + 1)
            return new int[]{};
        else
            return new int[]{valLowerIndex, valHigherIndex};
    }

    /**
     * Создайте рекурсивную функцию, которая проверяет, является ли число точной верхней границей факториала n.
     * Если это так, верните массив точной факториальной границы и n, или иначе, пустой массив.
     **/
    public static int[] isExact(int digit) {
        int check = digit, value = 0;
        for (int i = 1; i <= check; i++) {
            check /= i;
            value = i;
        }

        int result = 1;
        for (int i = 1; i <= value; i++)
            result *= i;

        return (digit != result) ? new int[]{} : new int[]{result, value};
    }

    /**
     * Деление на дробь часто приводит к бесконечно повторяющейся десятичной дроби.
     * Создайте функцию, которая принимает десятичную дробь в строковой форме с повторяющейся частью в круглых скобках
     * и возвращает эквивалентную дробь в строковой форме и в наименьших членах.
     */
    public static void fractions(String value) {
        int index = -1;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == '(') {
                index = i;
                break;
            }
        }
        double digit = Double.parseDouble(value.substring(0, index));
        int period = Integer.parseInt(value.substring(index + 1, value.length() - 1));
        int digitPow = 0, periodPow = 0;
        periodPow = Integer.toString(period).length();
        while (!(digit % 1 == 0)) {
            digitPow++;
            digit *= 10;
        }

        double fullDigit = Double.parseDouble(Integer.toString((int) digit) + '.' + period) * Math.pow(10, periodPow) - Double.parseDouble(value.substring(0, index)) * Math.pow(10, digitPow);
        int beforeSlash = (int) fullDigit;
        int afterSlash = (int) (Math.pow(10, periodPow) - 1) * (int) Math.pow(10, digitPow);

        for (int i = beforeSlash; i > 0; i--) {
            if (beforeSlash % i == 0 && afterSlash % i == 0) {
                beforeSlash /= i;
                afterSlash /= i;
            }
        }
        System.out.println(beforeSlash + "/" + afterSlash);
    }

    /**
     * В этой задаче преобразуйте строку в серию слов (или последовательности символов), разделенных одним пробелом,
     * причем каждое слово имеет одинаковую длину, заданную первыми 15 цифрами десятичного представления числа Пи:
     * Если строка содержит больше символов, чем общее количество, заданное суммой цифр Пи, неиспользуемые символы
     * отбрасываются, и вы будете использовать только те, которые необходимы для формирования 15 слов
     */
    public static void pilish_string(String s) {
        String Pi = "314159265358979";
        StringBuilder result = new StringBuilder();
        while (!s.equals("") && !Pi.equals("")) {
            if (Integer.parseInt(Character.toString(Pi.charAt(0))) > s.length()) {
                result.append(s);
                result.append(String.valueOf(s.charAt(s.length() - 1))
                        .repeat(Math.max(0, Integer.parseInt(Character.toString(Pi.charAt(0))) - s.length())));
                break;
            } else {
                result.append(s.substring(0, Integer.parseInt(Character.toString(Pi.charAt(0))))).append(" ");
                s = s.substring(Integer.parseInt(Character.toString(Pi.charAt(0))));
                Pi = Pi.substring(1);
            }
        }
        System.out.println(result);
    }

    /**
     * Шерлок считает строку действительной, если все символы строки встречаются одинаковое количество раз.
     * Также допустимо, если он может удалить только 1 символ из 1 индекса в строке, а остальные символы будут
     * встречаться одинаковое количество раз. Для данной строки str определите, действительна ли она. Если да,
     * верните «ДА», в противном случае верните «НЕТ».
     **/
    public static boolean isValid(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 1;
        int zapomnit = 0;
        int max = -1;
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), count);
            } else {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
        }
        count = 0;

        for (Integer digit : map.values()) {
            if (digit == zapomnit)
                count++;
            zapomnit = digit;
            if (digit > max)
                max = digit;
        }
        if (count == map.size() - 1)
            return true;
        else if (map.containsValue(1)) {
            count = 0;
            for (Integer digit : map.values()) {
                if (digit == 1)
                    count++;
            }
            return count == 1;
        } else {
            count = 0;
            max -= 1;
            for (Integer digit : map.values()) {
                if (digit == max)
                    count++;
            }
            return count == map.size() - 1;
        }
    }

    /**
     * Создайте функцию, которая получает каждую пару чисел из массива, который суммирует до восьми, и возвращает
     * его как массив пар (отсортированный по возрастанию).
     **/
    public static ArrayList<String> sumsUp(int[] array) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = array.length - 1; i > 1; i--) {
            for (int j = i - 1; j > -1; j--) {
                if (array[i] + array[j] == 8)
                    if (array[i] < array[j])
                        list.add(Integer.toString(array[i]) + " " + Integer.toString(array[j]));
                    else
                        list.add(Integer.toString(array[j]) + " " + Integer.toString(array[i]));
            }
        }
        Collections.reverse(list);
        System.out.println(list);
        return list;
    }
}
