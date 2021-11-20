package organicfood.bean;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class StringUtil {
	public static String baCham(String s, int soKiTu, String loaiBaCham) {
		if(s==null) return "";
		if(s.length()<=soKiTu) return s;
		return s.substring(0,soKiTu-loaiBaCham.length())+loaiBaCham;
	}
	
	public static String newLine(String s, int quatityCharacter) {
			StringBuilder sb = new StringBuilder(s);
			int i = 0;
			while ((i = sb.indexOf(" ", i + quatityCharacter)) != -1) {
			    sb.replace(i, i + 1, "\n");
			}
			return sb.toString();
	}
	
	public static double roundDouble(double n) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		System.out.println("format double"+Double.parseDouble(df.format(n)));
		return Double.parseDouble(df.format(n));
	}
}
