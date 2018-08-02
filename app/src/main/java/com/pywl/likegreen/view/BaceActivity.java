package com.pywl.likegreen.view;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.pywl.likegreen.R;

/**
 * Created by theWind on 2018/7/31.
 */

public abstract class BaceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
//获取接口实例
        MovieService MovieService movieService = retrofit.create(MovieService.class);
        Subscription subscription = movieService.getTop250(0,20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieSubject>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(MovieSubject movieSubject) {
                        mMovieAdapter.setMovies(movieSubject.subjects);
                        mMovieAdapter.notifyDataSetChanged();
                    }
                });*/

    }
    public abstract void initView();
}
 /*   public interface MovieService {

        //获取豆瓣Top250 榜单
        @GET("top250")
        Observable<MovieSubject> getTop250(@Query("start") int start, @Query("count")int count);
    }
    public interface MovieService {
        //获取豆瓣Top250 榜单
        @FormUrlEncoded
        @POST("top250")
        Observable<MovieSubject> getTop250(@Field("start") int start, @Field("count") int count);
    }*/

