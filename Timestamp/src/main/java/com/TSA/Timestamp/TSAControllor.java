package com.TSA.Timestamp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.StampingProperties;
import com.itextpdf.signatures.ITSAClient;
import com.itextpdf.signatures.PdfSigner;
import com.itextpdf.signatures.TSAClientBouncyCastle;

@RestController
public class TSAControllor {
	


	@RequestMapping("/TSATimestamp")
	public String TSA(String[] args) {
		String pdfPath = ("C:\\Users\\INET\\Desktop\\Manual Book\\HuenServiceProvider\\SimpleTest.pdf");
		String writePath = ("C:\\Users\\INET\\Desktop\\Manual Book\\HuenServiceProvider\\test1.pdf");
		File pdfFile = new File(pdfPath);
		File writeFile = new File(writePath);
		
		
		String tsaUrl = "https://tsa.one.th/query";
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
			PdfReader read = new PdfReader(pdfFile);
			StampingProperties stamp = new StampingProperties();
			ITSAClient tsa = new TSAClientBouncyCastle(tsaUrl);
			PdfSigner sign = new PdfSigner(read,bos,stamp);
			sign.timestamp(tsa,System.currentTimeMillis() + "");
			
			
			Files.write(writeFile.toPath(),bos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		System.out.println("DEBUG");
		return "Success";
	}
	
}
