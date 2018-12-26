package com.example.wisdom.partybuilding.mvp.bean;

import java.util.List;

public class MessageBean {
//   message

    /**
     * news : [{"id":10000011990118,"publisher":"","type":"通知公告","typename":"通知公告","titile":"平谷区\u201c街乡吹哨、部门报到\u201d的机制设计及经验总结，一直都是由区法制办在主要负责。","releasetime":1547049600000,"clickcount":null,"status":"1","content":"android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建","fileid":null,"image":null,"newssub":"","mfs":[{"fileid":"10000011990116","filename":"1.docx","fileurl":"http://192.168.1.199:8080/FHBE/attachFiles/file/10000011990116.docx","type":"docx"}],"firstimageurl":"","firstimgurl":null}]
     * pagetotal : 1
     * total : 1
     */

    private int pagetotal;
    private int total;
    private List<NewsBean> news;

    public int getPagetotal() {
        return pagetotal;
    }

    public void setPagetotal(int pagetotal) {
        this.pagetotal = pagetotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public static class NewsBean {
        /**
         * id : 10000011990118
         * publisher :
         * type : 通知公告
         * typename : 通知公告
         * titile : 平谷区“街乡吹哨、部门报到”的机制设计及经验总结，一直都是由区法制办在主要负责。
         * releasetime : 1547049600000
         * clickcount : null
         * status : 1
         * content : android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建android云党建
         * fileid : null
         * image : null
         * newssub :
         * mfs : [{"fileid":"10000011990116","filename":"1.docx","fileurl":"http://192.168.1.199:8080/FHBE/attachFiles/file/10000011990116.docx","type":"docx"}]
         * firstimageurl :
         * firstimgurl : null
         */

        private long id;
        private String publisher;
        private String type;
        private String typename;
        private String titile;
        private long releasetime;
        private Object clickcount;
        private String status;
        private String content;
        private Object fileid;
        private Object image;
        private String newssub;
        private String firstimageurl;
        private Object firstimgurl;
        private List<MfsBean> mfs;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getTitile() {
            return titile;
        }

        public void setTitile(String titile) {
            this.titile = titile;
        }

        public long getReleasetime() {
            return releasetime;
        }

        public void setReleasetime(long releasetime) {
            this.releasetime = releasetime;
        }

        public Object getClickcount() {
            return clickcount;
        }

        public void setClickcount(Object clickcount) {
            this.clickcount = clickcount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getFileid() {
            return fileid;
        }

        public void setFileid(Object fileid) {
            this.fileid = fileid;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public String getNewssub() {
            return newssub;
        }

        public void setNewssub(String newssub) {
            this.newssub = newssub;
        }

        public String getFirstimageurl() {
            return firstimageurl;
        }

        public void setFirstimageurl(String firstimageurl) {
            this.firstimageurl = firstimageurl;
        }

        public Object getFirstimgurl() {
            return firstimgurl;
        }

        public void setFirstimgurl(Object firstimgurl) {
            this.firstimgurl = firstimgurl;
        }

        public List<MfsBean> getMfs() {
            return mfs;
        }

        public void setMfs(List<MfsBean> mfs) {
            this.mfs = mfs;
        }

        public static class MfsBean {
            /**
             * fileid : 10000011990116
             * filename : 1.docx
             * fileurl : http://192.168.1.199:8080/FHBE/attachFiles/file/10000011990116.docx
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
