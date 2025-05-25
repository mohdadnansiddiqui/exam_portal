package com.examportal.controller;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@RestController
public class QRController {

	@GetMapping(value = "/generateQR", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> generateQRAndRedirect() {
		byte[] qrCode = generateQRCode("www.google.com");

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCode);
	}

	private byte[] generateQRCode(String url) {
		try {

			Map<EncodeHintType, Object> hintMap = new HashMap<>();
			hintMap.put(EncodeHintType.MARGIN, 1);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 200, 200, hintMap);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);
			return outputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
