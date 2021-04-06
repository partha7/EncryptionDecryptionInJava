# FoveaKeysEncryption
This code has logic for encryption and decryption primarily written for encryption of various keys needed for authorization during Fovea models and libraries download.

# For Encryption
1. Place the keys to be encrypted inside the KeysToEncrypt.json file.
2. Add the Key and Ivec for encryption in the same json file.
3. Run the ToEncrypt class.
4. The encrypted result will be stored in a text file (encryptedValues.txt).

# For Decryption
1. Run the ToDecrypt class.
2. It will automatically take the encrypted values from the "encryptedValues.txt" file as input for decryption.
3. The decrypted values will be stored in a text file (decryptedValues.txt).

# Example

## KeysToEncrypt.json :

KEY0, KEY1, KEY2, KEY3, KEY4 : The values to be encrypted. <br/>
ENCKEY, ENCIVS : Keys used for encrypting the above values. <br/>

{ <br/>
"ENCKEY": "quwyfr8273tgfdi1", <br/>
"ENCIVS": "2387rgfc", <br/>
"KEY0" : "This is some", <br/>
"KEY1" : "code", <br/>
"KEY2" : "for encryption", <br/>
"KEY3" : "and decryption", <br/>
"KEY4" : "written in Java" <br/>
} <br/>


## encryptedValues.txt :<br/>

KEY0: kJRZJPBYFvtdQbTWNkApIA== <br/>
KEY1: 7saRdKH0rHCinkR1ZMfosg== <br/>
KEY2: EW4LhAb9HOBBrWvbuGyCaw== <br/>
KEY3: 0smOcfJ5WiBtcBbFO949mg== <br/>
KEY4: YLPQ0yKJinRaZvwgIek2KA== <br/>


## decryptedValues.txt :

KEY0: This is some <br/>
KEY1: code <br/>
KEY2: for encryption <br/>
KEY3: and decryption <br/>
KEY4: written in Java <br/>


