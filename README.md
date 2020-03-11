# Cipher
Cipher can help to encrypt and decrypt plan text.

Cipher jar can be used in the projects to encrypt/decrypt plan text passwords in configuration file.
The encrypted text will be hard to decrypt without sharing the JAR.
The JAR can be plugged in project pipelines to prevent the distribution of plain text. 

Usage to encrypt text.

``java -cp Cipher-1.0-SNAPSHOT.jar com.tala.security.PasswordCipher -encrypt TestPassword``

Output

``Text Input: TestPassword``
 
``Encrypted: B15DC96EB26230D0E5CE4643C50EE2E1``
  
  Usage to decrypt the text.
  
  ``java -cp Cipher-1.0-SNAPSHOT.jar com.tala.security.PasswordCipher -decrypt B15DC96EB26230D0E5CE4643C50EE2E1``
  
  Output
  
  ``Text Input: B15DC96EB26230D0E5CE4643C50EE2E1``
  
  ``Decrypted: TestPassword``