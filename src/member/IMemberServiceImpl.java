package member;

import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import VO.MemberVO;


public class IMemberServiceImpl implements IMemberService{
	private static IMemberService memberService;

	
	private IMemberDAO memberDao;
	private IMemberServiceImpl() {
		memberDao = IMemberDAOImpl.getInstance();
	}

	public static IMemberService getInstance() {
		if(memberService == null){
			memberService = new IMemberServiceImpl();
		}
		return memberService;
	}

	@Override
	public MemberVO getMemberInfo(Map<String, String> params) {
		String pw = params.get("memPw");
		String pwEncrypt = pwEncryption(pw);
		params.put("memPw", pwEncrypt);
		return memberDao.getMemberInfo(params);
	}

	@Override
	public boolean changeName(String name, String loginMemberId) {
		return memberDao.changeName(name, loginMemberId);
	}

	@Override
	public boolean changePw(String pw, String loginMemberId) {
		String pwEncrypt = pwEncryption(pw);
		return memberDao.changePw(pwEncrypt, loginMemberId);
	}

	@Override
	public boolean changeBd(String bd, String loginMemberId) {
		return memberDao.changeBd(bd, loginMemberId);
	}

	@Override
	public boolean changeMoney(int money, String loginMemberId) {
		return memberDao.changeMoney(money, loginMemberId);
	}

	@Override
	public boolean MemberRetire(String loginMemberId) {
		return memberDao.MemberRetire(loginMemberId);
	}

	@Override
	public boolean addPersonFinish(MemberVO mv) {
		String pw = mv.getMemPw();
		String pwEncrypt = pwEncryption(pw);
		mv.setMemPw(pwEncrypt);
		return memberDao.addPersonFinish(mv);
	}

	@Override
	public boolean checkId(String id) {
		return memberDao.checkId(id);
	}

	@Override
	public String[] MemberIdPrint() {
		return memberDao.MemberIdPrint();
	}

	@Override
	public Object[] adminCsPrint(String customerId) {
		return memberDao.adminCsPrint(customerId);
	}

	@Override
	public boolean adminCsPointUP(int money, String customerId) {
		return memberDao.adminCsPointUP(money, customerId);
	}

	@Override
	public void adminCsRetire(String customerId) {
		memberDao.adminCsRetire(customerId);		
	}


	
	int a= 1;
	public void createPdf(Map<String, Object> asd){
		
		String memName = (String) asd.get("mem_name");
		int payPrice = (int) asd.get("payPrice");
		int mem_mileage = (int) asd.get("mem_mileage");
		int qty = (int) asd.get("qty");
		String prodName = (String) asd.get("prodName");
		
		
		Document document = new Document(PageSize.A4);
		document.addAuthor("iu");
		document.addTitle("Test IText");
		System.out.println("summary created");
		
		
		try{
			//BaseFont objBaseFont = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", false); 
			BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/malgunbd.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font font = new Font(baseFont, 29);
			
			BaseFont baseFont1 = BaseFont.createFont("c:/windows/fonts/malgunbd.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font font1 = new Font(baseFont, 40);
			
			BaseFont baseFont2 = BaseFont.createFont("c:/windows/fonts/malgunbd.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font font2 = new Font(baseFont, 18);
			
			PdfWriter.getInstance(document,new FileOutputStream(a++ +"번째 영수증.pdf"));
			Image image = Image.getInstance("mc.jpg");
			image.scaleAbsolute(200, 280);
			image.setAlignment(image.MIDDLE);
			
			document.open();
			document.add(new Paragraph("★★★★★★★★★★★★★",font1));
			document.add(new Paragraph("            28년  전통   ",font1));
			document.add(new Paragraph("             민창이의   ",font1));
			document.add(new Paragraph("          Coffee House  ",font1));
			document.add(new Paragraph("★★★★★★★★★★★★★",font1));
			
			document.add(image);
			document.add(new Paragraph("                                                             믿을 수 있어요!",font2));
			document.add(new Paragraph("=========================================================================="));
			document.add(new Paragraph("                 영     수     증",font));
			document.add(new Paragraph("=========================================================================="));
			document.add(new Paragraph("회원이름     :    "+memName,font));
			document.add(new Paragraph("상품명        :    "+prodName,font));
			document.add(new Paragraph("수량           :    "+qty,font));
			document.add(new Paragraph("결제금액     :    "+payPrice,font));
			document.add(new Paragraph("마일리지     :    "+mem_mileage,font));
		}catch(Exception e){
			System.out.println(e);
		}
		
		document.close();
		
			
		
	}
	
	
	/**
	 * 비밀번호 암호화
	 * @author 김범휘
	 * @param 평문상태의 비밀번호
	 * @return 암호화된 비밀번호
	 */
	@Override
	public String pwEncryption(String pw) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");//메세지다이제스트 객체 생성
            md.update(pw.getBytes());
            byte byteData[] = md.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
	}

	@Override
	public void sendMail(Map<String, Object> asd , MemberVO mv) {
		String host = "smtp.naver.com"; //네이버 호스트 설정
//		String host = "gmail-smtp-in.google.com."//gmail 호스트 설정
		
		final String user = "youbi89@naver.com"; //보내는 메일의 id
		final String password = "kbh711482!"; //보내는 메일의 비밀번호

		String to = mv.getMemEmail();//수신측의 메일 주소

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host); //네이버일 경우
		props.put("mail.smtp.auth", "true");
											//구글일 경우
//		props.put("mail.smtp.auth", "true");       gmail
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");
		

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// 메일 제목
			message.setSubject("카페 영수증");
			
			
			String memName = (String) asd.get("mem_name");
			int payPrice = (int) asd.get("payPrice");
			int mem_mileage = (int) asd.get("mem_mileage");
			int qty = (int) asd.get("qty");
			String prodName = (String) asd.get("prodName");
			// 메일 내용
	
			
			
			message.setText("★★★★★★★★★★★★★\n"
					+ "            28년  전통   \n"
					+ "             민창이의   \n"
					+ "          Coffee House  \n"
					+ "★★★★★★★★★★★★★\n"
					+ "===============================\n"
					+ "			영     수     증\n"
					+ "===============================\n"
					+ "회원이름     :    "+memName
					+"\n상품명        :    "+prodName
					+"\n수량           :    "+qty
					+"\n결제금액     :    "+payPrice
					+"\n마일리지     :    "+mem_mileage);


	

			
			
			
			

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}