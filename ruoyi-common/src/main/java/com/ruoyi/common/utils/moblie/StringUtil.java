package com.ruoyi.common.utils.moblie;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;






public class StringUtil extends StringUtils {
	public static final String TA_LEFT = "left";
	public static final String TA_RIGHT = "right";
	
	 /**
     * ���������ļ�
     */
    final static Map<String, Properties> propMap = new HashMap<String, Properties>();

	public StringUtil() {
	}

	
	/** 
     * ��ȡWEB-INFĿ¼����server.xml�ļ���·�� 
     * @return 
     */  
    public static String getPath()  
    {  
        //file:/D:/JavaWeb/.metadata/.me_tcat/webapps/TestBeanUtils/WEB-INF/classes/   
        String path=Thread.currentThread().getContextClassLoader().getResource("").toString();  
        path=path.replace('/', '\\'); // ��/����\  
        path=path.replace("file:", ""); //ȥ��file:  
        path=path.substring(1); //ȥ����һ��\,�� \D:\JavaWeb...  
        //System.out.println(path);  
        return path;  
    }  
    
    public static boolean isStringInValid(String... args) {
        for (String str : args) {
            if (str == null || str.length() == 0) {
                return true;
            }
        }
        return false;
    }
    
    public static String middleString(String value, char item, int len) {
        if (len <= value.length()) {
            return value;
        }
        StringBuffer stringBuffer = new StringBuffer(len);
        for (int i = 0; i < len; i++) {
            stringBuffer.append(item);
        }
        int index = (len - value.length()) / 2;
        stringBuffer.replace(index, index + value.length(), value);
        return stringBuffer.toString();
    }
    
