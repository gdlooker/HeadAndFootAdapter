package com.example.administrator.swiperefreshlayout;

/**
 * Created by Administrator on 2017/1/7.
 */

public class NewsBeanData {

    /**
     * code : 200
     * message : success
     * data : {"newslist":[{"ctime":"2016-12-30 13:37","title":"杜淳电影电视综艺齐发力 升级做老板成制片人","description":"腾讯明星","picUrl":"http://img1.gtimg.com/18/1863/186353/18635334_small.jpg","url":"http://www.baidu.com"},{"ctime":"2016-12-30 13:37","title":"美女","description":"腾讯明星","picUrl":"https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=64175659840a19d8d403830503fb82c9/e7cd7b899e510fb3a78c787fdd33c895d0430c44.jpg","url":"https://www.hao123.com"}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public NewsBeanData(){
        data=new DataBean();
    }
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "NewsBeanData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
