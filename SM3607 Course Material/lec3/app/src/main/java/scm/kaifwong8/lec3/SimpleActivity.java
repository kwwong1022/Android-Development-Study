package scm.kaifwong8.lec3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Intent i = getIntent();
        TextView email = findViewById(R.id.text_to);
        TextView subject = findViewById(R.id.text_subject);
        TextView msg = findViewById(R.id.text_message);
        email.setText(i.getStringExtra(MainActivity.TAG1));
        subject.setText(i.getStringExtra(MainActivity.TAG2));
        msg.setText(i.getStringExtra(MainActivity.TAG3));
    }
}