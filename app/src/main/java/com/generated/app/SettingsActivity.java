package com.generated.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setPadding(32, 32, 32, 32);
        rootLayout.setBackgroundColor(0xFF0D3B2E);

        TextView tvTitle = new TextView(this);
        tvTitle.setText(R.string.settings);
        tvTitle.setTextColor(0xFFD4AF37);
        tvTitle.setTextSize(22);
        tvTitle.setPadding(0, 0, 0, 32);
        rootLayout.addView(tvTitle);

        final SharedPreferences prefs = getSharedPreferences("ZochrimPrefs", MODE_PRIVATE);

        Button btnTheme1 = new Button(this);
        btnTheme1.setText(R.string.theme_traditional);
        btnTheme1.setBackgroundResource(R.drawable.bg_button);
        btnTheme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().putString("theme", "traditional").apply();
                Toast.makeText(SettingsActivity.this, "העיצוב נשמר", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 120);
        p.setMargins(0, 0, 0, 20);
        btnTheme1.setLayoutParams(p);
        rootLayout.addView(btnTheme1);

        Button btnTheme2 = new Button(this);
        btnTheme2.setText(R.string.theme_dark_gold);
        btnTheme2.setBackgroundResource(R.drawable.bg_button);
        btnTheme2.setLayoutParams(p);
        btnTheme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().putString("theme", "dark_gold").apply();
                Toast.makeText(SettingsActivity.this, "העיצוב נשמר", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        rootLayout.addView(btnTheme2);

        TextView tvOffline = new TextView(this);
        tvOffline.setText(R.string.offline_mode_active);
        tvOffline.setTextColor(0xFFFFFFFF);
        tvOffline.setPadding(0, 40, 0, 0);
        rootLayout.addView(tvOffline);

        setContentView(rootLayout);
    }
}
