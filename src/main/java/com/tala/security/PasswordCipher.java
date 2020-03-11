package com.tala.security;


import java.security.Key;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PasswordCipher {
    private static String algorithm = "AES";
    private static Key key = null;
    private static Cipher cipher = null;

    private final static String COMMAND_ENCRYPT = "-encrypt";
    private final static String COMMAND_DECRYPT = "-decrypt";

    /**
     * Constructor
     */
    public PasswordCipher() {
    }

    /**
     * Setup Cipher
     *
     * @throws Exception
     */
    private void setupCipher() throws Exception {
        byte[] keyBytes = "Fw2_a!hT3sd$8GxK".getBytes("UTF-8");

        key = new SecretKeySpec( keyBytes, algorithm );

        cipher = Cipher.getInstance( algorithm );
    }

    /**
     * Converts Hex string to byte array
     *
     * @param input input string
     * @return byte array
     * @throws Exception
     */
    private byte[] hexToByte( String input ) throws Exception {
        int inputLength = input.length();
        int size = inputLength / 2;

        byte[] bytes = new byte[size];

        int j = 0;

        for (int i = 0; i < inputLength; i += 2) {
            bytes[j] = (byte) ((Character.digit(input.charAt(i), 16) << 4) + Character.digit(input.charAt(i + 1), 16));
            j++;
        }

        return bytes;
    }

    /**
     * Converts byte array to hex string
     *
     * @param bytes bytes array
     * @return hex string
     */
    private String byteToHex( byte[] bytes ) {
        StringBuffer buffer = new StringBuffer();

        if (bytes != null) {
            for (int i = 0; i < bytes.length; i++) {
                String temp = Integer.toHexString(0xff & bytes[i]).toUpperCase(Locale.ENGLISH);

                // Add leading zero for hex value if required
                if (temp.length() == 1) {
                    buffer.append('0');
                }

                buffer.append(temp);
            }
        }

        return buffer.toString();
    }

    /**
     * Encrypts the plain text string input
     *
     * @param input plain text string
     * @return encrypted bytes
     * @throws Exception
     */
    public String encrypt( String input ) throws Exception {
        setupCipher();

        cipher.init( Cipher.ENCRYPT_MODE, key );

        return byteToHex( cipher.doFinal(input.getBytes("UTF-8")) );
    }

    /**
     * Decrypts the encrypted string
     *
     * @param encBytes encrypted bytes
     * @return plain text string
     * @throws Exception
     */
    public String decrypt( String encBytes ) throws Exception {
        setupCipher();

        cipher.init( Cipher.DECRYPT_MODE, key );

        return new String( cipher.doFinal(hexToByte(encBytes)), "UTF-8" );
    }

    /**
     * Main for testing and encrypting plain text strings
     *
     * @param args
     */
    public static void main( String[] args ) {
        try {
            int numArgs = args.length;

            if (numArgs == 2) {
                PasswordCipher pCipher = new PasswordCipher();

                String command = args[0];
                String input = args[1];

                System.out.println("Text Input: " + input);

                if (command.equalsIgnoreCase(COMMAND_ENCRYPT)) {
                    String encString = pCipher.encrypt( input );
                    System.out.println("Encrypted: " + encString);
                }
                else if (command.equalsIgnoreCase(COMMAND_DECRYPT)) {
                    String decString = pCipher.decrypt( input );
                    System.out.println("Decrypted: " + decString);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error encrypting/decrypting  text: " + e.getMessage());
        }
    }
}