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
    private String urlActive;
    private String urlPrevious = "";
    private String urlNext = "";

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
        urlActive ="http://www.google.com";
    }

    public void searchButtonActivity(View v) {
        urlPrevious = urlActive;
        String s = searchbar.getText().toString();
        if (s.matches("")) {
            Toast toast = Toast.makeText(context, "Input address first.", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (s.matches("index.html")) {
            url = "file:///android_asset/index.html";
            web.loadUrl(url);
            urlActive = url;
        }
        else {
            url = "http://"+s;
            web.loadUrl(url);
            urlActive = url;
        }
    }

    public void refreshButtonActivity(View v) {
        web.loadUrl(urlActive);
    }

    public void previousButtonActivity (View v) {
        if (urlPrevious.matches("")) {
            Toast toast = Toast.makeText(context, "No previous site found.", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            web.loadUrl(urlPrevious);
            urlNext = urlActive;
        }
    }

    public void nextButtonActivity(View v) {
        if (urlNext.matches("")) {
            Toast toast = Toast.makeText(context, "Already on most recent website.", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            web.loadUrl(urlNext);
        }
    }

    /*public void executeShoutOut(View v) {
        web.evaluateJavascript("shoutOut()", null);
    }

    public void executeInitialize(View v) {
        web.evaluateJavascript("initialize()", null);
    }*/
}
