package com.example.wisdom.partybuilding.mvp.bean.home;

import java.util.List;

public class PartyknowledgeBean {


    private List<CarouselmapBean> carouselmap;

    public List<CarouselmapBean> getCarouselmap() {
        return carouselmap;
    }

    public void setCarouselmap(List<CarouselmapBean> carouselmap) {
        this.carouselmap = carouselmap;
    }

    public static class CarouselmapBean {
        /**
         * infoId : 10000008261124
         * releaseDate : 1509638400000
         * contents : 中国共产党章程（中国共产党第十九次全国代表大会部分修改，2017年10月24日通过）
         * bt : 中国共产党章程（2017年10月24日通过）
         * orgname : 中共中国航天科工集团公司总部委员会
         * fileupload : 1
         * releasetime : 2017-11-03
         * file : [{"fileid":"1","filename":"教育学习文档.docx","fileurl":"http://192.168.1.199:9998/FHBE/attachFiles/file/1.docx","type":"docx"}]
         */

        private long infoId;
        private long releaseDate;
        private String contents;
        private String bt;
        private String orgname;
        private String fileupload;
        private String releasetime;
        private List<FileBean> file;

        public long getInfoId() {
            return infoId;
        }

        public void setInfoId(long infoId) {
            this.infoId = infoId;
        }

        public long getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(long releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getBt() {
            return bt;
        }

        public void setBt(String bt) {
            this.bt = bt;
        }

        public String getOrgname() {
            return orgname;
        }

        public void setOrgname(String orgname) {
            this.orgname = orgname;
        }

        public String getFileupload() {
            return fileupload;
        }

        public void setFileupload(String fileupload) {
            this.fileupload = fileupload;
        }

        public String getReleasetime() {
            return releasetime;
        }

        public void setReleasetime(String releasetime) {
            this.releasetime = releasetime;
        }

        public List<FileBean> getFile() {
            return file;
        }

        public void setFile(List<FileBean> file) {
            this.file = file;
        }

        public static class FileBean {
            /**
             * fileid : 1
             * filename : 教育学习文档.docx
             * fileurl : http://192.168.1.199:9998/FHBE/attachFiles/file/1.docx
             * type : docx
             */

            private String fileid;
            private String filename;
            private String fileurl;
            private String type;

            public String getFileid() {
                return fileid;
            }

            public void setFileid(String fileid) {
                this.fileid = fileid;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getFileurl() {
                return fileurl;
            }

            public void setFileurl(String fileurl) {
                this.fileurl = fileurl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
