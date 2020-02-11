package dev.mvc.test;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class MyAuthentication extends Authenticator {
  PasswordAuthentication pa;

  public MyAuthentication(){
    pa = new PasswordAuthentication("test@nulunggi.pe.kr", "test2016");
  }
 
  public PasswordAuthentication getPasswordAuthentication() {
    return pa;
  }
  
  public void sendMail(String subject, String content, String from, String to) {
    
    content = content.replace("\n", "<BR>"); // ����Ű�� �ٹٲ� <BR>�� ���� 
     
    // mw-002.cafe24.com, Cafe24
    String host = "mw-002.cafe24.com";        // smtp mail server(����������)     
     
    Properties props = new Properties();  // SMTP �������� ���, port 25
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.auth","true");
     
    Authenticator auth = new MyAuthentication();
    Session sess = Session.getInstance(props, auth);   // ���� ���� �˻�
     
    try {
      Message msg = new MimeMessage(sess);   // ���� ���� ��ü ����
      msg.setFrom(new InternetAddress(from));   // ������ ��� ����
            
      // �Ѹ��Ը� ����
      InternetAddress[] address = {new InternetAddress(to)}; // �޴� ��� ����
      
      /*
      // ���� ���� ����
      to=mail1@gmail.com,mail2@gmail.com,mail3@gmail.com,mail4@gmail.com,mail5@gmail.com,
      String[] addrs = to.split(",");
      InternetAddress[] address = new InternetAddress[30];
      
      for(int i=0; i<addrs.length; i++){
        address[i] = new InternetAddress(addrs[i]);
      }
      */
      
      msg.setRecipients(Message.RecipientType.TO, address); // ������ �ּ� ����
            
      msg.setSubject(subject);                  // ���� ���� 
      msg.setSentDate(new Date());          // ���� ��¥ ����
            
      // msg.setText(msgText); // ���� �������� ���ڸ� ���� ���
     
      // ������ �������� HTML �������� ���� ���
      msg.setContent(content, "text/html;charset=utf-8");
            
      Transport.send(msg);  // ���� ����
      System.out.println(to + "���� ���� �߼�~~~~~~~");
      /**
      // ���� ����
      for(int i=0; i<addrs.length; i++){
        out.println(addrs[i] + " �Կ��� ������ �����߽��ϴ�.");
      }
      */
      
    } catch (MessagingException mex) {
      System.out.println(to + "���� ���� �߼� ����!!!!!!!!!");
      /*out.println(mex.getMessage()+"<br><br>");
      out.println(to + "�Կ��� ���� �߼��� ���� �߽��ϴ�.");*/
    }
  }
}