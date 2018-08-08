package com.common.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:17 2018/7/31 0031
 * Modeified By:
 */
@Component
public class SendMessageUtils {

    private static   String SING_NAME;

    private static String TEMPLATE_CODE;

    private static   String PRODUCT;

    private static  String DOMAIN;

    private static String ACCESS_KEY_ID;

    private static String ACCESS_KEY_SECRET;

    @Value("${SING_NAME}")
    public  void setSingName(String singName) {
        SING_NAME = singName;
    }

    @Value("${TEMPLATE_CODE}")
    public  void setTemplateCode(String templateCode) {
        TEMPLATE_CODE = templateCode;
    }

    @Value("${PRODUCT}")
    public  void setPRODUCT(String PRODUCT) {
        SendMessageUtils.PRODUCT = PRODUCT;
    }

    @Value("${DOMAIN}")
    public  void setDOMAIN(String DOMAIN) {
        SendMessageUtils.DOMAIN = DOMAIN;
    }

    @Value("${ACCESS_KEY_ID}")
    public  void setAccessKeyId(String accessKeyId) {
        ACCESS_KEY_ID = accessKeyId;
    }

    @Value("${ACCESS_KEY_SECRET}")
    public  void setAccessKeySecret(String accessKeySecret) {
        ACCESS_KEY_SECRET = accessKeySecret;
    }

    public static Boolean sendMessage(String phoneNum, String securityCode){
        try {
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数
            //替换成你的AK
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID,
                    ACCESS_KEY_SECRET);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest sendSmsRequest = new SendSmsRequest();
            //使用post提交
            sendSmsRequest.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            sendSmsRequest.setPhoneNumbers(phoneNum);
            //必填:短信签名-可在短信控制台中找到
            sendSmsRequest.setSignName(SING_NAME);
            //必填:短信模板-可在短信控制台中找到
            sendSmsRequest.setTemplateCode(TEMPLATE_CODE);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            sendSmsRequest.setTemplateParam("{\"code\":\""+securityCode+"\"}");
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            //request.setOutId("yourOutId");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(sendSmsRequest);
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
