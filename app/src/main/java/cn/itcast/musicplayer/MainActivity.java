package cn.itcast.musicplayer;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    private EditText path;
    private Intent intent;
    private myConn conn;
    MusicService.MyBinder binder;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);
        conn = new myConn();
        intent = new Intent(this, MusicService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }
    private class myConn implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MusicService.MyBinder) service;
        }
        public void onServiceDisconnected(ComponentName name) {
        }
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                    binder.play(MainActivity.this);
                break;
            case R.id.btn_pause:
                binder.pause();
                break;
            default:
                break;
        }
    }
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}
