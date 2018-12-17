package com.example.wisdom.partybuilding.mvp.bean.home;

import java.util.List;

public class DetailBean {

    /**
     * nian : 登录成功
     * xiangqing : [{"shijian1":"登录ssss成s功","xiangqissng":[{"shijian1":"登功","shijian2":"登ssssss功","jine":"登功","qudao":"登ssssss功"},{"shijian1":"登功","shijian2":"登ssssss功","jine":"登功","qudao":"登ssssss功"},{"shijian1":"登功","shijian2":"登ssssss功","jine":"登功","qudao":"登ssssss功"}]}]
     */

    private String nian;
    private List<XiangqingBean> xiangqing;

    public String getNian() {
        return nian;
    }

    public void setNian(String nian) {
        this.nian = nian;
    }

    public List<XiangqingBean> getXiangqing() {
        return xiangqing;
    }

    public void setXiangqing(List<XiangqingBean> xiangqing) {
        this.xiangqing = xiangqing;
    }

    public static class XiangqingBean {
        /**
         * shijian1 : 登录ssss成s功
         * xiangqissng : [{"shijian1":"登功","shijian2":"登ssssss功","jine":"登功","qudao":"登ssssss功"},{"shijian1":"登功","shijian2":"登ssssss功","jine":"登功","qudao":"登ssssss功"},{"shijian1":"登功","shijian2":"登ssssss功","jine":"登功","qudao":"登ssssss功"}]
         */

        private String shijian1;
        private List<XiangqissngBean> xiangqissng;

        public String getShijian1() {
            return shijian1;
        }

        public void setShijian1(String shijian1) {
            this.shijian1 = shijian1;
        }

        public List<XiangqissngBean> getXiangqissng() {
            return xiangqissng;
        }

        public void setXiangqissng(List<XiangqissngBean> xiangqissng) {
            this.xiangqissng = xiangqissng;
        }

        public static class XiangqissngBean {
            /**
             * shijian1 : 登功
             * shijian2 : 登ssssss功
             * jine : 登功
             * qudao : 登ssssss功
             */

            private String shijian1;
            private String shijian2;
            private String jine;
            private String qudao;

            public String getShijian1() {
                return shijian1;
            }

            public void setShijian1(String shijian1) {
                this.shijian1 = shijian1;
            }

            public String getShijian2() {
                return shijian2;
            }

            public void setShijian2(String shijian2) {
                this.shijian2 = shijian2;
            }

            public String getJine() {
                return jine;
            }

            public void setJine(String jine) {
                this.jine = jine;
            }

            public String getQudao() {
                return qudao;
            }

            public void setQudao(String qudao) {
                this.qudao = qudao;
            }
        }
    }
}
