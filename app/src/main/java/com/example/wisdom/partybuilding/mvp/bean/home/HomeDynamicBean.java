package com.example.wisdom.partybuilding.mvp.bean.home;

import java.util.List;

public class HomeDynamicBean {


    private List<CarouselmapBean> carouselmap;

    public List<CarouselmapBean> getCarouselmap() {
        return carouselmap;
    }

    public void setCarouselmap(List<CarouselmapBean> carouselmap) {
        this.carouselmap = carouselmap;
    }

    public static class CarouselmapBean {
        /**
         * functionname : 党委新闻
         */

        private String functionname;

        public String getFunctionname() {
            return functionname;
        }

        public void setFunctionname(String functionname) {
            this.functionname = functionname;
        }
    }
}
