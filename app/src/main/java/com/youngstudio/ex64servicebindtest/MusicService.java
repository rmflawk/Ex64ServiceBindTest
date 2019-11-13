package com.youngstudio.ex64servicebindtest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;


public class MusicService extends Service {

    MediaPlayer mp;
    //MainActivity mainActivity;

//    public MusicService(MainActivity mainActivity) {
//        this.mainActivity = mainActivity;
//    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    //bindService()를 실행하몀ㄴ 발동하는 메소드
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder(); // 외교관 리턴 - MainActivity에서 사용가능
    }

    //MainActivity와 bind 되었을 때
    //넘어갈 객체 클래스( 외교관역할 )
    class ServiceBinder extends Binder {
        //이 MusicService객체의 참조값을 리턴하는 기능 메소드
        MusicService getService(){
            return MusicService.this;
        }
    }

    public void playMusic(){
        if(mp==null){
            mp= MediaPlayer.create(this,R.raw.kalimba);
            mp .setLooping(true);//음악이 끝나면?
            mp.setVolume(1.0f,1.0f);
        }

        //mp.seekTo(150); 150초 위치로
        if( !mp.isPlaying() )mp.start();//start & resume
    }

    public void pauseMusic(){
        if( mp.isPlaying() && mp!=null ) mp.pause();
    }

    public void stopMusic(){
        if( mp!=null ) {
            mp.stop();
            mp.release();// 메모리 없애기
            mp=null;
        }
    }


}
























