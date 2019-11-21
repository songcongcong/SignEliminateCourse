package com.scc.signeliminateclass.bean;

import java.util.List;

/**
 * UserInfo
 */
public class UserInfo {

    /**
     * error_code : 0
     * error_msg : SUCCESS
     * log_id : 304569260337782611
     * timestamp : 1566033778
     * cached : 0
     * result : {"face_token":"17f387d82041a6f282ae23135869b5e2","user_list":[{"group_id":"ezu_kj","user_id":"515659526701958_1768989271","user_info":"宋聪聪","score":96.255592346191}]}
     */

    /**
     * error_code
     */
    private String error_code;
    /**
     * error_msg
     */
    private String error_msg;
    /**
     * log_id
     */
    private long log_id;
    /**
     * timestamp
     */
    private int timestamp;
    /**
     * cached
     */
    private int cached;
    /**
     * result
     */
    private ResultBean result;

    /**
     * getError_code
     * @return String
     */
    public String getError_code() {
        return error_code;
    }

    /**
     * setError_code
     * @param error_code error_code
     */
    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    /**
     * getError_msg
     * @return String
     */
    public String getError_msg() {
        return error_msg;
    }

    /**
     * error_msg
     * @param error_msg error_msg
     */
    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    /**
     * getLog_id
     * @return long
     */
    public long getLog_id() {
        return log_id;
    }

    /**
     * setLog_id
     * @param log_id log_id
     */
    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    /**
     * getTimestamp
     * @return int
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * setTimestamp
     * @param timestamp timestamp
     */
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * getCached
     * @return int
     */
    public int getCached() {
        return cached;
    }

    /**
     * setCached
     * @param cached cached
     */
    public void setCached(int cached) {
        this.cached = cached;
    }

    /**
     * getResult
     * @return ResultBean
     */
    public ResultBean getResult() {
        return result;
    }

    /**
     * setResult
     * @param result result
     */
    public void setResult(ResultBean result) {
        this.result = result;
    }

    /**
     * ResultBean
     */
    public static class ResultBean {
        /**
         * face_token : 17f387d82041a6f282ae23135869b5e2
         * user_list : [{"group_id":"ezu_kj","user_id":"515659526701958_1768989271","user_info":"宋聪聪","score":96.255592346191}]
         */

        /**
         * face_token
         */
        private String face_token;
        /**
         * user_list
         */
        private List<UserListBean> user_list;

        /**
         * getFace_token
         * @return String
         */
        public String getFace_token() {
            return face_token;
        }

        /**
         * setFace_token
         * @param face_token face_token
         */
        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }

        /**
         * getUser_list
         * @return List<UserListBean>
         */
        public List<UserListBean> getUser_list() {
            return user_list;
        }

        /**
         * setUser_list
         * @param user_list user_list
         */
        public void setUser_list(List<UserListBean> user_list) {
            this.user_list = user_list;
        }

        /**
         * UserListBean
         */
        public static class UserListBean {
            /**
             * group_id : ezu_kj
             * user_id : 515659526701958_1768989271
             * user_info : 宋聪聪
             * score : 96.255592346191
             */
            /**
             * group_id
             */
            private String group_id;
            /**
             * user_id
             */
            private String user_id;
            /**
             * user_info
             */
            private String user_info;
            /**
             * score
             */
            private double score;

            /**
             * getGroup_id
             * @return String
             */
            public String getGroup_id() {
                return group_id;
            }

            /**
             * setGroup_id
             * @param group_id group_id
             */
            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }

            /**
             * getUser_id
             * @return String
             */
            public String getUser_id() {
                return user_id;
            }

            /**
             * setUser_id
             * @param user_id user_id
             */
            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            /**
             * getUser_info
             * @return String
             */
            public String getUser_info() {
                return user_info;
            }

            /**
             * setUser_info
             * @param user_info user_info
             */
            public void setUser_info(String user_info) {
                this.user_info = user_info;
            }

            /**
             * getScore
             * @return double
             */
            public double getScore() {
                return score;
            }

            /**
             * setScore
             * @param score score
             */
            public void setScore(double score) {
                this.score = score;
            }
        }
    }
}
