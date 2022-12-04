package com.example.study4test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import until.encrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest
class Study4TestApplicationTests {


    @Test
    public static void main(String[] args) {
        String text="https://study4.com/media/tez_media1/img/ets_toeic_2022_test_2_1.png";
        String binText=encrypt.convertStringToBinary(text);

        String key="5gh9k8h7v6n5d4v6t0k99jhgfdsrtb6g7[1], [2], [3], [4] h7v66t0k99jhgfdsrtb6g7u8jh5fn5d4v6t0k99jhgfdsrtbvỵ9m55we90ru5yw3904iy4e51N5we90ru5yw3904iy4e5j.mganjet6r3i6t0k99jhgfdsrtb6g.7u8jh5f8i9h6g4dczl2sahkgfdbgdvỵ9m4e5g";
        String key2="5gh9k8h7v6n5d4v6t0k99jhgfdsrtb6g7[1], [2], [3], [4] h7v66t0k99jhgfdsrtb6g7u8jh5fn5d4v6t0k99jhgfdsrtbvỵ9m55we90ru5yw3904iy4e51N5we90ru5yw3904iy4e5j.mganjet6r3i6t0k99jhgfdsrtb6g.7u8jh5f8i9h6g4dczl2sahkgfdbgdvỵ9m4e5g";
        String binKey=encrypt.convertStringToBinary(key);
//    // xor 2 hex
        String xorHex=encrypt.xor(binText,binKey); // mã hóa
      //  String result = encrypt.xor(xorHex,binKey); // giải mã
        System.out.println(xorHex);
      //  System.out.println(result);
//        byte[] UTF8Bytes = xorHex.getBytes(StandardCharsets.US_ASCII);
//        String StringData2 = new String(UTF8Bytes, StandardCharsets.US_ASCII);
//        System.out.println(StringData2);
        System.out.println(encrypt.binaToStr(xorHex)); // lưu ở db
     String resultDb = encrypt.convertStringToBinary2(encrypt.binaToStr(xorHex));
        String result = encrypt.xor(resultDb,binKey); // giải mã
        System.out.println(encrypt.binaToStr(result));
    }



}
