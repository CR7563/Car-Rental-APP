package com.pony.util;

import java.security.Key;

import javax.crypto.Cipher;

	public class SecurityUtil {
	    private static String strDefaultKey = "test_zyt";
	    private Cipher encryptCipher = null;
	    private Cipher decryptCipher = null;

	    /**
	     * ʹ��Ĭ�ϵ�key
	     */
	    public SecurityUtil() throws Exception {
	        this(strDefaultKey);
	    }

	    /**
	     * ʹ��Ĭ�ϼ����㷨
	     */
	    public byte[] encrypt(byte[] arrB) throws Exception {
	        return encryptCipher.doFinal(arrB);// �������ֲ������ܻ�������ݣ����߽���һ���ಿ�ֲ��������ݽ������ܻ���ܣ�����ȡ���ڴ� Cipher �ĳ�ʼ����ʽ����
	    }

	    /**
	     * ʹ���Զ�����ܼ����㷨
	     */
	    public String encrypt(String strIn) throws Exception {
	        return byteArr2HexStr(encrypt(strIn.getBytes()));
	    }

	    /**
	     * ʹ��Ĭ�Ͻ����㷨����encrypt(byte[] arrB)��Ӧ
	     */
	    public byte[] decrypt(byte[] arrB) throws Exception {
	        return decryptCipher.doFinal(arrB);
	    }

	    /**
	     * ʹ���Զ�����ܽ����㷨����encrypt(String strIn)��Ӧ
	     */
	    public String decrypt(String strIn) throws Exception {
	        return new String(decrypt(hexStr2ByteArr(strIn)));
	    }

	    /**
	     * ʹ���Զ���key
	     */
	    public SecurityUtil(String strKey) {
	        Key key;
	        try {
	            key = getKey(strKey.getBytes());
	            encryptCipher = Cipher.getInstance("DES");
	            encryptCipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ������ģʽ
	            decryptCipher = Cipher.getInstance("DES");
	            decryptCipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ������ģʽ
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * ������Կ
	     */
	    private Key getKey(byte[] arrBTmp) throws Exception {
	        // ����һ���յ�8λ�ֽ����飨Ĭ��ֵΪ0��
	        byte[] arrB = new byte[8];
	        // ��ԭʼ�ֽ�����ת��Ϊ8λ
	        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
	            arrB[i] = arrBTmp[i];
	        }
	        // ������Կ
	        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
	        return key;
	    }

	    public static String byteArr2HexStr(byte[] arrB) throws Exception {
	        int iLen = arrB.length;
	        // ÿ��byte�������ַ����ܱ�ʾ�������ַ����ĳ��������鳤�ȵ�����
	        StringBuffer sb = new StringBuffer(iLen * 2);
	        for (int i = 0; i < iLen; i++) {
	            int intTmp = arrB[i];
	            // �Ѹ���ת��Ϊ����
	            while (intTmp < 0) {
	                intTmp = intTmp + 256;
	            }
	            // С��0F������Ҫ��ǰ�油0
	            if (intTmp < 16) {
	                sb.append("0");
	            }
	            sb.append(Integer.toString(intTmp, 16));
	        }
	        return sb.toString();
	    }

	    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
	        byte[] arrB = strIn.getBytes();
	        int iLen = arrB.length;
	        // �����ַ���ʾһ���ֽڣ������ֽ����鳤�����ַ������ȳ���2
	        byte[] arrOut = new byte[iLen / 2];
	        for (int i = 0; i < iLen; i = i + 2) {
	            String strTmp = new String(arrB, i, 2);
	            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
	        }
	        return arrOut;
	    }

	  
	}