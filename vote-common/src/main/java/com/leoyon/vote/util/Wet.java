package com.leoyon.vote.util;

import java.util.Random;

public class Wet {
	final static byte[] salt_seeds = new byte[]{
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
			'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
			'U', 'V', 'W', 'X', 'Y', 'Z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '=', '-', '_'
	};
	
	private static byte salt_byte() {
		Random r = new Random();
		return salt_seeds[r.nextInt(salt_seeds.length)];
	}
	
	public static int salt_int() {
		return (salt_byte()<<24)|0x00<<16|(salt_byte()<<8)|salt_byte();
	}
	
	public static String encode(String s) {
		byte[] b = s.getBytes();
		int[] c = new int[b.length];
		for(int i=0; i< b.length; ++i) {
			c[i] = b[i]<<16|salt_int();
		}
		byte[] cb = new byte[Integer.SIZE/Byte.SIZE * c.length];
		int j = 0;
		for(int i=0 ;i<c.length; ++i) {
			cb[j++] = (byte) (c[i]>>24);
			cb[j++] = (byte) (c[i]>>16);
			cb[j++] = (byte) (c[i]>>8);
			cb[j++] = (byte) c[i];
		}
		return new String(cb);
	}
	
	public static String decode(String s) {
		byte[] cb = s.getBytes();
		int[] c = new int[cb.length/(Integer.SIZE/Byte.SIZE)];
		int j = 0;
		for(int i=0; i<cb.length; ) {
			c[j++] = (cb[i++]<<24)|(cb[i++]<<16)|(cb[i++]<<8)|(cb[i++]);
		}
		byte[] d = new byte[c.length];
		for(int i=0; i< c.length; ++i) {
			d[i] = (byte) (c[i]>>16);
		}
		return new String(d);
	}
}
