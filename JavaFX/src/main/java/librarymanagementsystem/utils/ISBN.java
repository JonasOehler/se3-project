package librarymanagementsystem.utils;

public class ISBN {
    public static boolean isbnRequirements(String isbn) {
        if (isbn.length() == 10) {
            int sum = 0;
            int rightMostDigitIndex = 10;

            for (int i = 1; i <= isbn.length(); i++) {
                int digit = Integer.parseInt(isbn.substring(rightMostDigitIndex -1, rightMostDigitIndex));
                rightMostDigitIndex--;
                sum += i * digit;

            }
            System.out.println(sum);
            if ((sum % 11) == 0) return true;

        } else if (isbn.length() == 13) {
            int sum = 0;

            for(int i = 0; i < 13; i++) {
                int digit = Integer.parseInt(isbn.substring(i,i+1));
                if(i % 2 == 0) {
                    sum += digit;
                } else {
                    sum += digit * 3;
                }
            }

            if(sum % 10 == 0) return true;
        }

        return false;
    }


}
