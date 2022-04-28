package com.haha.common.domain;

import com.haha.common.utils.LocaleUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ReturnMessage {
    // 静态变量
    OK("msg.ok"),
    ERROR("msg.error");

    // 变量
    private String message;

    // 构造函数
    ReturnMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return getMessage(null);
    }

    // 获取值方法,通过国际化方式
    public String getMessage(String language){
        return getMessage(language, null);
    }

    /**
     * 真实获取国际化方法
     * @param language 语言_区域信息
     * @param basename 国际化配置文件路径/名称
     * @return
     */
    public String getMessage(String language, String basename){
        Locale locale = LocaleUtils.getLocale(language);  // 工具类获取当地语言
        basename = StringUtils.isBlank(basename) ? "i18n/msg" : basename;
        ResourceBundle bundle = ResourceBundle.getBundle(basename, locale);
        return bundle.getString(this.message);
    }

}
