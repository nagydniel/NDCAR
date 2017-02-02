package com.razorhost.ndcar;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class OldMainActivity extends Activity {

    private static final float EPSILON = 0.000001f;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;

    private TextView text;
    private TextView text2;
    private TextView text3;

    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;

    private static SensorManager mySensorManager;
    private boolean sersorrunning;

    private static final String TAG = "MainActivity";
    protected UDP_Client Client;

    double X;
    double Y;
    double Z;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b1.setOnTouchListener(goForward);
        b2.setOnTouchListener(goBackward);

        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b3.setOnTouchListener(goLeft);
        b4.setOnTouchListener(goRight);

        b5 = (Button) findViewById(R.id.b5);
        b5.setOnClickListener(Release);

        mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> mySensors = mySensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);


            mySensorManager.registerListener(mySensorEventListener, mySensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
            sersorrunning = true;
            Toast.makeText(this, "Start ORIENTATION Sensor", Toast.LENGTH_LONG).show();


    }

    boolean pressedLeft = false;
    boolean pressedRight = false;
    boolean pressedUp = false;
    boolean pressedDown = false;

    class SendGoForwardTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Client = new UDP_Client();
            Client.Message = "FWFW";
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (pressedUp) {
                Client.SendUDP();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {

                }
            }
            return null;
        }
    }

    class SendGoBackTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Client = new UDP_Client();
            Client.Message = "BKBK";
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (pressedDown) {
                Client.SendUDP();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {

                }
            }
            return null;
        }
    }


    class SendTurnLeftTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Client = new UDP_Client();
            Client.Message = "LELE";
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (pressedLeft) {
                Client.SendUDP();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {

                }
            }
            return null;
        }
    }

    class SendTurnLeftTaskSlow extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Client = new UDP_Client();
            Client.Message = "LELE";
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (pressedLeft) {
                Client.SendUDP();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {

                }
            }
            return null;
        }
    }

    class SendTurnRightTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Client = new UDP_Client();
            Client.Message = "RIRI";
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (pressedRight) {
                Client.SendUDP();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {

                }
            }
            return null;
        }
    }
    class SendTurnUpRightTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Client = new UDP_Client();
            Client.Message = "ELJO";
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (pressedRight && pressedUp) {
                Client.SendUDP();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {

                }
            }
            return null;
        }
    }
    class SendTurnUpLeftTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Client = new UDP_Client();
            Client.Message = "ELBA";
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (pressedLeft && pressedUp) {
                Client.SendUDP();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {

                }
            }
            return null;
        }
    }
    class SendTurnDownRightTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Client = new UDP_Client();
            Client.Message = "HAJO";
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (pressedRight && pressedDown) {
                Client.SendUDP();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {

                }
            }
            return null;
        }
    }
    class SendTurnDownLeftTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Client = new UDP_Client();
            Client.Message = "HABA";
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (pressedLeft && pressedDown) {
                Client.SendUDP();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {

                }
            }
            return null;
        }
    }

    class SendTurnRightTaskSlow extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Client = new UDP_Client();
            Client.Message = "RIRI";
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (pressedRight) {
                Client.SendUDP();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {

                }
            }
            return null;
        }
    }

    View.OnTouchListener goForward = new View.OnTouchListener() {

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    if(pressedUp == false){
                        pressedUp = true;
                        Log.v(TAG, "up click");
                        new SendGoForwardTask().execute();

                    }
                    break;
                case MotionEvent.ACTION_UP:

                    pressedUp = false;

            }
            return true;
        }
    };

    View.OnTouchListener goBackward = new View.OnTouchListener() {

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    if(pressedDown == false){
                        pressedDown = true;
                        Log.v(TAG, "down click");
                        new SendGoBackTask().execute();

                    }
                    break;
                case MotionEvent.ACTION_UP:

                    pressedDown = false;

            }
            return true;
        }
    };

    View.OnTouchListener goLeft = new View.OnTouchListener() {

       public boolean onTouch(View v, MotionEvent event) {
           switch (event.getAction()) {

               case MotionEvent.ACTION_DOWN:

                   if(pressedLeft == false){
                       pressedLeft = true;
                       Log.v(TAG, "left click");
                       new SendTurnLeftTask().execute();

                   }
                   break;
               case MotionEvent.ACTION_UP:

                   pressedLeft = false;

           }
           return true;
       }
   };

    View.OnTouchListener goRight = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    if(pressedRight == false){
                        Log.v(TAG, "right click");
                        pressedRight = true;
                        new SendTurnRightTask().execute();
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    pressedRight = false;

            }
            return true;
        }
    };
    View.OnTouchListener goUpRight = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    if(!pressedRight && !pressedUp){
                        Log.v(TAG, "upright click");
                        pressedRight = true;
                        pressedUp = true;
                        new SendTurnUpRightTask().execute();
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    pressedRight = false;
                    pressedUp = false;

            }
            return true;
        }
    };
    View.OnTouchListener goUpLeft = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    if(!pressedLeft && !pressedUp){
                        Log.v(TAG, "upleft click");
                        pressedLeft = true;
                        pressedUp = true;
                        new SendTurnUpLeftTask().execute();
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    pressedLeft = false;
                    pressedUp = false;

            }
            return true;
        }
    };
    View.OnTouchListener goDownLeft = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    if(!pressedLeft && !pressedDown){
                        Log.v(TAG, "downleft click");
                        pressedLeft = true;
                        pressedDown = true;
                        new SendTurnDownLeftTask().execute();
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    pressedLeft = false;
                    pressedDown = false;

            }
            return true;
        }
    };
    View.OnTouchListener goDownRight = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    if(!pressedRight && !pressedDown){
                        Log.v(TAG, "downright click");
                        pressedRight = true;
                        pressedDown = true;
                        new SendTurnDownRightTask().execute();
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    pressedRight = false;
                    pressedDown = false;

            }
            return true;
        }
    };

    View.OnClickListener Release = new View.OnClickListener() {
        public void onClick(View v) {
            Log.v(TAG, "stop click");
            // new HttpAsyncTask().execute("http://192.168.4.1/mode=1");
            //new HTTPGet("http://192.168.4.1/mode=5");
            Client = new UDP_Client();
            Client.Message = "START";
            Client.SendUDP();
        }
    };


    private SensorEventListener mySensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // This timestep's delta rotation to be multiplied by the current rotation
            // after computing it from the gyro sample data.
            if (timestamp != 0) {
                final float dT = (event.timestamp - timestamp) * NS2S;
                // Axis of the rotation sample, not normalized yet.
                float axisX = event.values[0];
                float axisY = event.values[1];
                float axisZ = event.values[2];
                //text2.setText("X: "+axisX+", Y: "+axisY+", Z: "+axisZ);

                double k = 90/9.8;
                X = axisX * k; // this is a rough estimate of degrees, converted from gravity
                Y = axisY * k;
                Z = axisZ * k;

/*
                int r = 25;
                int l  = -25;

                if (Y>r) {
                    pressedRight = true;
                    new SendTurnRightTaskSlow().execute();

                } else if (Y<l) {
                    pressedLeft = true;
                    new SendTurnLeftTaskSlow().execute();

                } else {
                    pressedRight = false;
                    pressedLeft = false;
                }

                int r = 10;
                int l  = -10;

                if (Y<=r || Y>=l) {
                    pressedRight = false;
                    pressedLeft = false;
                } else if (Y>r) {
                    pressedRight = true;
                    new SendTurnRightTask().execute();
                } else if (Y<l) {
                    pressedLeft = true;
                    new SendTurnLeftTask().execute();
                }

*/

                DecimalFormat precision = new DecimalFormat("0.00");

                text.setText("X: "+precision.format(X)+", Y: "+precision.format(Y)+", Z: "+ precision.format(Z));


            }


            timestamp = event.timestamp;

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
            int r = 10;
            int l  = -10;

            if (Y>r) {
                pressedRight = true;
                new SendTurnRightTask().execute();
            } else if (Y<l) {
                pressedLeft = true;
                new SendTurnLeftTask().execute();
            } else {
                pressedRight = false;
                pressedLeft = false;
            }

        }
    };

}
