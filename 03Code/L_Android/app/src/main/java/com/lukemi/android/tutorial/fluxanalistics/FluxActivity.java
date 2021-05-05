package com.lukemi.android.tutorial.fluxanalistics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.util.DeviceUtil;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.util.HttpUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FluxActivity extends AppCompatActivity implements View.OnClickListener {

    String picUrl = "http://imgsrc.baidu.com/forum/pic/item/b785beb7d0a20cf42845940d76094b36adaf9916.jpg";
    private Button clickBTN;
    private TextView showFlux_ET;
    private ImageView pic_IV;
    private ExecutorService es = Executors.newFixedThreadPool(1);
    private TrafficStats ts;
    private int uid;
    /**
     * 统计的开机该app的流量
     */
    private long totalBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flux);
        initView();
        initData();
    }

    private void initData() {
        ts = new TrafficStats();
        uid = DeviceUtil.getUID(this);
    }

    private void initView() {
        clickBTN = findViewById(R.id.click);
        showFlux_ET = findViewById(R.id.showFlux);
        pic_IV = findViewById(R.id.pic);

        clickBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.click:
                loadPIC();
                break;
        }
    }

    /**
     * 下载图片斌并展示
     */
    private void loadPIC() {
        es.submit(() -> {
            //异步获取数据
            byte[] byteArray = HttpUtils.httpGetRequest_ByteArray(picUrl);
            final Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            Logcat.log("----FluxActivity----pic----bitmap---->" + "bitmap.getByteCount() = " + bitmap.getByteCount());
            //ui操作
            runOnUiThread(() -> {
                pic_IV.setImageBitmap(bitmap);
                long uidRxBytes = TrafficStats.getUidRxBytes(uid);
                long uidTxBytes = TrafficStats.getUidTxBytes(uid);
                totalBytes = uidRxBytes + uidTxBytes;
                showFlux_ET.setText("使用流量 = " + totalBytes + "byte" + "");
                Logcat.log("----FluxActivity----info---->" + "uid= " + uid
                        + "; Process.myUid()= " + Process.myUid());
            });
        });
    }
}
