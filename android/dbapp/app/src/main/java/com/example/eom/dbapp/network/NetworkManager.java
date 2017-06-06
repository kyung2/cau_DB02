package com.example.eom.dbapp.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

/**
 * Created by Administrator on 2016-10-10.
 * 네트워크 합니다
 */
public class NetworkManager {
//    public static String serverIP = "http://dldnjstp20_gogo.goorm.io";
     static String serverIP = "http://sswjjang.iptime.org:8000";
    private final static int TIMEOUT = 10000;

    static void setupConnection(String postParam,HttpURLConnection urlConnection)throws IOException {
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);

        try {
            urlConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("Content-Length", "" + Integer.toString(postParam.getBytes().length));
        urlConnection.setUseCaches(false);
        urlConnection.setConnectTimeout(TIMEOUT);
        urlConnection.setReadTimeout(TIMEOUT);
        DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
        dataOutputStream.writeBytes(postParam);
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }

}