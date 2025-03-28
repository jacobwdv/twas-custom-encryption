package com.jake.custom.security.customencryption;

import java.util.HashMap;

import com.ibm.wsspi.security.crypto.CustomPasswordEncryption;
import com.ibm.wsspi.security.crypto.EncryptedInfo;
import com.ibm.wsspi.security.crypto.PasswordDecryptException;
import com.ibm.wsspi.security.crypto.PasswordEncryptException;

/**
 * Hello world!
 */
public class WasCustomerPasswordEncryption implements CustomPasswordEncryption {
	public static final String ENC_STR = "ENCRYPTED_";
	public static final String ALIAS = "jake";

	@Override
	public byte[] decrypt(EncryptedInfo info) throws PasswordDecryptException {
		String encryptedData = new String(info.getEncryptedBytes());
		return encryptedData.substring(ENC_STR.length()).getBytes();

	}

	@Override
	public EncryptedInfo encrypt(byte[] msg) throws PasswordEncryptException {
		String bytes = new String(msg);
		EncryptedInfo info = new EncryptedInfo((ENC_STR + bytes).getBytes(), ALIAS);
		return info;

	}

	@Override
	public void initialize(HashMap map) {
		// TODO Auto-generated method stub

	}
}
