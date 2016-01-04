package com.ctlovedove.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 字符串处理.
 * 
 * @version  1.0
 * @author 陈婷
 */
public class StringUtil {
	private static final Logger logger = Logger.getLogger(StringUtil.class);
	/**
	 *判断字符串是否为非空(不是 null 或 "" 或 "null").
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @param str 要进行判断的字符串
	 * @return  字符串为空返回false，非空返回true. 
	 *注意：
	 */
    public static boolean isNotNull(String str){
        boolean isNotNull = true;
        if(str == null || "".equals(str.trim()) || "null".equals(str)){
            isNotNull = false;
        }
        return isNotNull;
    }

	/**
	 *转换空值为一个字符串对象.
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @param str 要进行转换的字符串
	 * @return  如为空返回"",非空则返回原字符. 
	 *注意：
	 */
    public static String converNullTostr(String str){
        if(str == null || "".equals(str.trim()) || "null".equals(str)){
            str = "";
        }
        return str;
    }

	/**
	 *判断字符串是否为空.
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @param str 要进行判断的字符串
	 * @return  如为空返回true,非空则返回false. 
	 *注意：
	 */
    public static boolean isNull(String str){
        boolean isNull = true;
        if(str != null && !"".equals(str.trim()) && !"null".equals(str)){
            isNull = false;
        }
        return isNull;
    }

	/**
	 *随机生成通用唯一标识符.
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @return 返回通用唯一标识符. 
	 *注意：
	 */
	public static String randomUUID(){
		
		return UUID.randomUUID().toString();
	}

	/**
	 *使字符串增加到指定长度.
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @param instr 传入的字符串
	 * @param length 指定的字符串长度。
	 * @return 返回扩展到指定长度后的字符串. 
	 *注意：
	 */
	public static String setLength(String instr,int length){
		
		int realLen = instr.getBytes().length; 
		if(realLen<length){ 
		  for(int i=realLen;i<length;i++){ 
			  instr=instr+" "; 
		  } 
		} 
		return instr;
	}

	/**
	 *字符串截取(获取指定中文数的字符串).
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @param subject 待处理 字符串
	 * @param num 取前几个(中文字长)
	 * @return 返回指定长度的字符串. 
	 *注意：
	 */
	public static String getShortString(String subject, int num) {

		int n = 0;
		int i = 0;
		int j = 0;
		int byteNum=num*2;
		
		boolean flag = true;
		if (subject == null) {
			return " ";
		}
		if (subject.endsWith(".")) {
			subject = subject.substring(0, subject.length() - 1);
		}

		for (i = 0; i < subject.length(); i++) {
			if ((int) (subject.charAt(i)) < 128) {
				n += 1;
			} else {
				n += 2;
			}
			if (n > byteNum && flag) {
				j = i;
				flag = false;
			}
			if (n >= byteNum + 2)
				break;
		}

		if (n >= byteNum + 2 && i != subject.length() - 1) {
			subject = subject.substring(0, j);
			subject += "…";
		}

		return subject;
	}
	
	/**
	 * 功能的简述. 获取字符串长度，一个中文占2个字符
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 *
	 * @param content
	 * @return
	 * 注意：
	 */
	public static int getStringLength(String content) {

		if(StringUtil.isNull(content)){
			return 0;
		}
		content = content.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("&apos;", "'");
		content = content.replaceAll("[\u4E00-\u9FA5]", "xx");
		return content.length();
	}
	

	/**
	 *代码转换.
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @param s 要转换的字符串
	 * @param code1 原字符集。
	 * @param code2 目的字符集。
	 * @return  返回转码后的字符串. 
	 *注意：
	 */
	public static String covertCode (String s,String code1,String code2)
  {
     String result=null;
     try{
         if(s == null || s.trim().equals(""))
            result=null;
         else
            result=new String(s.getBytes(code1),code2);

     }
     catch(java.io.UnsupportedEncodingException  ex)
     {
        logger.info(ex.toString());
     }
     return result;
  }

	/**
	*功能的简述: 格式化日期
	* <br>修改历史：
	* <br>修改日期  修改者 BUG小功能修改申请单号
	* <br>
	* @param s 要转换的日期
	* @return  返回转码后的字符串. 
	*注意：
	*/
	public static String DateString(Date date){
     if(date==null) date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     return sdf.format(date).toString();
	}
  
