package com.happycity.project.jobme.presenter;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Ha Truong on 12/1/2017.
 * This is a jobme
 * into the com.happycity.project.jobme.presenter
 */

public class CallRestApi implements RestService{
    @Override
    public String readHtmlFile(String s) {
        try
        {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(s);
            ResponseHandler<String> resHandler = new BasicResponseHandler();
            String page = httpClient.execute(httpGet, resHandler);
            return page;
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
            return "";
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "";
        }
    }

}
