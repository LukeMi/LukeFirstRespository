package com.lukemi.android.tutorial.util;

import android.util.Log;

import com.lukemi.android.common.util.Logcat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by android on 2017/5/11.
 */

public class UploadImage {
    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10 * 10000000; //超时时间
    private static final String CHARSET = "utf-8"; //设置编码
    private static final String BOUNDARY = "FlPm4LpSXsE"; //UUID.randomUUID().toString(); //边界标识 随机生成 String PREFIX = "--" , LINE_END = "\r\n";
    private static final String PREFIX = "--";
    private static final String LINE_END = "\n\r";
    private static final String CONTENT_TYPE = "multipart/form-data"; //内容类型

    /**
     * android上传文件到服务器
     *
     * @param requestStr 需要上传的文件
     * @param requestURL 请求的rul
     * @return 返回响应的内容
     */
    public static String uploadFile(String requestStr, String requestURL) {
        String result = "";
        try {
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); //允许输入流
            conn.setDoOutput(true); //允许输出流
            conn.setUseCaches(false); //不允许使用缓存
            conn.setRequestMethod("POST"); //请求方式
            conn.setRequestProperty("Charset", CHARSET);//设置编码
            //头信息
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            if (requestStr != null) {
                /** * 当文件不为空，把文件包装并且上传 */
                OutputStream outputSteam = conn.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputSteam);

//                String[] params = {"\"ownerId\"","\"docName\"","\"docType\"","\"sessionKey\"","\"sig\""};
//                String[] values = {"1410065922",file.getName(),"jpg","dfbe0e1686656d5a0c8de11347f93bb6","e70cff74f433ded54b014e7402cf094a"};
                String[] params = {"avatarfile"};
                String[] values = {"avatarfile.jpg"};
                //添加docName,docType,sessionKey,sig参数
                for (int i = 0; i < params.length; i++) {
                    //添加分割边界
                    StringBuffer sb = new StringBuffer();
                    sb.append(PREFIX);
                    sb.append(BOUNDARY);
                    sb.append(LINE_END);

                    sb.append("Content-Disposition: form-data; name=" + params[i] + LINE_END);
                    sb.append(LINE_END);
                    sb.append(values[i]);
                    sb.append(LINE_END);
                    dos.write(sb.toString().getBytes());
                }

                //file内容
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);

                sb.append("Content-Disposition: form-data; name=\"data\";filename=" + "\"" + "xh.jpg" + "\"" + LINE_END);
                sb.append("Content-Type: image/jpg" + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                //读取文件的内容
//                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
             /*   while((len=is.read(bytes))!=-1)
                {
                    dos.write(bytes, 0, len);
                }*/
                dos.write(requestStr.getBytes());
//                is.close();
                //写入文件二进制内容
                dos.write(LINE_END.getBytes());
                //写入end data
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码 200=成功
                 * 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                Log.e(TAG, "response code:" + res);
                if (res == 200) {
                    String oneLine;
                    StringBuffer response = new StringBuffer();
                    BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((oneLine = input.readLine()) != null) {
                        response.append(oneLine);
                    }
                    result = response.toString();
                    Logcat.log(result);
                    return response.toString();
                } else {
                    result = res + "";
                    Logcat.log(result);
                    return result;
                }
            } else {
                Logcat.log("file not found");
                return "file not found";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Logcat.log("failed");
            return "failed";
        } catch (IOException e) {
            e.printStackTrace();
            Logcat.log("failed");
            return "failed";
        }
    }

}
