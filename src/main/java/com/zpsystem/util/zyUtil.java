package com.zpsystem.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;

public class zyUtil {
	/**
	 * 函数功能：获取请求中的数据，存储到一个has表
	 * @param request
	 * @param map
	 */
	
	/**
	 * 测试文档注释
	 */
	public void test() {
		
	}
    public static void getRequestData(HttpServletRequest request, HashMap<String,String> map){
       Enumeration<String > params= request.getParameterNames();
       while (params.hasMoreElements()){
           String paramName=params.nextElement();
           map.put(paramName,request.getParameter(paramName));
       }
    }
    public static String getStr(Scanner scanner) {
        String s=null;
        while (scanner.hasNext()){
            s=scanner.nextLine();
            if (s.length()==0)
                continue;
            break;
        }
        return s;
    }
    public static boolean isPass(String pass){
        if(pass.length()<8 || pass.length()>12)
            return false;
        boolean hasNumber=false,hasLetter=false;
        for (int i = 0; i < pass.length(); i++) {
            char ch=pass.charAt(i);
            if (ch>='0' && ch<='9')
                hasNumber=true;
            else if ((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))
                hasLetter=true;
            else
                return false;

        }
        return hasLetter&&hasNumber;
    }
    public static int getInt(Scanner scanner) {
        int i=0;
        while (scanner.hasNext()){
            String s=scanner.nextLine();
            if (s.length()==0)
                continue;
            try {
                i=Integer.parseInt(s);
                break;
            }catch (Exception e){
                System.out.println("无效的数字");
            }
        }
        return i;
    }

    public static long getLong(Scanner scanner) {
        long i=0;
        while (scanner.hasNext()){
            String s=scanner.nextLine();
            if (s.length()==0)
                continue;
            try {
                i=Long.parseLong(s);
                break;
            }catch (Exception e){
                System.out.println("无效的数字");
            }
        }
        return i;
    }
}
