package com.example.administrator.swiperefreshlayout;

import java.util.List;

/**
 * Created by Administrator on 2016/12/31.
 */

public class NewsItemBean {


    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-12-30 13:37","title":"杜淳电影电视综艺齐发力 升级做老板成制片人","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1863/186353/18635334_small.jpg","url":"http://ent.qq.com/a/20161230/020984.htm"},{"ctime":"2016-12-29 14:42","title":"文咏珊忙拍戏无暇庆生 实力打拼全年无休","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1862/186250/18625017_small.jpg","url":"http://ent.qq.com/a/20161229/024796.htm"},{"ctime":"2016-12-29 14:48","title":"张艺兴微博粉丝破2000万 晒视频送福利","description":"腾讯明星","picUrl":"http://img1.gtimg.com/ent/pics/hv1/211/212/2173/141353596_small.jpg","url":"http://ent.qq.com/a/20161229/025012.htm"},{"ctime":"2016-12-26 06:22","title":"孙志浩大婚梧桐妹认新妈 前妻贾静雯隔空秀恩爱","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1858/185831/18583108_small.jpg","url":"http://ent.qq.com/a/20161226/002052.htm"},{"ctime":"2016-12-26 06:52","title":"林心如首度公开秀恩爱 晒夫妇合照为霍建华庆生","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1858/185831/18583152_small.jpg","url":"http://ent.qq.com/a/20161226/002343.htm"},{"ctime":"2016-12-25 00:02","title":"萧淑慎与小15岁男友曝吻照 曾否认做小三","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/000067.htm"},{"ctime":"2016-12-25 00:02","title":"42岁陈德容蕾丝裙肉感十足 昔日女神依旧优雅","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/000072.htm"},{"ctime":"2016-12-25 00:02","title":"【存照】虽未领证，他们的爱情足以惊世骇俗","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/000075.htm"},{"ctime":"2016-12-25 00:33","title":"查杰熊梓淇沪上欢度圣诞 《刺客2》仍组CP","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/000332.htm"},{"ctime":"2016-12-25 06:41","title":"圣诞节到了，美人鱼林允扮性感小鹿","description":"腾讯明星","picUrl":"","url":"http://ent.qq.com/a/20161225/001771.htm"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2016-12-30 13:37
         * title : 杜淳电影电视综艺齐发力 升级做老板成制片人
         * description : 腾讯明星
         * picUrl : http://img1.gtimg.com/18/1863/186353/18635334_small.jpg
         * url : http://ent.qq.com/a/20161230/020984.htm
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
