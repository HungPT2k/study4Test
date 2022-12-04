package com.example.study4test.until;

import java.util.Arrays;
import java.util.stream.Collectors;

public class encrypt {


    // String to Bin (liền)
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

    // String to bin (8bit)
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
        return sb.toString();
    }


    // xor 2 binary strings(text và key)
    public static String xor(String a, String b) {
        String ans = "";
        a = a.replaceAll(" ", ""); // xóa khoảng trắng
        b = b.replaceAll(" ", "");
        // Loop to iterate over the
        // Binary Strings
//        StringBuilder aBuilder = new StringBuilder(a);
//        while (aBuilder.length() < b.length()) aBuilder.insert(0, "0");
//        a = aBuilder.toString();
//        StringBuilder bBuilder = new StringBuilder(b);
//        while (a.length() > bBuilder.length()) bBuilder.insert(0, "0");
//        b = bBuilder.toString();
        b = b.substring(b.length() - a.length()); // cắt chuỗi b sao cho độ dài = a
        int n = a.length();
        for (int i = 0; i < n; i++) {
            // If the Character matches
            if (a.charAt(i) == b.charAt(i))
                ans += "0";
            else
                ans += "1";
            if ((i + 1) % 8 == 0) ans += " ";
        }
        return ans;
    }

    // Binar to string ( text và key sau khi xor chuyển thành string và lưu ở db )
    public static String binaToStr(String bina) {
        String raw = Arrays.stream(bina.split(" "))
                .map(binary -> Integer.parseInt(binary, 2))
                .map(Character::toString)
                .collect(Collectors.joining()); // cut the space

        return raw;
    }

    public static String Encode(String text, String key){
        String binText=encrypt.convertStringToBinary(text);
        String binKey=encrypt.convertStringToBinary(key);
        String xorHex=encrypt.xor(binText,binKey);
        return encrypt.binaToStr(xorHex);
    }
    public static String Decode(String resultDb,String key){
        String binKey=encrypt.convertStringToBinary(key);
        String result = encrypt.xor(resultDb,binKey);
        result =encrypt.binaToStr(result);
        return result;
    }

}
