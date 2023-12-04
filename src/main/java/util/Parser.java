package util;

public class Parser {

    public static Integer parsePrice(String price) {
        String extractedDigits = price.replaceAll("[^0-9]", "");
        return Integer.parseInt(extractedDigits);
    }
}
