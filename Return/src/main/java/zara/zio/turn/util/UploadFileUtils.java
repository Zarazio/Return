package zara.zio.turn.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	private static Method resizeMode;
	
	// ���ε� ����� ���� 
	public static String uploadFile(String uploadPath, 
			String originalName, byte[] fileData) throws Exception {
		// uploadPath : ������ ������
		// originalName : ���������� �̸�
		// fileData : ���� ������ byte[] 
		
		// UUID �߱� 
		UUID uid = UUID.randomUUID();
		
		// ������ ���ϸ� = UUID + �����̸�
		String saveName = uid.toString() + "_" + originalName;
		
		// ���� ���(������ ���ε���+��¥�����), ���ϸ��� �޾� ���� ��ü ����
		File target = new File(uploadPath, saveName);
		
		 // �ӽ� ���丮�� ���ε�� ������ ������ ���丮�� ����
		FileCopyUtils.copy(fileData, target);
		
		// ������� �����ϱ� ���� ������ Ȯ���� �˻�
        // ���ϸ��� aaa.bbb.ccc.jpg�� ��� ������ ��ħǥ�� ã�� ����
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		String uploadedFileName = null;
		
		 // �̹��� ������ ����� ���
		if(MediaUtils.getMediaType(formatName) != null) {
			// ����ϻ��� 
			uploadedFileName = makeThumbnail(uploadPath, saveName);
		} else {
			// ������ �����ܻ��� 
			uploadedFileName = makeIcon(uploadPath, saveName);
		}
		
		return uploadedFileName;
	}
	
	// ����� ���� 
	private static String makeThumbnail(String uploadPath, String fileName) throws Exception{
		// �̹����� �о���̱� ���� ����
		BufferedImage sourceImg = 
				ImageIO.read(new File(uploadPath, fileName));
		// 100 �ȼ����� ����� ����
		BufferedImage destImg = 
				Scalr.resize(sourceImg, 200, null, null);
//				Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		// ������� �̸����� "s_"�� ����
		String thumbnailName = 
				uploadPath+ File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String formatName = 
				fileName.substring(fileName.lastIndexOf(".")+1);
		// ����� ����
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		// ������� �̸��� ���� 
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// ������ ���� 
	private static String makeIcon(String uploadPath, String fileName) throws Exception {
		// �������� �̸�
		String iconName = uploadPath + File.separator + fileName;
		
		// ������ �̸��� ����
        // File.separatorChar : ���丮 ������
        // ������ \ , ���н�(������) /  
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
}


/*
 * 
package zara.zio.turn.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	private static final Logger logger =
			LoggerFactory.getLogger(UploadFileUtils.class);
	
	// ���ε� ����� ���� 
	public static String uploadFile(String uploadPath, 
			String originalName, byte[] fileData) throws Exception {
		// uploadPath : ������ ������
		// originalName : ���������� �̸�
		// fileData : ���� ������ byte[] 
		
		// UUID �߱� 
		UUID uid = UUID.randomUUID();
		
		// ������ ���ϸ� = UUID + �����̸�
		String saveName = uid.toString() + "_" + originalName;
		
		// ���ε��� ���丮(��¥�� ����) ���� 
		String savePath = calcPath(uploadPath);
		
		// ���� ���(������ ���ε���+��¥�����), ���ϸ��� �޾� ���� ��ü ����
		File target = new File(uploadPath + savePath, saveName);
		
		 // �ӽ� ���丮�� ���ε�� ������ ������ ���丮�� ����
		FileCopyUtils.copy(fileData, target);
		
		// ������� �����ϱ� ���� ������ Ȯ���� �˻�
        // ���ϸ��� aaa.bbb.ccc.jpg�� ��� ������ ��ħǥ�� ã�� ����
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		String uploadedFileName = null;
		
		 // �̹��� ������ ����� ���
		if(MediaUtils.getMediaType(formatName) != null) {
			// ����ϻ��� 
			uploadedFileName = makeThumbnail(uploadPath, savePath, saveName);
		} else {
			// ������ �����ܻ��� 
			uploadedFileName = makeIcon(uploadPath, savePath, saveName);
		}
		
		return uploadedFileName;
	}
	
	
	// ��¥ ����ó�� 
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance(); // Ķ���� �Լ�
		
		// File.separator : ���丮 ������(\\)
		
		String yearPath = File.separator+cal.get(Calendar.YEAR); // ����, ex) \\2017 
		String monthPath = yearPath + File.separator +
				new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1); // ��, ex) \\2017\\03
		String datePath = monthPath + File.separator + 
				new DecimalFormat("00").format(cal.get(Calendar.DATE)); // ��¥, ex) \\2017\\03\\01
		 
		// ���丮 ���� ���� �޼��� ȣ��
		makeDir(uploadPath, yearPath, monthPath, datePath);
		logger.info(datePath);
		return datePath;
	}
	
	// ���丮 ���� ���� 
	private static void makeDir(String uploadPath, String ...paths) {
		// ���丮�� �����Ҷ�. 
		if(new File(paths[paths.length-1]).exists()) {
			return;
		}
		
		// ���丮�� �������� ������..
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			// ��������� 
			if(! dirPath.exists()) {
				dirPath.mkdir();
			}
		}
		
	}
	
	// ����� ���� 
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception{
		// �̹����� �о���̱� ���� ����
		BufferedImage sourceImg = 
				ImageIO.read(new File(uploadPath + path, fileName));
		// 100 �ȼ����� ����� ����
		BufferedImage destImg = 
				Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		// ������� �̸����� "s_"�� ����
		String thumbnailName = 
				uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String formatName = 
				fileName.substring(fileName.lastIndexOf(".")+1);
		// ����� ����
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		// ������� �̸��� ���� 
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// ������ ���� 
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		// �������� �̸�
		String iconName = uploadPath + path + File.separator + fileName;
		
		// ������ �̸��� ����
        // File.separatorChar : ���丮 ������
        // ������ \ , ���н�(������) /  
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	
}
 * 
 */