    /**
     * ��ȡconfigFile�����ļ��µ�proKey��Ӧֵ
     * 
     * @param configFile
     * @param proKey
     * @return
     */
    public static String getProperty(String configFile, String proKey) {
        Properties prop = propMap.get(configFile);
        if (prop != null) {
            return prop.getProperty(proKey);
        }
        InputStream in = null;
        try {
            in = StringUtil.class.getClassLoader().getResource(configFile).openStream();
            prop = new Properties();
            prop.load(in);
            propMap.put(configFile, prop);
            return prop.getProperty(proKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }
    
	/**
	 * �ַ�����ָ�����ȹ����ַ���,������ո�
	 */
	public static String toStringAlign(String str, int len, String align) {
		String alignText = new String(str);
		if (alignText.length() < len) {
			int count = len - alignText.length();
			while (count > 0) {
				if (StringUtil.equalsIgnoreCase(align, StringUtil.TA_LEFT)) {
					alignText = alignText + " ";
				} else {
					alignText = " " + alignText;
				}
				count--;
			}
		}
		return alignText;

	}

	/**
	 * ������ָ�����ȹ����ַ���,������ո�
	 * 
	 * @param iVal
	 * @param len
	 * @param align
	 *            left,right
	 * @return String
	 */
	public static String toStringAlign(int iVal, int len, String align) {
		return StringUtil.toStringAlign(String.valueOf(iVal), len, align);
	}

	/**
	 * ��������ָ�����ȹ����ַ���,������ո�
	 * 
	 * @param lVal
	 * @param len
	 * @param align
	 * @return
	 */
	public static String toStringAlign(long lVal, int len, String align) {
		return StringUtil.toStringAlign(String.valueOf(lVal), len, align);
	}

	/**
	 * ���ַ�����ɾ����ָ���ַ�
	 * 
	 * @param str
	 * @param ch
	 * @return
	 */
	public static String remove(String str, char ch) {
		String temp = "";
		int index = 0;
		while (index < str.length()) {
			if (str.charAt(index) != ch) {
				temp = temp + String.valueOf(str.charAt(index));
			}
			index++;
		}
		return temp;
	}

	/**
	 * ���ַ�����ɾ����ָ���ַ���
	 * 
	 * @param str
	 * @param revStr
	 * @return
	 */
	public static String remove(String str, String revStr) {
		return StringUtil.replace(str, revStr, "");
	}

	/***************************************************************************
	 * ������ָ�����ȹ����ַ���,���ַ���ո�
	 * 
	 * @param str
	 * @param len
	 * @param align
	 * @param full
	 * @return
	 */
	public static String fullStringAlgin(String str, int len, String align,
			char full) {
		String temp = str;
		if (temp.length() < len) {
			int nCount = len - temp.getBytes().length;
			String fullstr = String.valueOf(full);
			while (nCount > 0) {
				if (StringUtil.equalsIgnoreCase(align, StringUtil.TA_LEFT))
					temp = temp + fullstr;
				else
					temp = fullstr + temp;
				nCount--;
			}
		}
		return temp;
	}

	/***************************************************************************
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean containsIgnoreCase(String str1, String str2[]) {
		boolean aEnable = false;
		for (int index = 0; index < str2.length; index++)
			aEnable |= StringUtil.equalsIgnoreCase(str1, str2[index]) ? true
					: false;
		return aEnable;
	}

	/***************************************************************************
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean contains(String str1, String str2[]) {
		boolean aEnable = false;
		for (int index = 0; index < str2.length; index++)
			aEnable |= StringUtil.equals(str1, str2[index]) ? true : false;
		return aEnable;
	}

	/***************************************************************************
	 * �ж��ַ�����β���Ƿ���ָ���ַ���
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean endsWith(String str1, String str2) {
		if (str1 == null || str2 == null)
			return false;
		if (str1.length() < str2.length())
			return false;
		for (int index = 0; index < str2.length(); index++) {
			if (str1.charAt(str1.length() - index - 1) != str2.charAt(str2
					.length()
					- 1 - index))
				return false;
		}
		return true;
	}

	/***************************************************************************
	 * �ж��ַ�����β���Ƿ���ָ���ַ���(�����ַ���Сд)
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean endsIgnoreCaseWith(String str1, String str2) {
		if (str1 == null || str2 == null)
			return false;
		if (str1.length() < str2.length())
			return false;
		return StringUtil.endsWith(str1.toUpperCase(), str2.toUpperCase());
	}

	/***************************************************************************
	 * �ж��ַ����ڿ�ͷ�Ƿ���ָ���ַ���
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean startWith(String str1, String str2) {
		if (str1 == null || str2 == null)
			return false;
		if (str1.length() < str2.length())
			return false;
		for (int index = 0; index < str2.length(); index++)
			if (str1.charAt(index) != str2.charAt(index))
				return false;
		return true;
	}

	/***************************************************************************
	 * �ж��ַ����ڿ�ͷ�Ƿ���ָ���ַ���(�����ַ���Сд)
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean startIgnoreCaseWith(String str1, String str2) {
		if (str1 == null || str2 == null)
			return false;
		if (str1.length() < str2.length())
			return false;
		return StringUtil.startWith(str1.toUpperCase(), str2.toUpperCase());
	}

	/***************************************************************************
	 * �ж��ַ������Ƿ����ָ���ַ���(�����ַ���Сд)
	 * 
	 * @param lpBuffer
	 * @param containsbuff
	 * @return
	 */
	public static boolean containsIgnoreCase(String lpBuffer,
			String containsbuff) {
		boolean retCode = false;
		if (containsbuff == null || containsbuff.length() <= 0)
			return true;
		if (lpBuffer == null || lpBuffer.length() <= 0
				|| lpBuffer.length() < containsbuff.length())
			return false;
		int index = 0;
		String temp;
		while (index < lpBuffer.length()) {
			temp = StringUtil.mid(lpBuffer, index, containsbuff.length());
			if (StringUtil.equalsIgnoreCase(temp, containsbuff) == true)
				return true;
			index++;
		}
		return retCode;
	}

	/***************************************************************************
	 * 
	 * @param lpBuffer
	 * @param containsbuff
	 * @return
	 */
	public static boolean containsIgnoreBlank(String lpBuffer,
			String containsbuff) {
		lpBuffer = StringUtil.remove(lpBuffer, ' ');
		containsbuff = StringUtil.remove(containsbuff, ' ');
		return StringUtil.contains(lpBuffer, containsbuff);
	}

	/***************************************************************************
	 * �ж������ַ�����С
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static int compare(String str1, String str2) {
		return str1.compareTo(str2);
	}

	/***************************************************************************
	 * �ж������ַ�����С(�����ַ���Сд)
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static int compareIgnoreCase(String str1, String str2) {
		return str1.compareToIgnoreCase(str2);
	}

	/***************************************************************************
	 * ��ָ�����ȷָ��ַ���
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String[] split(String str, int length) {
		String retn[] = null;
		int nCount = str.length() / length + 1;
		retn = new String[nCount];
		for (int i = 0; i < nCount; i++)
			retn[i] = StringUtil.substring(str, length * i, length * (i + 1));
		return retn;
	}

	/***************************************************************************
	 * ���ݷָ�����ȡ�ַ���(�����ɿ��ַ�)
	 * 
	 * @param str
	 * @param splitChar
	 * @return
	 */
	public static String[] splitNoIgnoreBlank(String str, String splitChar) {
		List elementLst = new ArrayList();
		int index = 0, index1 = -splitChar.length();
		while (index1 < str.length()) {
			index = index1 + splitChar.length();
			index1 = StringUtil.indexOf(str, splitChar, index);
			if (index1 < 0)
				index1 = str.length();
			elementLst.add(StringUtil.substring(str, index, index1));
		}
		return (String[]) elementLst.toArray(new String[] {});
	}

	/***************************************************************************
	 * 
	 * @param aITaskExpr
	 * @return
	 */
	public static String[][] splitExpr(String aITaskExpr) {
		List aVector = new java.util.Vector();
		if (StringUtil.isBlank(aITaskExpr) == false
				&& StringUtil.contains(aITaskExpr, "=")) {
			String aITaskExprL[] = StringUtil.split(aITaskExpr, ";");
			for (int index = 0; index < aITaskExprL.length; index++) {
				String temp[] = StringUtil.split(aITaskExprL[index], "=");
				if (temp != null && temp.length > 0) {
					aVector.add(new String[] { temp[0],
							temp.length > 1 ? temp[1] : "" });
				}
			}
		}
		return (String[][]) aVector.toArray(new String[][] {});
	}

	/***************************************************************************
	 * 
	 * @param aRefeValue
	 * @param aITaskExpr
	 * @return
	 */
	public static String splitExpr(String aRefeValue, String aITaskExpr) {
		String aValue = aITaskExpr;
		String aITaskExprL[][] = StringUtil.splitExpr(aITaskExpr);
		if (aITaskExprL != null && aITaskExprL.length > 0) {
			aValue = null;
			for (int index = 0; index < aITaskExprL.length; index++) {
				if (StringUtil.equals(aITaskExprL[index][0], aRefeValue)) {
					aValue = aITaskExprL[index][1];
					break;
				}
			}
		}
		return aValue;
	}

	/***************************************************************************
	 * 
	 * @param aITaskExpr
	 * @param aITaskPrefe
	 */
	public static void splitExpr(String aITaskExpr, Map aITaskExprList) {
		String aITaskExprL[][] = StringUtil.splitExpr(aITaskExpr);
		if (aITaskExprL != null && aITaskExprL.length > 0) {
			for (int index = 0; index < aITaskExprL.length; index++)
				aITaskExprList
						.put(aITaskExprL[index][0], aITaskExprL[index][1]);
		}
	}

	/***************************************************************************
	 * ��ch�滻�ַ�����ָ��λ�õ��ַ�
	 * 
	 * @param str
	 * @param pos
	 * @param ch
	 */
	public static String replace(String str, int pos, char ch) throws Exception {
		if (str == null || str.getBytes().length <= pos)
			throw new ArrayIndexOutOfBoundsException();
		byte srcByte[] = str.getBytes();
		srcByte[pos] = (byte) ch;
		return new String(srcByte);
	}

	/***************************************************************************
	 * ����
	 * 
	 * @param str
	 * @return
	 */
	public static String DoEncrypt(String str) {
		int i;
		int tmpch;
		StringBuffer enStrBuff = new StringBuffer();
		for (i = 0; i < str.length(); i++) {
			tmpch = (int) str.charAt(i);
			tmpch = tmpch ^ 0x01;
			tmpch = tmpch ^ 0x0a;
			enStrBuff.append(Integer.toHexString(tmpch));
		}
		return enStrBuff.toString().toUpperCase();
	}

	/***************************************************************************
	 * ����
	 * 
	 * @param str
	 * @return
	 */
	public static String DoDecrypt(String str) {
		int tmpch;
		String deStr = str.toLowerCase();
		StringBuffer deStrBuff = new StringBuffer();
		for (int i = 0; i < deStr.length(); i += 2) {
			String subStr = deStr.substring(i, i + 2);
			tmpch = Integer.parseInt(subStr, 16);
			tmpch = tmpch ^ 0x01;
			tmpch = tmpch ^ 0x0a;
			deStrBuff.append((char) tmpch);
		}
		return deStrBuff.toString();
	}

	/**
	 * �ж��Ƿ���ͬ
	 * 
	 * @param str1
	 * @param str2
	 * @return �Ƿ���ͬ
	 */
	public static final boolean isSame(String str1, String str2) {
		if (StringUtil.isBlank(str1) && StringUtil.isBlank(str2)) {
			return true;
		} else if (!StringUtil.isBlank(str1) && !StringUtil.isBlank(str2)
				&& str1.equals(str2)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �ж��ַ����Ƿ�Ϊ�ջ���ַ�
	 * 
	 * @param str
	 * @return �Ƿ�Ϊ��
	 */
	public static final boolean isNull(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static final boolean isRegxNumeric(String str) {
		if (str == null || str.trim().equals("")) {
			return false;
		} else {
			return str.matches("-?\\d+(\\.\\d+)?");
		}
	}

	/***************************************************************************
	 * ���ݼ����㷨��������
	 * 
	 * @param algorithm
	 *            ��ϢժҪMD5��SHA-1
	 * @param aEncryptString
	 * @return
	 */
	public static final byte[] DoEncrypt(String algorithm, String aEncryptString)
			throws Exception {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(aEncryptString.getBytes());
			return messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
//			log.error("���ݼ����㷨�������Ĵ���", e);
			throw new Exception("���ݼ����㷨�������Ĵ���", e);
		} finally {
			if (messageDigest != null) {
				messageDigest.reset();
				messageDigest = null;
			}
		}
	}

	/***************************************************************************
	 * 
	 * @param aVariableValue
	 * @return
	 */
	public static final String DoEnginVariable(String aVariableValue) {
		if (aVariableValue == null)
			return null;
		return "$" + aVariableValue;
	}

	/***************************************************************************
	 * �ַ���ͨ����㷨 1,ͨ���*�ţ���ʾ����ƥ���������ַ� 2,ͨ����ʺ�?��ʾƥ��һ���ַ�
	 * ����:�ַ���test.txt,ͨ���Ϊt?s*.txtΪtrue
	 * 
	 * @param pattern
	 * @param str
	 * @return
	 */
	public static final boolean wildcardMatch(String str, String pattern) {
		int patternLength = pattern.length();
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				while (strIndex < strLength) {
					if (wildcardMatch(str.substring(strIndex), pattern
							.substring(patternIndex + 1)))
						return true;
					strIndex++;
				}
			} else if (ch == '?') {
				strIndex++;
				if (strIndex > strLength)
					return false;
			} else {
				if ((strIndex >= strLength || (ch != str.charAt(strIndex))))
					return false;
				strIndex++;
			}
		}
		return (strIndex == strLength);
	}

	/***************************************************************************
	 * 
	 * @param aPackage
	 * @return
	 */
	public static final String wildcardRight(String aPackage, String aMatch) {
		String[] aWildCard = StringUtil.split(aPackage, aMatch);
		return aWildCard[aWildCard.length - 1];
	}

	/***************************************************************************
	 * 
	 * @param aPackage
	 * @return
	 */
	public static final String wildcardLeft(String aPackage, String aMatch) {
		StringBuffer aWildCard = new StringBuffer();
		String[] aWildMatch = StringUtil.split(aPackage, aMatch);
		for (int index = 0; index < aWildMatch.length - 1; index++) {
			aWildCard.append(aWildMatch[index]);
			if (index < aWildMatch.length - 2)
				aWildCard.append(aMatch);
		}
		return aWildCard.toString();
	}

	/***************************************************************************
	 * 
	 * @param aPlatformbin
	 *            �ַ����ֽ�
	 * @return
	 */
	public static final StringBuffer concatBinary(byte[][] aBinary) {
		StringBuffer aStringbin = new StringBuffer();
		for (int index = 0; index < aBinary.length; index++) {
			aStringbin.append(new String(aBinary[index])).append(
					index != aBinary.length - 1 ? "\n" : "");
		}
		return aStringbin;
	}

	/***************************************************************************
	 * ����λ
	 * 
	 * @param len
	 * @return
	 */
	public static final String trimRight(String str, int len) {
		if (str == null || str.length() < len || len < 0)
			return str;
		return StringUtil.substring(str, str.length() - len);
	}

	/***************************************************************************
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static final String trimLeft(String str, int len) {
		if (str == null || str.length() < len || len < 0)
			return str;
		return StringUtil.substring(str, 0, len);
	}

	/***************************************************************************
	 * 
	 * @param t
	 * @return
	 */
	public static final String doThrowable(Throwable t, int aVM_MAX_BYTE) {
		String aThrowable = null;
		try {
			Throwable tt = t;
			if (tt.getCause() != null)
				tt = tt.getCause();
			StringWriter aWrite = new StringWriter();
			PrintWriter out = new PrintWriter(aWrite);
			tt.printStackTrace(out);
			out.flush();
			out.close();
			out = null;
			aThrowable = aWrite.toString();
			aWrite.close();
			aWrite = null;
			if (aThrowable.getBytes().length > aVM_MAX_BYTE) {
				aThrowable = StringUtil.substring(aThrowable, 0, aVM_MAX_BYTE);
			}
		} catch (Throwable e) {
		}
		return aThrowable;
	}

	public static String getEmptyIfNullObj(Object str) {
		return str == null ? "" : str.toString();
	}

	// 2011-07-07
	public static String getCurMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		String month = formatter.format(new Date());
		return month;
	}

	public static String getDate(long date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String month = formatter.format(date);
		return month;
	}
	// 2011-07-07
	public static String getDiffMonth(String inMonth, int i) {
		int iYear = Integer.valueOf(inMonth.substring(0, 4));
		int iMonth = Integer.valueOf(inMonth.substring(4, 6));
		int iMonthTmp = iMonth + i;
		int m = iMonthTmp % 12; // ��
		int n = iMonthTmp / 12; // ��

		if (m <= 0) {
			n--;
			m += 12;
		}
		String sMonth = m < 10 ? "0" + m : String.valueOf(m);

		return (iYear + n) + "" + sMonth;
	}

	// MD5 �����㷨
	public static String MD5(String inStr) {
		MessageDigest md5 = null;

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}

		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	/**
	 * 
	 * ���ַ���Ϊ��ʱ,����defaultValue,���򷵻�ԭ�ַ���
	 * 
	 * @param str
	 *            ԭ�ַ���
	 * 
	 * @param defaultValue
	 *            ���滻���ַ�
	 * 
	 * @return String
	 */

	public static String nvl(String str, String defaultValue) {

		if (str == null) {
			str = defaultValue;
		}
		return str;
	}

	/**
	 * 
	 * �ж������ַ����Ƿ����
	 * 
	 * @param str1
	 *            �ַ���1
	 * 
	 * @param str2
	 *            �ַ���2
	 * 
	 * @return boolean �Ƿ����
	 */

	public static boolean equals(String str1, String str2) {

		if (str1 == null && str2 == null || str1 != null && str1.equals(str2))
			return true;

		else
			return false;

	}

	public static String[] getParamFromString(String aSourceString,
			String aStartStr, String aEndStr) {
		aSourceString = aSourceString + aEndStr;
		String strSource = aSourceString;
		ArrayList strKey = new ArrayList();
		int iStartIndex = strSource.indexOf(aStartStr);
		int iStartLength = aStartStr.length();
		int iEndLength = aEndStr.length();
		String strTemp = "";
		strTemp = strSource.substring(iStartIndex + iStartLength, strSource
				.length());
		int iEndIndex = strTemp.indexOf(aEndStr)
				+ strSource.substring(0, iStartIndex + iStartLength).length();
		if (iEndIndex == iStartIndex) {
			strKey.add(strTemp);
		}
		while ((iStartIndex != -1) && (iEndIndex != -1)
				&& (iStartIndex < iEndIndex)) {
			strTemp = strSource
					.substring(iStartIndex + iStartLength, iEndIndex);
			strKey.add(strTemp);
			strSource = strSource.substring(iEndIndex + iEndLength, strSource
					.length());
			iStartIndex = strSource.indexOf(aStartStr);
			strTemp = strSource.substring(iStartIndex + iStartLength, strSource
					.length());
			iEndIndex = strTemp.indexOf(aEndStr)
					+ strSource.substring(0, iStartIndex + iStartLength)
							.length();
		}
		return ((String[]) (String[]) strKey.toArray(new String[0]));
	}

	/***************************************************************************
	 * 
	 * @param argv
	 */
	public static void main(String argv[]) {
		String str[][] = StringUtil.splitExpr("A=01;B=02;C=03");
//		for (int index = 0; index < str.length; index++)
//			log.debug(str[index][0] + "\t" + str[index][1]);
	}

	public final static String getEmptyIfNull(String input) {
		return input == null ? "" : input;
	}
}
