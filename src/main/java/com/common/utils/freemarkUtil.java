package com.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class freemarkUtil {

    private Configuration configuration = null;

    public freemarkUtil(){
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    public static void main(String[] args) {
        freemarkUtil test = new freemarkUtil();
        test.createWord();
    }

    public void createWord(){
        Map<String,Object> dataMap=new HashMap<String,Object>();
        dataMap.put("name","abyss");
        configuration.setClassForTemplateLoading(this.getClass(), "/freemarker");

        Template t=null;
        try {
            t = configuration.getTemplate("test.ftl"); //文件名
        } catch (IOException e) {
            e.printStackTrace();
        }
        File outFile = new File("E:/test1.doc");  //导出文档的存放位置
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            t.process(dataMap, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
