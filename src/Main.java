import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Создать программу, которая переворачивает вводимые строки (читает справа налево).
        Stack stack;
        Scanner scanner = new Scanner(System.in);
        char[] chars = scanner.nextLine().toCharArray();
        stack = new Stack(chars.length);
        for (char each : chars) {
            stack.push(each);
        }
        while (!stack.isEmpty()) {
            System.out.print((char) stack.pop());
        }
    }

}
