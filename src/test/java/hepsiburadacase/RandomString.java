package hepsiburadacase;

import java.util.Random;

public class RandomString {
	
	public String randomUserName() {
	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	StringBuilder sb = new StringBuilder();
	Random random = new Random();
	int uzunluk=7;
	
	for(int i = 0;i<uzunluk;i++)
	{

		// generate random index number
		int index = random.nextInt(alphabet.length());

		// get character specified by index
		// from the string
		char randomChar = alphabet.charAt(index);

		// append the character to string builder
		sb.append(randomChar);
	}

	String randomString = sb.toString();
	return randomString;

}
	}
