package com.loex.email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SendEmail {

    /**
     * 发送测试报告链接到邮箱
     *
     * @param
     */


    public void testSend(String emailname) throws Exception {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();

        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        // 发件人的账号
        props.put("mail.user", "729560832@qq.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "exbjjmbjshezbdga");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人，此发件人就是上面输入的账号
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人
        InternetAddress to = new InternetAddress(emailname);
//        InternetAddress to = new InternetAddress("3407135351@qq.com");
        message.setRecipient(MimeMessage.RecipientType.TO, to);

        // 设置邮件标题
        Date t = new Date();
        //生成当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = df.format(t);
        message.setSubject("WebApi自动化测试报告邮件_"+data);

        // 设置邮件的内容体
        String repotr_url = "<a href="+"http://192.168.0.204:80/test-output/index.html"+">请点击链接查看测试报告</a>";
        message.setContent(repotr_url,
                "text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);

    }

//    // 初始化参数
//    private static Properties prop;
//    // 发件人
//    private static InternetAddress sendMan = null;
//    static {
//        prop = new Properties();
//        prop.put("mail.transport.protocol", "smtp"); // 指定协议
//        prop.put("mail.smtp.host", "smtp.qq.com"); // 主机 stmp.qq.com
//        // prop.put("mail.smtp.port", 25); // 端口
//        prop.put("mail.smtp.auth", "true"); // 用户密码认证
//        prop.put("mail.debug", "true"); // 调试模式
//        try {
//            sendMan = new InternetAddress("729560832@qq.com");
//        } catch (AddressException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Test
//    public void testSend() throws Exception {
//
//        // 1. 创建邮件会话
//        Session session = Session.getDefaultInstance(prop);
//        // 2. 创建邮件对象
//        MimeMessage message = new MimeMessage(session);
//        // 3. 设置参数：标题、发件人、收件人、发送时间、内容
//        message.setSubject("带图片邮件");
//        message.setSender(sendMan);
//        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("zhaopengtian2015@163.com"));
//        message.setSentDate(new Date());
//
//        /*
//         * 带附件(图片)邮件开发
//         */
//        // 构建一个总的邮件块
//        MimeMultipart mixed = new MimeMultipart("mixed");
//        // ---> 总邮件快，设置到邮件对象中
//        message.setContent(mixed);
//        // 左侧： （文本+图片资源）
//        MimeBodyPart left = new MimeBodyPart();
//        // 右侧： 附件
//        MimeBodyPart right = new MimeBodyPart();
//        // 设置到总邮件块
//        mixed.addBodyPart(left);
//        mixed.addBodyPart(right);
//
//        /****** 附件 ********/
//        String attr_path = "E:\\ideaWorkPlace\\loex\\test-output\\index.html";
//        DataSource attr_ds = new FileDataSource(new File(attr_path));
//        DataHandler attr_handler = new DataHandler(attr_ds);
//        right.setDataHandler(attr_handler);
//        Date t = new Date();
//        //生成当前时间
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String data = df.format(t);
//        right.setFileName("WebApi自动化测试报告"+data+".html");
//
//        /*************** 设置邮件内容: 多功能用户邮件 (related) *******************/
//        // 4.1 构建一个多功能邮件块
//        MimeMultipart related = new MimeMultipart("related");
//        // ----> 设置到总邮件快的左侧中
//        left.setContent(related);
//
//        // 4.2 构建多功能邮件块内容 = 左侧文本 + 右侧图片资源
//        MimeBodyPart content = new MimeBodyPart();
//        MimeBodyPart resource = new MimeBodyPart();
//
//        // 设置具体内容: a.资源(图片)
//        String filePath = SendEmail.class.getClassLoader().getResource("1.jpg").getPath();
//        DataSource ds = new FileDataSource(new File(filePath));
//        DataHandler handler = new DataHandler(ds);
//        resource.setDataHandler(handler);
//        resource.setContentID("1.jpg"); // 设置资源名称，给外键引用
//
//        // 设置具体内容: b.文本
//        content.setContent(" WebApi自动化测试报告以附件形式发送到您邮箱，请查看！", "text/html;charset=UTF-8");
//
//        related.addBodyPart(content);
////        related.addBodyPart(resource);
//
//        // 5. 发送
//        Transport trans = session.getTransport();
//        trans.connect("729560832@qq.com", "exbjjmbjshezbdga");
//        trans.sendMessage(message, message.getAllRecipients());
//        trans.close();
//
//
//    }
}
