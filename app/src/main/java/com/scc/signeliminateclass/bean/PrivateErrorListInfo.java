package com.scc.signeliminateclass.bean;

import java.util.List;

/**
 * @author
 * @data 2019/11/12
 */
public class PrivateErrorListInfo {
    /**
     * message : [{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190909191337","nickname":"姓名1昵称","id":26},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190909192946","nickname":"dasda","id":53},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190917132359","nickname":"原木清风","id":62},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190917174510","nickname":"雪碧","id":63},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190917145627","nickname":"啊红","id":64},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902132627","nickname":"测色","id":65},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902132756","nickname":"测试啊","id":66},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902133041","nickname":"测试3","id":67},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902133215","nickname":"测试4","id":68},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902133313","nickname":"测试5","id":69},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902133436","nickname":"测试6","id":70},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190729160738","nickname":"666","id":71},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902133721","nickname":"测试7","id":72},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902133838","nickname":"测","id":73},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902134123","nickname":"111","id":74},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902134327","nickname":"测试222","id":75},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902134444","nickname":"测试444","id":76},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902134614","nickname":"测试555","id":77},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902135330","nickname":"测试888888","id":78},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902135616","nickname":"测试999","id":79},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902135728","nickname":"测试1000","id":80},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190902135850","nickname":"测试101","id":81},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190909191458","nickname":"师德师风师德师风","id":87},{"image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190918111417","nickname":"私教大叔大婶","id":94},{"image":"https://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190918111704","nickname":"私教1213123","id":95},{"image":"https://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190918133045","nickname":"大叔大婶","id":97},{"image":"https://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190919105912","nickname":"虎虎虎","id":99},{"image":"https://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190919110107","nickname":"明虎","id":100}]
     * data : 操作成功
     * code : 200
     */

    private String data;
    private String code;
    private List<MessageBean> message;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * image : http://yizutiyu.oss-cn-beijing.aliyuncs.com/huiji/touxiang/20190909191337
         * nickname : 姓名1昵称
         * id : 26
         */

        private String image;
        private String nickname;
        private int id;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
