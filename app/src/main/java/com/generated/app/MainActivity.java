package com.generated.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    private TextView tvDaysCount;
    private TextView tvQuoteText;
    private TextView tvQuoteSource;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DatabaseHelper(this);

        // Root Layout with proper RTL and padding
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        rootLayout.setPadding(32, 32, 32, 32);
        rootLayout.setBackgroundColor(0xFF0D3B2E); // Rich traditional green

        // App Title / Subtitle
        TextView tvTitle = new TextView(this);
        tvTitle.setText(R.string.app_name);
        tvTitle.setTextColor(0xFFD4AF37); // Gold
        tvTitle.setTextSize(26);
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setPadding(0, 10, 0, 20);
        rootLayout.addView(tvTitle);

        // Days Banner Card Layout
        LinearLayout cardLayout = new LinearLayout(this);
        cardLayout.setOrientation(LinearLayout.VERTICAL);
        cardLayout.setGravity(Gravity.CENTER);
        cardLayout.setBackgroundResource(R.drawable.bg_card);
        cardLayout.setPadding(24, 32, 24, 32);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cardParams.setMargins(0, 10, 0, 24);
        cardLayout.setLayoutParams(cardParams);

        tvDaysCount = new TextView(this);
        tvDaysCount.setTextColor(0xFFD4AF37);
        tvDaysCount.setTextSize(40);
        tvDaysCount.setGravity(Gravity.CENTER);
        cardLayout.addView(tvDaysCount);

        TextView tvSubtitle = new TextView(this);
        tvSubtitle.setText(R.string.days_without_temple);
        tvSubtitle.setTextColor(0xFFFFFFFF);
        tvSubtitle.setTextSize(16);
        tvSubtitle.setGravity(Gravity.CENTER);
        cardLayout.addView(tvSubtitle);

        rootLayout.addView(cardLayout);

        // Daily Quote Card
        LinearLayout quoteCard = new LinearLayout(this);
        quoteCard.setOrientation(LinearLayout.VERTICAL);
        quoteCard.setBackgroundResource(R.drawable.bg_card);
        quoteCard.setPadding(24, 24, 24, 24);
        quoteCard.setLayoutParams(cardParams);

        tvQuoteText = new TextView(this);
        tvQuoteText.setTextColor(0xFFFFFFFF);
        tvQuoteText.setTextSize(16);
        tvQuoteText.setGravity(Gravity.CENTER);
        quoteCard.addView(tvQuoteText);

        tvQuoteSource = new TextView(this);
        tvQuoteSource.setTextColor(0xFFD4AF37);
        tvQuoteSource.setTextSize(14);
        tvQuoteSource.setGravity(Gravity.CENTER);
        tvQuoteSource.setPadding(0, 10, 0, 0);
        quoteCard.addView(tvQuoteSource);

        rootLayout.addView(quoteCard);

        // Navigation Buttons for features
        Button btnCounters = createMenuButton(getString(R.string.manage_counters));
        btnCounters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CountersListActivity.class));
            }
        });
        rootLayout.addView(btnCounters);

        Button btnQuotes = createMenuButton(getString(R.string.quotes_library));
        btnQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QuotesActivity.class));
            }
        });
        rootLayout.addView(btnQuotes);

        Button btnSettings = createMenuButton(getString(R.string.settings));
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
        rootLayout.addView(btnSettings);

        setContentView(rootLayout);
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private Button createMenuButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextColor(0xFF0D3B2E);
        button.setBackgroundResource(R.drawable.bg_button);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 110);
        params.setMargins(0, 10, 0, 10);
        button.setLayoutParams(params);
        return button;
    }

    private void updateUI() {
        List<CounterModel> counters = dbHelper.getAllCounters();
        if (!counters.isEmpty()) {
            CounterModel mainCounter = counters.get(0);
            long days = TimeCalculator.calculateDaysBetween(mainCounter.getStartDate());
            tvDaysCount.setText(String.format("%,d", days));
        } else {
            tvDaysCount.setText("0");
        }

        Quote quote = QuoteRepository.getRandomQuote();
        if (quote != null) {
            tvQuoteText.setText("„" + quote.getText() + "”");
            tvQuoteSource.setText("— " + quote.getSource());
        }
    }
}
