package until;

import java.util.Arrays;
import java.util.stream.Collectors;

public class encrypt {


    // String to Bin
    public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int,
                            .replaceAll(" ", "0")
            );
        }
        return result.toString();

    }
    public static String convertStringToBinary2(String input) {
    StringBuilder sb = new StringBuilder();

    char[] chars = input.toCharArray();
for (char c : chars) {
        String binary = Integer.toBinaryString(c);
        String formatted = String.format("%8s", binary);
        String output = formatted.replaceAll(" ", "0");
        sb.append(output);
        sb.append(" ");
    }
    return sb.toString();}

    // xor 2 binary strings
  public static   String xor(String a, String b)
    {
        String ans = "";
   a = a.replaceAll(" ", "");
        b = b.replaceAll(" ", "");
        // Loop to iterate over the
        // Binary Strings
//        StringBuilder aBuilder = new StringBuilder(a);
//        while (aBuilder.length() < b.length()) aBuilder.insert(0, "0");
//        a = aBuilder.toString();
//        StringBuilder bBuilder = new StringBuilder(b);
//        while (a.length() > bBuilder.length()) bBuilder.insert(0, "0");
//        b = bBuilder.toString();
        b=b.substring(b.length()-a.length());
        int  n=a.length();
        for (int i = 0; i < n; i++)
        {
            // If the Character matches
            if (a.charAt(i) == b.charAt(i))
                ans += "0";
            else
                ans += "1";
           if((i+1)%8==0) ans+=" ";
        }
        return ans;
    }

    public static String binaToStr(String bina){
        String raw = Arrays.stream(bina.split(" "))
                .map(binary -> Integer.parseInt(binary, 2))
                .map(Character::toString)
                .collect(Collectors.joining()); // cut the space

       return raw;

    }
  //   left Circular Shifting bits
 public static    void leftCircularShift(String input, int numBits)
    {
//        String[]  x = input.split(" ");
//        StringBuilder result= new StringBuilder();
//        for(String in : x) {
//            int t_a = Integer.parseInt(in, 2); // binary to decimal
//            t_a = t_a >> 8;
//            int t_b= t_a<<8;
//
//       String    binaStr= Integer.toBinaryString(t_a); // integer to binary
//            String    binaStr2= Integer.toBinaryString(t_b);
//            StringBuilder bBuilder = new StringBuilder(binaStr);
//            while (bBuilder.length() < 8){
//                bBuilder.insert(0, "0"); // thêm 000
//            }
//          result.append(bBuilder.toString()).append(" ");
//        }
////        System.out.println(t_a+"x");
////        t_a= t_a >> numBits;
////        System.out.println(t_a);
////        input = Long.toBinaryString(t_a);
//        return result.toString();
    }

//    public static String encode(String text){
//        String result = convertStringToBinary(text);
//    }
}
