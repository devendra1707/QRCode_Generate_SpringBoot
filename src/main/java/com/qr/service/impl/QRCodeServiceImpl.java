package com.qr.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qr.service.QRCodeService;

@Service
public class QRCodeServiceImpl implements QRCodeService {

	private Logger logger = LoggerFactory.getLogger(QRCodeServiceImpl.class);
	
	
	@Override
	public byte[] generateQRCode(String qrContent, int width, int height) {
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		}catch(WriterException e){
			logger.error(e.getMessage(),e);
		}catch(IOException e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}

}
