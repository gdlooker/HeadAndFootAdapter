package com.example.administrator.swiperefreshlayout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class MainActivity extends FragmentActivity {

    private Context context = null;
    public static final String TAG = "chent";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ChentHeaderFooterAdapter mAdapter;

    private View headView;//头部视图
    private View footView;//底部视图
    private LayoutInflater mInflater;
    private LinearLayoutManager linearLayoutManager;
    private List<NewslistBean> newslistBeanList;
    private DataBean dataBean;
    private NewsBeanData newsBeanData;
    private String fileName = "newsdata.txt";

    private TextView tvMore = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("test", "onCreate");
        setContentView(R.layout.activity_main);
        context = this;
        mInflater = getLayoutInflater();
        initView();//初始化view
    }

    //初始化view
    private void initView() {
        tvMore = (TextView) findViewById(R.id.tvMore);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sRefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_blue_dark,
                android.R.color.holo_red_dark,
                android.R.color.black
        );
        mSwipeRefreshLayout.setDistanceToTriggerSync(150);//设置下拉 150dp的时候才有反应
        //设置进度条的背景颜色
        // mSwipeRefreshLayout.setProgressBackgroundColor(R.color.colorAccent);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorPrimaryDark);
        // mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorPrimaryDark));

        //设置进度条的大小
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        //下拉刷新操作
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //添加数据
                        mSwipeRefreshLayout.setRefreshing(false);

                    }
                }, 3000);
            }
        });

        mAdapter = new ChentHeaderFooterAdapter(context);
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutFrozen(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        ///加载头部布局
        for (int i = 0; i < 3; i++) {
            // View.inflate()
            headView = mInflater.inflate(R.layout.headview_recycleview, mRecyclerView, false);
            footView = mInflater.inflate(R.layout.footview_recycleview, mRecyclerView, false);
            //
            SimpleDraweeView headImageView = (SimpleDraweeView) headView.findViewById(R.id.sdView);
            SimpleDraweeView footImageView = (SimpleDraweeView) footView.findViewById(R.id.sdView);
            DraweeController controller1 = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse("http://7mno4h.com2.z0.glb.qiniucdn.com/%E8%8F%9C%E9%B8%9F%E6%96%B0%E9%97%BB.jpg"))
                    .build();

            DraweeController controller2 = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse("http://7mno4h.com2.z0.glb.qiniucdn.com/cfafa69ae71b4c3aa491778301e1f1cc.jpg"))
                    .build();
            //视图绑定
            //img  http://7mno4h.com2.z0.glb.qiniucdn.com/cfafa69ae71b4c3aa491778301e1f1cc.jpg
            headImageView.setController(controller1);
            footImageView.setController(controller2);
            mAdapter.addHeadView(headView);
            mAdapter.addFootView(footView);
            //mAdapter.getItemViewType()
        }
        mRecyclerView.setAdapter(mAdapter);
        //首先进来的时候默认加载的数据
        loadDefaultData();
        //添加滑动监听
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                int visibleItemCount = linearLayoutManager.getChildCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = linearLayoutManager.getItemCount();
                Log.i(TAG, "first=" + firstVisibleItem + "\nlastItem=" + lastVisibleItem + ",total=" + linearLayoutManager.getItemCount());

                Log.i(TAG, "count1=" + visibleItemCount + "\ncount2=" + totalItemCount);
                //做判断执行刷新操作
                if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                    //加载数据
                    Log.i("chent", "上拉加载");

                    tvMore.setVisibility(View.VISIBLE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvMore.setVisibility(View.GONE);
                        }
                    },3000);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


    }

    /**
     * 一进入列表ui 的Activity的时候就加载数据
     */
    private void loadDefaultData() {

        //通过http请求发送数据 进行json解析
        String url = "http://192.168.36.67:80/php/appapi/HttpUtils.php";
        if (HttpNetUtils.isNetworkConnected(context)) {
            OkHttpUtils.get()
                    .url(url)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                            Log.i(TAG, "error");
                            //如果是失败的就直接读取数据就ok了
                            String cacheData = FileUtilsMethod.readFromFile(MainActivity.this, fileName);
                            parseJson(cacheData);
                        }

                        @Override
                        public void onResponse(String response, int id) {

                            Log.i(TAG, "response=" + response);
                            //一般这里数据还要做缓存
                            FileUtilsMethod.saveDataToFile(MainActivity.this, response, fileName);
                            //json解析
                            parseJson(response);
                        }
                    });
        } else {
            String cacheData = FileUtilsMethod.readFromFile(MainActivity.this, fileName);
            parseJson(cacheData);
        }

    }

    /**
     * json数据解析
     *
     * @param response
     */
    private void parseJson(final String response) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                if (TextUtils.isEmpty(response)) {
                    System.out.print(response);
                    return;
                }
                newsBeanData = gson.fromJson(response, NewsBeanData.class);
                Log.i("chenzb", "newdata=" + newsBeanData.toString());
                //json对象
                dataBean = newsBeanData.getData();
                newslistBeanList = dataBean.getNewslist();
                //防止空指针
                if (newslistBeanList == null) {
                    return;
                } else if (newslistBeanList.size() == 0) {
                    return;
                }
                //适配器加载数据
                mAdapter.addList(newslistBeanList);
            }
        });
    }

    Handler handler = new Handler();
}
