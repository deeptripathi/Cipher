package com.tala.security;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCipherTest {


    @org.junit.jupiter.api.Test
    void encrypt() throws Exception {

          PasswordCipher cipher = new PasswordCipher();
          String encrypt = cipher.encrypt("test");

          assertEquals(encrypt,"2DDC25C36BFAB4BF0B09806D203D5A5B");
    }

    @org.junit.jupiter.api.Test
    void decrypt() throws Exception {
        PasswordCipher cipher = new PasswordCipher();
        String decrypt = cipher.decrypt("2DDC25C36BFAB4BF0B09806D203D5A5B");
        assertEquals(decrypt,"test");
    }
}