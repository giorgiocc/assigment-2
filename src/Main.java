import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


// კლასი რომლითაც შემოვიტანთ ფაილს
public class Main {
    public static void main(String[] args) {
        try {

            int[] arr = ArrayUtils.readIntegersFromFile("input.txt");


            int sum = ArrayUtils.calculateSum(arr);

            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            System.out.println("Error: Failed to read file");
        } catch (ArrayUtils.InvalidNumberException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


class ArrayUtils {
    private ArrayUtils() {

    }

    //მეთოდი, რომელიც წაიკითხავს ფაილიდან 10 მთელ რიცხვს,
    //ჩაწერს მასივში და დააბრუნებს მას.
    public static int[] readIntegersFromFile(String fileName) throws IOException, InvalidNumberException {
        int[] arr = new int[10];
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        try {
            for (int i = 0; i < 10; i++) {
                String line = br.readLine();
                int num = Integer.parseInt(line);

                if (num < 0) {
                    throw new InvalidNumberException("Number cannot be negative");
                }

                arr[i] = num;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format");
        }

        br.close();
        return arr;
    }

    //მეთოდი, რომლიც პარამეტრად იღებს მასივს და აბრუნებს
    //მასივის ყველა ელემენტის ჯამს.
    public static int calculateSum(int[] arr) {
        int sum = 0;

        for (int num : arr) {
            try {
                sum = Math.addExact(sum, num);
            } catch (ArithmeticException e) {
                System.out.println("Error: Overflow occurred");
            }
        }

        return sum;
    }

    //  InvalidNumberException კლასი.
    public static class InvalidNumberException extends Exception {
        public InvalidNumberException(String message) {
            super(message);
        }
    }
}
