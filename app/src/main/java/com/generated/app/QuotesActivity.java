package com.generated.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuotesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setPadding(24, 24, 24, 24);
        rootLayout.setBackgroundColor(0xFF0D3B2E);

        ListView listView = new ListView(this);
        listView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        rootLayout.addView(listView);

        setContentView(rootLayout);

        List<Quote> quotes = QuoteRepository.getAllQuotes();
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (Quote q : quotes) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("text", "„" + q.getText() + "”");
            map.put("source", "— " + q.getSource() + " (" + q.getType() + ")");
            data.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                android.R.layout.simple_list_item_2,
                new String[]{"text", "source"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        listView.setAdapter(adapter);
    }
}
