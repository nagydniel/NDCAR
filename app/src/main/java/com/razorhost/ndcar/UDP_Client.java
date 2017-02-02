package com.razorhost.ndcar;

/**
 * Created by DAX95 on 2015.10.14..
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;


public class UDP_Client {

    private static final String TAG = "Udp_client";
    private AsyncTask<Void, Void, Void> async_cient;
    public String Message;

    @SuppressLint("NewApi")
    public void SendUDP()
    {
        async_cient = new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground(Void... params)
            {
                DatagramSocket ds = null;
                int server_port = 2015;

                try {
                    Log.v(TAG, Message);
                    InetAddress local = InetAddress.getByName("192.168.4.1");

                    ds = new DatagramSocket();
                    DatagramPacket dp;
                    dp = new DatagramPacket(Message.getBytes(), Message.length(), local, server_port);
                    ds.setBroadcast(true);
                   // ds.send(dp);
                    ds.setSoTimeout(0);
                   // ds.send(dp);
                    ds.send(dp); //TODO notice!
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if (ds != null)
                    {
                        ds.close();
                    }
                }
                return null;
            }

            protected void onPostExecute(Void result)
            {
                super.onPostExecute(result);
                //Log.v(TAG, "sent");
            }
        };

        if (Build.VERSION.SDK_INT >= 11) async_cient.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else async_cient.execute();

}
}
