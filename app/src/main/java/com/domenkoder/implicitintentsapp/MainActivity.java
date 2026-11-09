package com.domenkoder.implicitintentsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareEditText = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        String urlStr = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(urlStr);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Can't handle this intent!", Toast.LENGTH_SHORT).show();
        }
    }

    public void openLocation(View view) {
        String locStr = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + Uri.encode(locStr));
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Can't handle this intent!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shareText(View view) {
        String txtStr = mShareEditText.getText().toString();
        String mimeType = "text/plain";
        new ShareCompat.IntentBuilder(this)
                .setType(mimeType)
                .setText(txtStr)
                .startChooser();
    }
}
