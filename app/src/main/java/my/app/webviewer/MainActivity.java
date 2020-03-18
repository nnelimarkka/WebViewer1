package my.app.webviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView web;
    private Context context;
    private EditText searchbar;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        searchbar = (EditText) findViewById(R.id.editText);
        web = (WebView) findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("http://www.google.com");
    }

    public void searchButtonActivity(View v) {
        String s = searchbar.getText().toString();
        if (s.matches("")) {
            Toast toast = Toast.makeText(context, "Input address first.", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            url = "http://"+s;
            web.loadUrl(url);
        }
    }
}
