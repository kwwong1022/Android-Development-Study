package udemy.course.toptendownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: starting AsyncTask");
        DownloadData downloadData = new DownloadData();
        downloadData.execute("URL goes here");
        Log.d(TAG, "onCreate: done");
    }

    private class DownloadData extends AsyncTask<String, Void, String> {
        private static final String TAG = "DownloadData";

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: starts with " + strings[0]);
            //String rssFeed = downloadXML(strings[0]);
            //if (rssFeed == null) {
            //    Log.e(TAG, "doInBackground: Error downloading");
            //}
            //return rssFeed;
            return "";
        };

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: parameter is: " + s);
            super.onPostExecute(s);
        }
    }
}