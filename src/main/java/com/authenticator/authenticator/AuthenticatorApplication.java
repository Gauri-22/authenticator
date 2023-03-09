package com.authenticator.authenticator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.authenticator.authenticator.util.Utils;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class AuthenticatorApplication {

	// public static void main(String[] args) {
	// 	SpringApplication.run(AuthenticatorApplication.class, args);
	// }

	public static void main(String[] args) throws IOException, WriterException {
		String secretKey = "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK";
		String email = "test@gmail.com";
		String companyName = "Vivanet";
		String barCodeUrl = Utils.getGoogleAuthenticatorBarCode(secretKey, email, companyName);
		// System.out.println(barCodeUrl);
		Utils.createQRCode(barCodeUrl, "qrcode/QRCode.png", 400, 400);

		System.out.print("Please enter 2fA code here -> ");
		Scanner scanner = new Scanner(System.in);
		String code = scanner.nextLine();
		if (code.equals(Utils.getTOTPCode(secretKey))) {
			System.out.println("Logged in successfully");
		} else {
			System.out.println("Invalid 2FA Code");
		}
	}
}
