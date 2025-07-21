import java.util.Scanner;
public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int retValue = 0;
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                String trash = pipe.nextLine();
                System.out.println("You must enter an integer, not " + trash);
            }
        } while (!done);
        return retValue;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double retValue = 0;
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                String trash = pipe.nextLine();
                System.out.println("You must enter a double, not " + trash);
            }
        } while (!done);
        return retValue;

    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retValue = 0;
        boolean done = false;
        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                pipe.nextLine();
                if (retValue >= low && retValue <= high) {
                    done = true;
                } else {
                    System.out.println("Input must be between " + low + " and " + high);
                }
            } else {
                String trash = pipe.nextLine();
                System.out.println("You must enter an integer, not " + trash);
            }
        } while (!done);
        return retValue;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retValue = 0;
        boolean done = false;
        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine();
                if (retValue >= low && retValue <= high) {
                    done = true;
                } else {
                    System.out.println("Input must be between " + low + " and " + high);
                }
            } else {
                String trash = pipe.nextLine();
                System.out.println("You must enter a double value, not " + trash);
            }
        } while (!done);
        return retValue;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String response = "";
        boolean valid = false;
        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                valid = true;
                return true;
            } else if (response.equalsIgnoreCase("N")) {
                valid = false;
                return false;
            } else {
                System.out.println("You must enter Y or N");
            }
        } while (!valid);
        return false;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String response = "";
        boolean valid = false;
        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regEx)) {
                valid = true;
            } else {
                System.out.println("Input does not match the required pattern");
            }
        } while (!valid);
        return response;
    }
    public static void prettyHeader(String msg) {
        int width = 60;
        int msgLen = msg.length();
        int totalPadding = width - msgLen - 6;
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;

        for (int i = 0; i < width; i++) System.out.print("*");
        System.out.println();

        System.out.print("***");
        for (int i = 0; i < leftPadding; i++) System.out.print(" ");
        System.out.print(msg);
        for (int i = 0; i < rightPadding; i++) System.out.print(" ");
        System.out.println("***");

        for (int i = 0; i < width; i++) System.out.print("*");
        System.out.println();
    }


}
