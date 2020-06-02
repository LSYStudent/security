package com.lsy.test.security.code;


public enum ValidateCodeType {

    SMS {
        @Override
        public String getParamNameOnValidate() {
            return "smsCode";
        }

        @Override
        public String getTextString() {
            return "短信";
        }
    },

    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return "imageCode";
        }

        @Override
        public String getTextString() {
            return "图片";
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     *
     * @return
     */
    public abstract String getParamNameOnValidate();

    /**
     * 返回文本数据
     * @return
     */
    public abstract String getTextString();
}
