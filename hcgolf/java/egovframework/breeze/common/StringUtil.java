package egovframework.breeze.common;

import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import com.ibm.icu.impl.TimeZoneGenericNames.Pattern;

import egovframework.breeze.survey.service.SurveyVO;

public class StringUtil {

	/**
	 * 문자의 Null 여부를 판단한다.
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
        if (obj == null) return true;

        if (obj instanceof String) {
            return ((String)obj).equals("");
        } else if (obj instanceof List) {
            return ((List<?>) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        } else if (obj instanceof Object[]) {
            return Array.getLength(obj) == 0;
        } else {
            return obj == null;
        }
	}
	
	/**
	 * 문자열이 Null일 경우 ''(빈 문자열)을 반환 한다.
	 * 
	 * @param str
	 * @return
	 */
	public static String null2Empty(String str) {
		if (str == null) return "";
		return str;
	}

	/**
	 * 문자열이 Null일 경우 지정한 문자열을 반환 한다.
	 * 
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static String null2Default(String str, String defaultValue) {
		if (str == null) return defaultValue;
		return str;
	}

	/**
	 * 문자열이 Null일 경우 지정한 문자열을 반환 한다.
	 * 
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static String null2Default(Object str, String defaultValue) {
		if (str == null) return defaultValue;
		return (String)str;
	}
	
	/**
	 * 인자값으로 받은 배열요소의 값이 null값인이 체크 한다.
	 * 
	 * @param stringTokens
	 * @return
	 */
	public static boolean isAllFull(String... stringTokens) {
		if (stringTokens == null || stringTokens.length == 0) {
			return false;
		} else {
			for (String str : stringTokens) {
				if (isEmpty(str) == true) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * HTML 태그를 치환하여 반환 한다.
	 * 
	 * @param val
	 * @return
	 */
	public static String getEscapeHtml(String source) {
		if("".equals(source) || source == null) {
			return "";
		}

	    StringBuffer stringbuffer = new StringBuffer();
	    char ac[] = source.toCharArray();
	    int i = ac.length;
	    for(int j = 0; j < i; j++) {
	    	//if(ac[j] == '&')
	    		//stringbuffer.append("&amp;");
	    	if(ac[j] == '<')
	            stringbuffer.append("&lt;");
	        else if(ac[j] == '>')
	            stringbuffer.append("&gt;");
	        else if(ac[j] == '"')
	            stringbuffer.append("&quot;");
	        else if(ac[j] == '\'')
	            /*stringbuffer.append("&#039;");*/
	        	stringbuffer.append("&#x27");
	        else if(ac[j] == '/')
	        	stringbuffer.append("&#x2F;");
	        else
	            stringbuffer.append(ac[j]);
	    }

	    return stringbuffer.toString();
	}
	
	/**
	 * 문자열을 태그로 치환하여 반환한다.
	 * @param str
	 * @return
	 */
	public static String getStringToTag(String str) {
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&quot;", "\"");
		str = str.replaceAll("&#39;", "'");
		str = str.replaceAll("&apos;", "'");
		 
		return str;
	}

	
	/**
	 * 문자열을 Byte 기준으로 잘라내고 말줄임표(...)를 표시 한다.
	 * 
	 * @param source
	 * @param maxLen
	 * @return
	 */
	public static String getLimitByte(String source, int maxLen) {
		int len = source.length(); 
		StringBuilder build = new StringBuilder();
		for (int i=0; i < len; i++) {
			if ((maxLen -= source.substring(i, i+1).getBytes().length) < 0) {
				break;
			}
			build.append(source.charAt(i));
		}
		if (build.toString().length() < source.length()) {
			build.append("...");
		}
		return build.toString();
	}
	
	/**
	 * 숫자를 소숫점 4자리까지 반영된 콤마 형태로 변환한다.
	 * @param source
	 * @return
	 */
	public static String getAddComma(String source) {
		try {
			DecimalFormat decFormat = new DecimalFormat("#,###,###,###.####");
			return decFormat.format(Double.parseDouble(source));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return source;
	}
	
	/**
	 * 시간을 받아와 초로 반환해준다.
	 * @param hhmmss
	 */
	public static String getSecond(String hhmmss){
		String rtnStr ="";
		if (!hhmmss.equals("")){
			String[] arr_hhmmss = hhmmss.split(":");
			int hour_s = Integer.parseInt(arr_hhmmss[0]) * 3600;
			int min_s = Integer.parseInt(arr_hhmmss[1]) * 60;
			int sec_s = Integer.parseInt(arr_hhmmss[2]);
			
			rtnStr = String.valueOf(hour_s + min_s + sec_s);
		}
		return rtnStr;
	}
	

	/**
	 * 초를 받아와 시분초으로 반환해준다.
	 * @param seconed
	 */
	public static String getHMS(String seconed){
		String rtnStr ="";
		if (!seconed.equals("")){
			int hour_s = (int) Math.floor(Integer.parseInt(seconed)/3600);
			int min_s = (int) Math.floor(Integer.parseInt(seconed) % 3600/60);
			int sec_s = Integer.parseInt(seconed) % 3600 % 60;
			
			rtnStr = String.valueOf(hour_s +":"+ min_s +":"+ sec_s);
		}
		return rtnStr;
	}
	
	public static String encode(String str) {
		if(str == null) return "";
		else return URLEncoder.encode(str);
	}
	public static String decode(String str) {
		if(str == null) return "";
		else return URLDecoder.decode(str);
	}
	
	/**
	 * 단계가 있는 수의 크기를 비교한다.
	 * @param x
	 * @param y
	 * @return
	 */
	public static int compareCode(String x, String y, String separator) {
        int i = 0;
        String[] xl = (x + separator + "0").split(separator);
        String[] yl = (y + separator + "0").split(separator);
        while ( xl.length > i && yl.length > i && xl[i].equals(yl[i]) ){
        	i++;
        }
        while (xl.length <= i || yl.length <= i){
        	i--;
        }
        try {			        	
        	return Integer.parseInt(xl[i]) - Integer.parseInt(yl[i]);
        } catch (NumberFormatException e) {
        	return xl[i].compareTo(yl[i]);
        }
	}
}
