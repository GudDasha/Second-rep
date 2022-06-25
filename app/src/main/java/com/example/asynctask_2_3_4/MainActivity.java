package com.example.asynctask_2_3_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    TextView text;
    Button btnstart;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.txt);
        btnstart = (Button) findViewById(R.id.btn_start);
        progress = (ProgressBar) findViewById(R.id.progressbar);
    }

    public void onClick(View view) {
        OurTask ourTask = new OurTask();
        ourTask.execute("cat1.jpg", "cat2.jgp", "cat3.jpg", "cat4.jpg");
    }
    class OurTask extends AsyncTask<String,Integer,Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            text.setText("Даша и Алина пошли в магазин");
            progress.setVisibility(View.VISIBLE);
            btnstart.setVisibility(View.INVISIBLE);
        }
        @Override
        protected Integer doInBackground(String... urls) {
            try{
                int counter = 0;

                for(int i = 0; i < 14; i++){
                    getTime(counter);
                    publishProgress(++counter);
                }
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return 2012;
        }
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            text.setText("Даша и Алина дошли до магазина " + "\nРезультат: "+result);
            progress.setVisibility(View.INVISIBLE);
            btnstart.setVisibility(View.VISIBLE);
            progress.setProgress(0);
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            text.setText("Идём: " + values[0]);
        }
        private void getTime(int floor) throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
        }
    }
}