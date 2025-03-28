package com.jake.custom.security.customencryption;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ibm.wsspi.security.crypto.EncryptedInfo;
import com.ibm.wsspi.security.crypto.PasswordDecryptException;
import com.ibm.wsspi.security.crypto.PasswordEncryptException;

class WasCustomerPasswordEncryptionTest {
	private static final String TEST_VALUE = "test";
	public WasCustomerPasswordEncryption enc = new WasCustomerPasswordEncryption();

	@Test
	void testDecrypt() throws PasswordDecryptException {
		EncryptedInfo info = new EncryptedInfo((WasCustomerPasswordEncryption.ENC_STR + TEST_VALUE).getBytes(),
				WasCustomerPasswordEncryption.ALIAS);
		assertEquals(new String(enc.decrypt(info)), TEST_VALUE);
		assertEquals(info.getKeyAlias(), WasCustomerPasswordEncryption.ALIAS);
	}

	@Test
	void testEncrypt() throws PasswordEncryptException {

		EncryptedInfo encryptedValue = enc.encrypt(TEST_VALUE.getBytes());
		assertEquals(WasCustomerPasswordEncryption.ENC_STR + TEST_VALUE,
				new String(encryptedValue.getEncryptedBytes()));
		assertEquals(encryptedValue.getKeyAlias(), WasCustomerPasswordEncryption.ALIAS);

	}

}
