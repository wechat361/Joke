/**
 * 软件著作权： 中华网科技公司
 * 系统名称： C_UTF8 v1.0
 * 相关文档： C_UTF8系统设计说明书.doc
 * 修改历史：
 */

package com.ctlovedove.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


/**
 * IP帮助类
 * @author chenting
 *
 */
public class IpUtil{
    /**
     * 获得真实的客户端的ip <br>
     * 修改历史： <br>
     * 修改日期 修改者 BUG小功能修改申请单号 <br>
     * 
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip.indexOf(",") > -1){
        	String[] ips = ip.split(",");
        	ip = ips[0];
        }
        return ip;
    }

 

    /**
     * 判断ip是否在里面
     * 
     * @param regexFontTag
     * @param str
     * @return
     */
    public static boolean degust(String regexFontTag, String str) {
        Pattern p = Pattern.compile(regexFontTag);
        Matcher m = p.matcher(str);
        return m.find();
    }

    
    /**
     * 判断ip地址是否正确
     * 
     * @param ip
     * @return
     */
    public static boolean isIP(String ip) {
		String ipCheck = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";
		Pattern pattern = Pattern.compile(ipCheck);
		Matcher matcher = pattern.matcher(ip);
		return matcher.matches();
	}

}
