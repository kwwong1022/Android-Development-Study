package scm.kaifwong8.activitydemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_TO = "KEY1";
    public static final String KEY_SUBJECT = "KEY2";
    public static final String KEY_MESSAGE = "KEY3";

    private static final int PICK_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.btn_start_activity);
        start.setOnClickListener((v) -> {
            Intent i = new Intent(MainActivity.this, SimpleActivity.class);
            i.putExtra(KEY_TO, "hongbofu@cityu.edu.hk");
            i.putExtra(KEY_SUBJECT, "Hi");
            i.putExtra(KEY_MESSAGE, "SM3607");
            startActivity(i);
        });

        Button call = findViewById(R.id.callBtn);
        call.setOnClickListener((v) -> {
            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:34424302"));
            startActivity(i);
        });

        Button add = findViewById(R.id.addBtn);
        add.setOnClickListener((v) -> {
            Intent i = new Intent(ContactsContract.Intents.SHOW_OR_CREATE_CONTACT, Uri.parse("tel:34424302"));
            i.putExtra(ContactsContract.Intents.EXTRA_FORCE_CREATE, true);
            i.putExtra(ContactsContract.Intents.Insert.NAME, "Hongbo Fu");
            i.putExtra(ContactsContract.Intents.Insert.EMAIL, "hongbofu@city.edu.hk");
            startActivity(i);
        });

        Button pick = findViewById(R.id.btn_pick_contact);
        pick.setOnClickListener((v) -> {
            Intent pickContact = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
            pickContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            startActivityForResult(pickContact, PICK_CONTACT_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Get the URI that points to the selected contact
                Uri contactUri = data.getData();
                // We only need the NUMBER column, because there will be only one row in the result
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                // Perform the query on the contact to get the NUMBER column
                // We don't need a selection or sort order (there's only one result for the given URI)
                // CAUTION: The query() method should be called from a separate thread to avoid blocking
                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                // Consider using CursorLoader to perform the query.
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);

                // Do something with the phone number...
                ((TextView) findViewById(R.id.text_debug)).setText(number);
            }
        }
    }
}