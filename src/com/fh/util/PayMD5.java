/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.fh.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PayMD5
{
  private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

  private static final String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

  private static String byteArrayToHexString(byte[] b)
  {
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < b.length; ++i) {
      resultSb.append(byteToHexString(b[i]));
    }
    return resultSb.toString();
  }

  private static String byteToHexString(byte b) {
    int n = b;
    if (n < 0)
      n += 256;
    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
  }

  public static String MD5Encode(String origin, String charsetname) {
    String resultString = null;
    try {
      resultString = new String(origin);
      MessageDigest md = MessageDigest.getInstance("MD5");
      if ((charsetname == null) || ("".equals(charsetname))) {
        resultString = byteArrayToHexString(md.digest(resultString.getBytes())); 
      }
      resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
    } catch (Exception localException) {
    }
    label62: return resultString.toUpperCase();
  }

  public static String GetMD5nonce_str() {
    Random rnd = new Random();
    int num = rnd.nextInt(899999999) + 1000000;
    String strObj = String.valueOf(num);
    String resultString = null;
    try {
      resultString = new String(strObj);
      MessageDigest md = MessageDigest.getInstance("MD5");

      resultString = byteToString(md.digest(strObj.getBytes()));
    } catch (NoSuchAlgorithmException ex) {
      ex.printStackTrace();
    }
    return resultString;
  }

  public static String nonceStrNumber() {
    Random rnd = new Random();
    int num = rnd.nextInt(899999999) + 100000000;
    return String.valueOf(num);
  }

  private static String byteToArrayString(byte bByte)
  {
    int iRet = bByte;

    if (iRet < 0) {
      iRet += 256;
    }
    int iD1 = iRet / 16;
    int iD2 = iRet % 16;
    return strDigits[iD1] + strDigits[iD2]; }

  // ERROR //

  private static String byteToString(byte[] bByte) { StringBuffer sBuffer = new StringBuffer();
    for (int i = 0; i < bByte.length; ++i) {
      sBuffer.append(byteToArrayString(bByte[i]));
    }
    return sBuffer.toString();
  }

  public static String GetMD5Code(String strObj)
  {
    String resultString = null;
    try {
      resultString = new String(strObj);
      MessageDigest md = MessageDigest.getInstance("MD5");

      resultString = byteToString(md.digest(strObj.getBytes()));
    } catch (NoSuchAlgorithmException ex) {
      ex.printStackTrace();
    }
    return resultString;
  }

  public static final String getMessageDigest(byte[] buffer)
  {
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    try {
      MessageDigest mdTemp = MessageDigest.getInstance("sha-1");
      mdTemp.update(buffer);
      byte[] md = mdTemp.digest();
      int j = md.length;
      char[] str = new char[j * 2];
      int k = 0;
      for (int i = 0; i < j; ++i) {
        byte byte0 = md[i];
        str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
        str[(k++)] = hexDigits[(byte0 & 0xF)];
      }
      return new String(str); } catch (Exception e) {
    }
    return null;
  }

  public static final String Sha1(String s)
  {
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    try {
      byte[] btInput = s.getBytes();

      MessageDigest mdInst = MessageDigest.getInstance("sha-1");

      mdInst.update(btInput);

      byte[] md = mdInst.digest();

      int j = md.length;
      char[] str = new char[j * 2];
      int k = 0;
      for (int i = 0; i < j; ++i) {
        byte byte0 = md[i];
        str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
        str[(k++)] = hexDigits[(byte0 & 0xF)];
      }
      return new String(str);
    } catch (Exception e) {
      e.printStackTrace(); }
    return null;
  }
}