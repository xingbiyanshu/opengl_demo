package com.sissi.opengldemo;

import android.app.ActivityManager;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView glSurfaceView;
    private GLSurfaceView.Renderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        glSurfaceView = new GLSurfaceView(this);
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        boolean  supportsEs2 = activityManager.getDeviceConfigurationInfo().reqGlEsVersion >= 0x20000;
        if (supportsEs2){
            glSurfaceView.setEGLContextClientVersion(2);
            renderer = new MyRenderer(this);
            glSurfaceView.setRenderer(renderer);
        }else{
            Toast.makeText(this, "this device does not support OpenGL ES 2.0", Toast.LENGTH_LONG).show();
            return;
        }

        setContentView(glSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != renderer){
            glSurfaceView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != renderer){
            glSurfaceView.onPause();
        }
    }

}
