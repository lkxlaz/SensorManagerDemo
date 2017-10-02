package mad.com.sensormanagerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.textV);
        startPull();
    }

    public void startPull() {
        new SenseFromAllPullSensorsTask(this, mTv)
        {
            @Override
            protected void onPostExecute(Void result)
            {
                super.onPostExecute(result);
                //startEnvironment();
                startPush();
            }
        }.execute();
    }

    private void startEnvironment()
    {
        new SenseFromAllEnvSensorsTask(this)
        {
            @Override
            protected void onPostExecute(Void result)
            {
                super.onPostExecute(result);
                startPush();
            }
        }.execute();
    }

    private void startPush()
    {
        new SenseFromAllPushSensorsTask(this).execute();
    }
}
