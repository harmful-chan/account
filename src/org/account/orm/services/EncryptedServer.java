package org.account.orm.services;

import org.apache.commons.codec.binary.Base64;

public class EncryptedServer {
	public String encode(String password, String self) {
		try {
			return new String(Base64.encodeBase64((password+self).getBytes(), true));	
		}catch(Exception e) {
			return null;
		}
		
	}

	public String decode(String deeppwd) {
		try {
			return new String(Base64.decodeBase64(deeppwd));
		}catch(Exception e) {
			return null;
		}
	}
}
