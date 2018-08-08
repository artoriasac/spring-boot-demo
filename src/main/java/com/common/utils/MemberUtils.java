package com.common.utils;

import com.common.model.MemberInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:47 2018/7/31 0031
 * Modeified By:
 */
public class MemberUtils {

    public static final String MEMBER_INFO="MEMBER_INFO";

    public static void setMemberInfo(HttpServletRequest request, MemberInfo memberInfo){
        request.getSession().setAttribute(MEMBER_INFO,memberInfo);
    }

    public static MemberInfo getMemberInfo(HttpServletRequest request){
       return  (MemberInfo)request.getSession().getAttribute(MEMBER_INFO);
    }

    public static void delMemberInfo(HttpServletRequest request){
        request.getSession().removeAttribute(MEMBER_INFO);
    }
}
