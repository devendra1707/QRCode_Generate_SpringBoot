package com.qr.controller;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qr.service.QRCodeService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class QRCodeController {

	@Autowired
	private QRCodeService qrCodeService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/showQRCode")
	public String showQrCode(String qrContent, Model model) {
		model.addAttribute("qrCodeContent", "/generateQRCode?qrContent=" + qrContent);

		return "show-qr-code";

	}

	@GetMapping("/generateQRCode")
	public void generateQRCode(String qrContent, HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.setContentType("image/png");
		byte[] qrCode = qrCodeService.generateQRCode(qrContent, 500, 500);
		OutputStream outputStream = httpServletResponse.getOutputStream();
		outputStream.write(qrCode);
	}
}
