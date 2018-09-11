package com.fh.util.weixinUtil;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
public class ClientCustomSSL {

	public static CloseableHttpClient doRefund(String filePath,String PARTNER) throws Exception {
		//指定读取证书格式为PKCS12
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		//读取本机存放的PKCS12证书文件
		FileInputStream instream = new FileInputStream(new File(filePath));
		try {
		//指定PKCS12的密码(商户ID)
		keyStore.load(instream, PARTNER.toCharArray());
		} finally {
		instream.close();
		}
		SSLContext sslcontext = SSLContexts.custom()
		.loadKeyMaterial(keyStore, PARTNER.toCharArray()).build();
		//指定TLS版本
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
		sslcontext,new String[] { "TLSv1" },null,
		SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		//设置httpclient的SSLSocketFactory
		CloseableHttpClient httpclient = HttpClients.custom()
		.setSSLSocketFactory(sslsf)
		.build();
		return httpclient;
	}

}
