package com.pony.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {

	private String serverIp = "113.140.71.254";
	private int ftp_port = 21;
	private String ftp_user = "ftpuser";
	private String ftp_password = "ASDFqwer1234";

	private FTPUtil() {
		// TODO Auto-generated constructor stub
	}

	private static FTPUtil ftp;

	public static FTPUtil getInstance() {
		if (null == ftp)
			ftp = new FTPUtil();
		return ftp;
	}

	// �����ϴ�Ŀ¼
	// ftpClient.changeWorkingDirectory("/admin/pic" );
	// ftpClient.setBufferSize(1024);
	// ftpClient.setControlEncoding("GBK" );
	// FTPUtil.getInstance().ftpUpload(DTRMApplication.IMAGEPATH, "//");

	public boolean ftpUpload(String localPath, String pathname) throws Exception {
		boolean success = false;
		FTPClient ftp = new FTPClient();

		try {
			ftp.connect(serverIp, ftp_port);// ����FTP������
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			boolean loginResult = ftp.login(ftp_user, ftp_password);// ��¼
			int reply = ftp.getReplyCode();
			if (loginResult && FTPReply.isPositiveCompletion(reply)) // �����½�ɹ�
			{
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftp.makeDirectory(pathname);
				boolean change = ftp.changeWorkingDirectory(pathname);// �����ϴ�Ŀ¼
				if (change) {
					File file = new File(localPath);
					InputStream input = new FileInputStream(file);;
					ftp.storeFile(file.getName(), input);
					input.close();
//					File dir = new File(localPath);
//					File files[] = dir.listFiles();
//					InputStream input;
//					if (dir.isDirectory()) {
//						for (int i = 0; i < files.length; i++) {
//							try {
//								input = new FileInputStream(files[i]);
//								ftp.storeFile(imgName, input);
//							} catch (FileNotFoundException e) {
//								e.printStackTrace();
//							}
//						}
//					}
					//
					// for (File f : file.listFiles()) {
					// InputStream input = new FileInputStream(f);
					// ftp.storeFile(f.getName(), input);
					// input.close();
					// f.delete();
					// }

				}
				ftp.logout();
				success = true;
			} else {
				success = false;
			}
		} finally {
			if (ftp.isConnected()) {
				ftp.disconnect();
			}
		}
		return success;
	}

	public static final String DEFAULT_CONTROL_ENCODING = "ISO-8859-1";

	public boolean ftpUpload1(String fileNamePath, String remotePath) throws Exception {
		FTPClient ftpClient = new FTPClient();
		FileInputStream fis = null;

		try {
			ftpClient.connect(serverIp, ftp_port);
			boolean loginResult = ftpClient.login(ftp_user, ftp_password);
			int returnCode = ftpClient.getReplyCode();
			if (loginResult && FTPReply.isPositiveCompletion(returnCode)) // �����½�ɹ�
			{

				ftpClient.makeDirectory(remotePath);
				boolean change = ftpClient.changeWorkingDirectory(remotePath);// �����ϴ�Ŀ¼
				if (change) {
					ftpClient.setBufferSize(1024);
					ftpClient.setControlEncoding("UTF-8");
					ftpClient.enterRemotePassiveMode();
					ftpClient.enterLocalPassiveMode();
					// ��ȡ���еĴ�����־�����ϴ����Է��е��ļ���һ��û���ϴ��ɹ�
					File folder = new File(fileNamePath);
					String[] fileNames = folder.list();
					for (int i = 0; i < fileNames.length; i++) {
						File file = new File(fileNamePath + fileNames[i]);
						if (file.exists()) {
							fis = new FileInputStream(file);
							boolean result = ftpClient.storeFile(new String(fileNames[i].getBytes("UTF-8"), "iso-8859-1"), fis);
							if (result) // �ϴ��ɹ�,ɾ���ֻ����ص��ļ�
							{
								// file.delete();
							}
						}
					}
				}
			}
		} finally {

			ftpClient.disconnect();

		}
		return true;
	}
}