	/**
	*功能的简述: 转换数据库使用的特殊字符
	* <br>修改历史：
	* <br>修改日期  修改者 BUG小功能修改申请单号
	* <br>
	* @param strReplace 要处理的字符串
	* @return  返回替换码后的字符串. 
	*注意：
	*/
	public static String replaceDBSpecialChar(String strReplace) {

		String strRet = strReplace;
		if (strRet != null)
			strRet = strRet.replaceAll("'", "''");
		else
			strRet = "";

		return strRet;
	}
	

	/**
	 * 获取一个字符串的 md5，默认用 UTF-8
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @param data 需要处理的字符串
	 * @return 编码后的字符串
	 *注意：
	 */
	public static String md5(String data) {
		return md5(data, "UTF-8");
	}

	/**
	 * 获取一个字符串的 md5
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @param data 需要处理的字符串
	 * @param charset 所采用的 charset
	 * @return 编码后的字符串
	 *注意：
	 */
	public static String md5(String data, String charset) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");// 创建一个MD5消息文搞
			m.update(data.getBytes(charset));// 更新被文搞描述的位元组
			byte s[] = m.digest();// 最后更新使用位元组的被叙述的排列,然后完成文摘计算
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < s.length; i++) {
				// 进行十六进制转换
				result.append(Integer.toHexString(
						(0x000000FF & s[i]) | 0xFFFFFF00).substring(6));
			}
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * escape编码(与javascript的escape功能完全一致，类似encodeing)
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @param src	需要编码的字符串
	 * @return		编码后的字符串
	 *注意：
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * escape解码，和javascript的unescape通用
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @param src	需要解码的字符串
	 * @return		解码后的字符串
	 *注意：
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

    /**
     * 判断是否为合法的日期时间字符串
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @param rDateFormat 期望的合法日期格式
     * @return boolean;符合为true,不符合为false
	 *注意：
     */
	public static boolean isDate(String str_input,String rDateFormat){
		if(!isNull(str_input)){
			SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
			formatter.setLenient(false);
			try{
				//formatter.format(formatter.parse(str_input));
				String s = formatter.format(formatter.parse(str_input));
				return str_input.equals(s);
			}catch(Exception e){
				return false;
			}
		}
		return false;
	}

    /**
     * 判断是否为合法的日期时间字符串。
     *允许的格式:yyyy-MM-dd、yyyyMMdd、yyyy-MM-dd HH:mm:ss、yyyyMMddHHmmss
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false
	 *注意：
     */
	public static boolean isDate(String str_input){
		boolean isOk=false;
		if(isNull(str_input)){
			return isOk;
		}
		if(!isOk){
			isOk=isDate(str_input, "yyyy-MM-dd");
		}
		if(!isOk){
			isOk=isDate(str_input, "yyyyMMdd");
		}
		if(!isOk){
			isOk=isDate(str_input, "yyyy-MM-dd HH:mm:ss");
		}
		if(!isOk){
			isOk=isDate(str_input, "yyyyMMddHHmmss");
		}
		return isOk;
	}

	/**正则表达式字符串匹配
	* <br>修改历史：
	* <br>修改日期  修改者 BUG小功能修改申请单号
	* <br>
	* @param regex 正则表达式字符串
	* @param str 要匹配的字符串
	* @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	*注意：
	*/
	private static boolean match( String regex ,String str_input ){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher( str_input );
		return matcher.matches();
	}

    /**
     * 判断是否为合法的email地址字符串
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false
	 *注意：
     */
	public static boolean isEmail(String str_input){
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return match( regex ,str_input );
	}

    /**
     * 判断是否为合法的电话号码
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false
	 *注意：
     */
	public static boolean isPhone(String str_input){
		String regex = "^(([0-9]{11})|^(([0-9]{7,8})|([0-9]{4}|[0-9]{3})-([0-9]{7,8})|([0-9]{4}|[0-9]{3})-([0-9]{7,8})-([0-9]{4}|[0-9]{3}|[0-9]{2}|[0-9]{1})|([0-9]{7,8})-([0-9]{4}|[0-9]{3}|[0-9]{2}|[0-9]{1}))$)";
		return match( regex ,str_input );
	}

    /**
     * 判断是否为合法的移动电话号码
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false
	 *注意：
     */
	public static boolean isMobilePhone(String str_input){
		String regex = "^1[3,8,5][0-9]{9}";
		return match( regex ,str_input );
	}

    /**
     * 判断是否为数字
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false
	 *注意：
     */
	public static boolean isNumeric(String str_input){
		if(isNull(str_input)){
			return false;
		}
		if(str_input.matches("\\d*")){
			return true;
		}else{
			return false;
		}
	}

    /**
     * 判断是否合法url地址
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false
	 *注意：
     */
	public static boolean isURL(String str_input){
		String regex = "^(http|ftp|file)://.*";
		return match( regex ,str_input );
	}

    /**
     * 判断是否为合法身份证号
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false
	 *注意：
     */
	public static boolean isIDCard(String str_input){
		if (str_input.length() == 15) {
			str_input = uptoeighteen(str_input);
		}
		
		if (str_input.length() != 18) {
			return false; 
		}
		
		String verify = str_input.substring(17, 18);
		verify=verify.toUpperCase();
		
		if (verify.equals(getVerify(str_input))) {
			if(verifyIDCardYear(str_input)){
				return verifyIDCardArea(str_input);
			}
		}
		return false;
	}

    /**
     * 身份证号码15位升级到18位(isIDCard中用)
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param fifteencardid 15位身份证号
     * @return 18位身份证号
	 *注意：
     */
	private static String uptoeighteen(String fifteencardid) {
		String eightcardid = fifteencardid.substring(0,6);
		eightcardid = eightcardid + "19";
		eightcardid = eightcardid + fifteencardid.substring(6,15);
		eightcardid = eightcardid + getVerify(eightcardid);
		return eightcardid;
	}

    /**
     * 辅助方法(isIDCard中用)
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @return 返回校验码
	 *注意：
     */
	
	private static String getVerify(String eightcardid) {
		final int[] wi = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1};
		final int[] vi = {1,0,'X',9,8,7,6,5,4,3,2};
		int[] ai = new int[18];
		
		int remaining = 0;
		if (eightcardid.length() == 18) {
			eightcardid = eightcardid.substring(0, 17);
		}
		if (eightcardid.length() == 17) {
			int sum = 0;
			for (int i = 0; i < 17; i++) {
				String k = eightcardid.substring(i, i + 1);
				ai[i] = Integer.parseInt(k);
			}
			
			for (int i = 0; i < 17; i++) {
				sum = sum + wi[i] * ai[i];
			}
			remaining = sum % 11;
		}
		return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
	} 

    /**
     * 辅助方法(isIDCard中用)
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false
	 *注意：
     */
	private static boolean verifyIDCardYear(String eightcardid){
		String cardYear = eightcardid.substring(6,14);
		return isDate(cardYear,"yyyyMMdd");
	}

    /**
     * 辅助方法(isIDCard中用)
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false
	 *注意：
     */
	private static boolean verifyIDCardArea(String eightcardid){
		String[] aCity = new String[] { null, null, null, null, null, null, null,
                null, null, null, null, "北京", "天津", "河北", "山西", "内蒙古", null, null, null,
                null, null, "辽宁", "吉林", "黑龙江", null, null, null, null, null, null, null,
               "上海", "江苏", "浙江", "安微", "福建", "江西", "山东", null, null, null, "河南", "湖北",
               "湖南", "广东", "广西", "海南", null, null, null, "重庆", "四川", "贵州", "云南", "西藏",
                null, null, null, null, null, null, "陕西", "甘肃", "青海", "宁夏", "***", null,
                null, null, null, null, "台湾", null, null, null, null, null, null, null,
                null, null, "香港", "澳门", null, null, null, null, null, null, null, null,
               "国外", null, null, null, null, null, null, null, null };
		if(aCity[Integer.parseInt(eightcardid.substring(0, 2))] == null) {
			return false;
		}else{
			return true;
		}
	}

    /**
     * 判断是否为合法IP地址
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
     * @param str_input 要进行判断的字符串
     * @return boolean;符合为true,不符合为false
	 *注意：
     */
	public static boolean isIP(String str_input){
		 try{
			 String number = str_input.substring(0,str_input.indexOf('.'));
			 if(Integer.parseInt(number) > 255)
				 return false;
			 str_input = str_input.substring(str_input.indexOf('.')+ 1);
			 number = str_input.substring(0,str_input.indexOf('.'));
			 if(Integer.parseInt(number) > 255)
				 return false;
			 str_input = str_input.substring(str_input.indexOf('.')+ 1);
			 number = str_input.substring(0,str_input.indexOf('.'));
			 if(Integer.parseInt(number) > 255)
				 return false;
			 number = str_input.substring(str_input.indexOf('.')+ 1);
			 if (Integer.parseInt(number) > 255)
				 return false;
			 return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	/**
	 * 根据提供的图片路径，得到目录和文件名
	 * @param filePath
	 * @return fileDirAndName[0]：目录，fileDirAndName[1]：文件名
	 */
	public static String[] photoSplit(String filePath){
		String[] fileDirAndName = new String[2];		
	     if(filePath != null && !filePath.equals("")){
	         int flg = filePath.lastIndexOf("/");
	         fileDirAndName[0] = filePath.substring(0,flg+1);
	         fileDirAndName[1] = filePath.substring(flg+1);
	         return fileDirAndName;
	     }else{
	         return null;
	     }
	}
	
	/**
	 * 同struts标签的filter=true;
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 * <br>
	 * @param str
	 */
	public static String htmlFilter(String str){
		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	
	
	/**
	 * 将字符串编码成 Unicode 。
	 * @param theString 待转换成Unicode编码的字符串。
	 * @param escapeSpace 是否忽略空格。
	 * @return 返回转换后Unicode编码的字符串。
	 */
	public static String toUnicode(String theString, boolean escapeSpace) {
		int len = theString.length();
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuffer outBuffer = new StringBuffer(bufLen);
		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			// Handle common case first, selecting largest block that
			// avoids the specials below
			if ((aChar > 61) && (aChar < 127)) {
//				if (aChar == '\\') {
//					outBuffer.append('\\');
//					outBuffer.append('\\');
//					continue;
//				}
				outBuffer.append(aChar);
				continue;
			}
			switch (aChar) {
			case ' ':
				if (x == 0 || escapeSpace){
					outBuffer.append('\\');
				}
				outBuffer.append(' ');
				break;
			case '\t':
				outBuffer.append('\\');
				outBuffer.append('t');
				break;
			case '\n':
				outBuffer.append('\\');
				outBuffer.append('n');
				break;
			case '\r':
				outBuffer.append('\\');
				outBuffer.append('r');
				break;
			case '\f':
				outBuffer.append('\\');
				outBuffer.append('f');
				break;
			case '=': // Fall through
//			case ':': // Fall through
			case '#': // Fall through
//			case '\\': continue;// Fall through
			case '!':
				outBuffer.append('\\');
				outBuffer.append(aChar);
				break;
			default:
				if ((aChar < 0x0020) || (aChar > 0x007e)) {
					outBuffer.append('\\');
					outBuffer.append('u');
					outBuffer.append(toHex((aChar >> 12) & 0xF));
					outBuffer.append(toHex((aChar >> 8) & 0xF));
					outBuffer.append(toHex((aChar >> 4) & 0xF));
					outBuffer.append(toHex(aChar & 0xF));
				} else {
					outBuffer.append(aChar);
				}
			}
		}
		return outBuffer.toString();
	}
	
	public static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}
	
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	 /**
     * 
     * unicode 转换成 中文
     * @param theString
     * @return
     */
 
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't'){
						aChar = '\t';
					}else if (aChar == 'r'){
						aChar = '\r';
					}else if (aChar == 'n'){
						aChar = '\n';
					}else if (aChar == 'f'){
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}

			} else{
				outBuffer.append(aChar);
			}

		}

		return outBuffer.toString();
	}
	
	/**
	 * 功能的简述. 对Object对象中的字符串属性值中含有的html标签进行转义
	 * <br>修改历史：
	 * <br>修改日期  修改者 BUG小功能修改申请单号
	 *
	 * @param o
	 * @return
	 * 注意：
	 */
	public static void filterHtml(Object o) {
		try {
			Field[] field = o.getClass().getDeclaredFields();
			if(field == null || field.length == 0){
				return;
			}
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				if (!"String".equals(field[i].getType().getSimpleName().toString())) {
					continue;
				}
				if (field[i].get(o) == null || "".equals(field[i].get(o).toString().toString())) {
					continue;
				}
				field[i].set(o, field[i].get(o).toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;"));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向请求页面返回数据
	 * @param response
	 * @param returnValue
	 * @throws IOException
	 */
	public static void sendResponse(HttpServletResponse response, String returnValue, String charset) throws IOException{
		response.setContentType("text/html;charset="+charset+"");
		response.getWriter().println(returnValue);
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("charset", charset);  
	}
	
	public static void main(String[] args) {
		String str = "你好中国家里是空间大发了dfasdfads";
		//logger.info(toUnicode(str, false));
		//logger.info(StringEscapeUtils.escapeJava(str));
		System.out.println(getStringLength(str));
	}
}
