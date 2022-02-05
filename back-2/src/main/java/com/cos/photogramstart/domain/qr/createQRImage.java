package com.cos.photogramstart.domain.qr;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class createQRImage {
	public createQRImage(String text,String qrpic, String filepath) {
		try {
			
		    //String codeurl = new String("http://stoneis.pe.kr".getBytes("UTF-8"), "ISO-8859-1");
			//text = "http://m.site.naver.com/0UHMD";
		    String qrcontents = new String(text.getBytes("UTF-8"), "ISO-8859-1");

		    // 바코드 색상 값
		    int qrcodeColor =   0xFF2e4e96;
		    // 배경 색상 값
		    int backgroundColor = 0xFFFFFFFF;

		    QRCodeWriter qrCodeWriter = new QRCodeWriter();
		    // 300x300
		    BitMatrix bitMatrix = qrCodeWriter.encode(qrcontents, BarcodeFormat.QR_CODE, 300, 300);

		    MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor,backgroundColor);
		    BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);
		    // ImageIO를 사용한 바코드 파일쓰기
		    ImageIO.write(bufferedImage, "png", new File(filepath+qrpic+".png"));
		    
			
		}catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		
	}
	
	

}
