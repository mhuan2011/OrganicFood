package organicfood.bean;

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
}
