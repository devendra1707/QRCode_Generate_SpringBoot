package com.qr.service;

public interface QRCodeService {
	byte[] generateQRCode(String qrContent, int width, int height);
}
