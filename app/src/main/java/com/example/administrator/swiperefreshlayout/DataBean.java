package com.example.administrator.swiperefreshlayout;

import java.util.List;

/**
 * Created by Administrator on 2017/1/7.
 */

public class DataBean {

    private List<NewslistBean> newslist;


    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "newslist=" + newslist +
                '}';
    }
}
