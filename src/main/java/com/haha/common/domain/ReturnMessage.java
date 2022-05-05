package com.haha.common.domain;

import com.haha.common.utils.LocaleUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ReturnMessage {
    // 静态变量
    OK("msg.ok"),
    ERROR("msg.error"),

    // 增
    ADD_OK("msg.ok.add"),
    ADD_ERROR("msg.error.add"),
    BATCH_ADD_OK("msg.ok.batch.add"),
    BATCH_ADD_ERROR("msg.error.batch.add"),

    // 删
    DELETE_OK("msg.ok.delete"),
    DELETE_ERROR("msg.error.delete"),
    BATCH_DELETE_OK("msg.ok.batch.delete"),
    BATCH_DELETE_ERROR("msg.error.batch.delete"),

    // 改
    UPDATE_OK("msg.ok.update"),
    UPDATE_ERROR("msg.error.update"),
    BATCH_UPDATE_OK("msg.ok.batch.update"),
    BATCH_UPDATE_ERROR("msg.error.batch.update"),

    // 查
    SELECT_OK("msg.ok.select"),
    SELECT_ERROR("msg.error.select"),
    BATCH_SELECT_OK("msg.ok.batch.select"),
    BATCH_SELECT_ERROR("msg.error.batch.select"),

    // 异常
    EXCEPTION("exception"),
    EXCEPTION_SYS_INNER("exception.sys.inner"),
    EXCEPTION_ARGS("exception.args"),

    // 其它
    DATA_EXISTED("msg.data.existed"),
    DATA_NOT_EXIST("msg.data.not.exist");

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
