package com.fh.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;


public class OSSUtils {
	
	private static String ACCESS_ID = "";
	private static String ACCESS_KEY = "";
	private static String OSS_ENDPOINT = "";
	private static String BUCKET_NAME = "";

	static {
		Properties property = new Properties();
		try {
			property.load(OSSUtils.class.getClassLoader().getResourceAsStream("oss.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ACCESS_ID = property.getProperty("ACCESS_ID");
		ACCESS_KEY = property.getProperty("ACCESS_KEY");
		OSS_ENDPOINT = property.getProperty("OSS_ENDPOINT");
		BUCKET_NAME = property.getProperty("BUCKET_NAME");
	}

	// 使用默认的OSS服务器地址创建OSSClient对象
	static OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
	
	/**
	 * 上传文件（OSS）
	 * @param filePath
	 * @param is
	 */
	public static Boolean uploadFileOfOSS(String filePath, MultipartFile file) {
		try {
			ObjectMetadata objectMeta = new ObjectMetadata();
			objectMeta.setContentLength(file.getSize());
			objectMeta.setContentType("image/jpeg");
			InputStream input = file.getInputStream();
			client.putObject(BUCKET_NAME, filePath, input, objectMeta);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 删除文件（OSS）
	 * @param filePath
	 */
	public static void deleteBucket(String filePath) {
		client.deleteObject(BUCKET_NAME, filePath);
	}
	public static void main(String[] args) {
		deleteBucket("upload/goodstupian/20170922/70e9bfd78588436aaf743bcdeddba951.jpg");
	}
	/**
	 * 复制文件
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyObject(String oldPath, String newPath) {
	    client.copyObject(BUCKET_NAME, oldPath, BUCKET_NAME, newPath);
	}
}