package common;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendMail {

	public void sendEmail(String menu, String to, String content) throws Exception {
		// menu: 아이디 찾기/비밀번호 찾기/후원 내역 전송          to : 받는 사람          content : 아이디의 일부/임시 비밀번호/이름
		
		Properties prop = new Properties();

		// 프로토콜 설정
		prop.put("mail.transport.protocol", "smtp");
		// gmail SMTP 서비스 주소(호스트)
		prop.put("mail.smtp.user", "izevolf.gmail.com");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		// gmail SMTP 서비스 포트 설정
		prop.put("mail.smtp.port", "465");
		// 로그인할 때 TLS를 사용할 것인지 설정 ==> gmail에서는 TLS가 필수가 아님
		prop.put("mail.smtp.starttls.enable", "true");
		// gmail 인증용 SSL 설정 ==> gmail에서 인증할 때 사용하므로 필수
		prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// SMTP 인증 설정
		prop.put("mail.smtp.auth", "true");

		prop.put("mail.smtp.debug", "true");
		prop.put("mail.smtp.socketFactory.fallback", "false");

		// Authenticator : 네트워크 접속에 필요한 인증을 취득하기 위한 객체
		// 암호 인증을 사용하기 위해 호출
		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getDefaultInstance(prop, auth);
		// 메일을 전송할 때의 상황 출력
		mailSession.setDebug(true);

		Message msg = new MimeMessage(mailSession);

		// 보내는 사람 설정
		msg.setFrom(new InternetAddress("izevolf@gmail.com"));
		// 받는 사람 설정
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

		// 제목 설정
		msg.setSubject("안녕하세요. ♡함께하묘 행복하개♡ 입니다.");
		// 내용 설정
		String text ="";
		if(menu == "id") {
			// 아이디 찾기
			text = "<div style=\"border: 3px solid #2980b9; width: 850px; text-align: center; padding-top: 15px;\">\r\n" + 
					"		<div style=\"padding: 15px; line-height:600%;\">\r\n" + 
					"			<img src=\"https://docs.google.com/drawings/u/0/d/sytGzgCQNFh-6-SoDUlhdfQ/image?w=565&h=99&rev=3&ac=1&invite&parent=15LgYQdQFbkv1-vWBxBquFV0jA7Ire5vxVXXHF_-c56A\">\r\n" + 
					"			<hr style=\"border:5px dashed lightgray;\">\r\n" + 
					"			<b style=\"font-size: 21px;\"><< 아이디 안내 메일 >></b>\r\n" + 
					"			<div style=\"font-size: 19px; font-weight: 800;\">\r\n" + 
					"				<span>회원님의 아이디는 </span>\r\n" + 
					"				<span style=\"color: #008eeb;\">" + content + "***</span>\r\n" + 
					"				<span>입니다.</span>\r\n" + 
					"			</div>\r\n" + 
					"			<hr style=\"border:1.5px solid lightgray; width:60%;\">\r\n" + 
					"			<div style=\"font-weight: 550; line-height:200%;\">\r\n" + 
					"				저희 ♡함께하묘 행복하개♡를 이용해주셔서 감사합니다.<br>\r\n" + 
					"				더욱 편리한 서비스를 제공하기 위해 항상 최선을 다하겠습니다.\r\n" + 
					"			</div>\r\n" + 
					"		</div>\r\n" + 
					"		<br><br>\r\n" + 
					"		<div style=\"background: #eee; padding: 20px; line-height:250%;\">\r\n" + 
					"			함께하묘 행복하개&nbsp;&nbsp;|&nbsp;&nbsp;서울시 강남구 역삼동 어쩌구 123 (우) 12345&nbsp;&nbsp;|&nbsp;&nbsp;대표 : 김대표\r\n" + 
					"			<br>\r\n" + 
					"			TEL : 02-123-4567&nbsp;&nbsp;|&nbsp;&nbsp;FAX : 02-345-6789&nbsp;&nbsp;|&nbsp;&nbsp;EMAIL : tchd777@naver.com&nbsp;&nbsp;|&nbsp;&nbsp;사업자등록번호 : 123-45-67890\r\n" + 
					"		</div>\r\n" + 
					"	</div>\n";
		} else if(menu == "pwd") {
			// 비밀번호 찾기
			text = "<div style=\"border: 3px solid #2980b9; width: 850px; text-align: center; padding-top: 15px;\">\r\n" + 
					"		<div style=\"padding: 15px; line-height:600%;\">\r\n" + 
					"			<img src=\"https://docs.google.com/drawings/u/0/d/sytGzgCQNFh-6-SoDUlhdfQ/image?w=565&h=99&rev=3&ac=1&invite&parent=15LgYQdQFbkv1-vWBxBquFV0jA7Ire5vxVXXHF_-c56A\">\r\n" + 
					"			<hr style=\"border:5px dashed lightgray;\">\r\n" + 
					"			<b style=\"font-size: 21px;\"><< 비밀번호 안내 메일 >></b>\r\n" + 
					"			<div style=\"font-size: 19px; font-weight: 800;\">\r\n" + 
					"				<span>임시 비밀번호는 </span>\r\n" + 
					"				<span style=\"color: #008eeb;\">" + content + "</span>\r\n" + 
					"				<span>입니다.</span>\r\n" + 
					"			</div>\r\n" + 
					"			<hr style=\"border:1.5px solid lightgray; width:60%;\">\r\n" + 
					"			<div style=\"font-weight: 550; line-height:200%;\">\r\n" + 
					"				저희 ♡함께하묘 행복하개♡를 이용해주셔서 감사합니다.<br>\r\n" + 
					"				더욱 편리한 서비스를 제공하기 위해 항상 최선을 다하겠습니다.\r\n" + 
					"			</div>\r\n" + 
					"		</div>\r\n" + 
					"		<br><br>\r\n" + 
					"		<div style=\"background: #eee; padding: 20px; line-height:250%;\">\r\n" + 
					"			함께하묘 행복하개&nbsp;&nbsp;|&nbsp;&nbsp;서울시 강남구 역삼동 어쩌구 123 (우) 12345&nbsp;&nbsp;|&nbsp;&nbsp;대표 : 김대표\r\n" + 
					"			<br>\r\n" + 
					"			TEL : 02-123-4567&nbsp;&nbsp;|&nbsp;&nbsp;FAX : 02-345-6789&nbsp;&nbsp;|&nbsp;&nbsp;EMAIL : tchd777@naver.com&nbsp;&nbsp;|&nbsp;&nbsp;사업자등록번호 : 123-45-67890\r\n" + 
					"		</div>\r\n" + 
					"	</div>\n";
		} else if(menu == "support") {
			// 후원 내역 전송
			// content = name + "," + sup_no
			String[] strArr = content.split(",");
			String name = strArr[0];
			String sup_no = strArr[1];
			
			text = "<div style=\"border: 3px solid #2980b9; width: 850px; text-align: center; padding-top: 15px;\">\r\n" + 
					"		<div style=\"padding: 15px; line-height:400%;\">\r\n" + 
					"			<img src=\"https://docs.google.com/drawings/u/0/d/sytGzgCQNFh-6-SoDUlhdfQ/image?w=565&h=99&rev=3&ac=1&invite&parent=15LgYQdQFbkv1-vWBxBquFV0jA7Ire5vxVXXHF_-c56A\" style=\"width: 565px; height: 100px;\">\r\n" + 
					"			<hr style=\"border:5px dashed lightgray;\">\r\n" + 
					"			<b style=\"font-size: 21px;\"><< 후원 신청 내역 >></b>\r\n" + 
					"			<div style=\"font-size: 17px;\">\r\n" + 
					"				<div style=\"font-size: 19px;\">\r\n" + 
					"					<span style=\"color: #008eeb; font-weight: 800;\">♥ " + name + " ♥</span>\r\n" + 
					"					<span style=\"font-weight: 800;\"> 후원자님</span><br>\r\n" + 
					"					<span style=\"font-weight: 800;\">후원 번호 : </span>\r\n" + 
					"					<span style=\"color: #008eeb; font-weight: 800;\">" + sup_no + "</span>\r\n" + 
					"				</div>\r\n" + 
					"				<span>후원자님 덕분에 더 많은 보호 동물들이 행복한 세상을 만날 수 있게 되었습니다.<br>따스한 도움의 손길 감사합니다.</span>\r\n" + 
					"			</div>\r\n" + 
					"			<hr style=\"border:1.5px solid lightgray; width:80%;\">\r\n" + 
					"			<div style=\"font-weight: 550; line-height:200%;\">\r\n" + 
					"				저희 ♡함께하묘 행복하개♡를 이용해주셔서 감사합니다.<br>\r\n" + 
					"				더욱 편리한 서비스를 제공하기 위해 항상 최선을 다하겠습니다.\r\n" + 
					"			</div>\r\n" + 
					"		</div>\r\n" + 
					"		<br><br>\r\n" + 
					"		<div style=\"background: #eee; padding: 20px; line-height:250%;\">\r\n" + 
					"			함께하묘 행복하개&nbsp;&nbsp;|&nbsp;&nbsp;서울시 강남구 역삼동 어쩌구 123 (우) 12345&nbsp;&nbsp;|&nbsp;&nbsp;대표 : 김대표\r\n" + 
					"			<br>\r\n" + 
					"			TEL : 02-123-4567&nbsp;&nbsp;|&nbsp;&nbsp;FAX : 02-345-6789&nbsp;&nbsp;|&nbsp;&nbsp;EMAIL : tchd777@naver.com&nbsp;&nbsp;|&nbsp;&nbsp;사업자등록번호 : 123-45-67890\r\n" + 
					"		</div>\r\n" + 
					"	</div>";
		}
		msg.setContent(text, "text/html; charset=UTF-8;");
		// 보내는 날짜 설정
		msg.setSentDate(new Date());

		Transport.send(msg);
	}
}
