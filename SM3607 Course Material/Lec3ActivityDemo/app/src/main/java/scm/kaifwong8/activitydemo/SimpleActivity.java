package scm.kaifwong8.activitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static scm.kaifwong8.activitydemo.MainActivity.KEY_MESSAGE;
import static scm.kaifwong8.activitydemo.MainActivity.KEY_SUBJECT;
import static scm.kaifwong8.activitydemo.MainActivity.KEY_TO;

public class SimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        TextView email = findViewById(R.id.text_to);
        TextView subject = findViewById(R.id.text_subject);
        TextView message = findViewById(R.id.text_message);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra(KEY_TO));
        subject.setText(intent.getStringExtra(KEY_SUBJECT));
        message.setText(intent.getStringExtra(KEY_MESSAGE));
    }
}