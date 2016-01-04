package com.ctlovedove.tags;
/**
 * 自定义的字符串操作el表达式
 * @author chenting
 *
 */
public class StringTags {

	/**
	 * 将html标签过滤掉后再截取指定的长度
	 * @param str   字符串
	 * @param length  指定长度
	 * @return
	 */
	public static String cutStr(String str, int length){
		if(str == null || "".equals(str) || "null".equals(str)){
			return "";
		}
		str = filterHtml(str);
		str = str.trim();
		if(str.length() > length){
			str = str.substring(0, length) + "...";
		}
		return str;
	}
	
	/**
	 * 过滤html标签
	 * @param str   字符串
	 * @return
	 */
	public static String filterHtml(String str){
		if(str == null || "".equals(str) || "null".equals(str)){
			return "";
		}
		str = str.replaceAll("&nbsp;", " ").replaceAll("<[^>]+>", "")
				.replaceAll("'", "\"");
		return str;
	}
	
	public static void main(String[] args) {
		String a = "<span class=\"custom_item weixin\"></span><a title=\"分享到新浪微博\" class=\"sinaminiblog\" href=\"javascript:void(0);\">asdfkjasdlkjfklasdjfldjsl</a><a title=\"分享到QQ空间\" class=\"qzone\" href=\"javascript:void(0);\"></a><a title=\"分享到腾讯微博\" class=\"qqmb\" href=\"javascript:void(0);\"></a>";
		System.out.println(cutStr(a, 9));
	}
}
