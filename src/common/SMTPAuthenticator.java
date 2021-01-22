package common;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		String userName = "izevolf";
		String userPwd = "rlgus0929!";
		return new PasswordAuthentication(userName, userPwd);
	}
}
