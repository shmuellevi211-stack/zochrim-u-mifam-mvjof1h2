package com.generated.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountersListActivity extends Activity {

    private DatabaseHelper dbHelper;
    private ListView listView;
    private List<CounterModel> counterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setPadding(24, 24, 24, 24);
        rootLayout.setBackgroundColor(0xFF0D3B2E);

        Button btnAdd = new Button(this);
        btnAdd.setText(R.string.add_counter);
        btnAdd.setTextColor(0xFF0D3B2E);
        btnAdd.setBackgroundResource(R.drawable.bg_button);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 120);
        btnParams.setMargins(0, 0, 0, 24);
        btnAdd.setLayoutParams(btnParams);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCounterDialog();
            }
        });
        rootLayout.addView(btnAdd);

        listView = new ListView(this);
        LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        listView.setLayoutParams(listParams);
        rootLayout.addView(listView);

        setContentView(rootLayout);

        dbHelper = new DatabaseHelper(this);
        loadCounters();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final CounterModel selected = counterList.get(position);
                if (selected.isHistorical()) {
                    Toast.makeText(CountersListActivity.this, "לא ניתן למחוק מונה היסטורי ראשי", Toast.LENGTH_SHORT).show();
                    return true;
                }
                new AlertDialog.Builder(CountersListActivity.this)
                        .setTitle(R.string.delete)
                        .setMessage("האם למחוק מונה זה?")
                        .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.deleteCounter(selected.getId());
                                loadCounters();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
                return true;
            }
        });
    }

    private void loadCounters() {
        counterList = dbHelper.getAllCounters();
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (CounterModel c : counterList) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", c.getName());
            map.put("date", "התחלה: " + c.getStartDate() + (c.isHistorical() ? " (היסטורי)" : " (אישי)"));
            data.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "date"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        listView.setAdapter(adapter);
    }

    private void showAddCounterDialog() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32, 32, 32, 32);

        final EditText inputName = new EditText(this);
        inputName.setHint(R.string.counter_name);
        layout.addView(inputName);

        final EditText inputDate = new EditText(this);
        inputDate.setHint(R.string.start_date);
        inputDate.setText("2024-01-01");
        layout.addView(inputDate);

        new AlertDialog.Builder(this)
                .setTitle(R.string.add_counter)
                .setView(layout)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = inputName.getText().toString().trim();
                        String date = inputDate.getText().toString().trim();
                        if (name.isEmpty()) {
                            Toast.makeText(CountersListActivity.this, R.string.error_empty_name, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        CounterModel newC = new CounterModel(0, name, date, false);
                        dbHelper.addCounter(newC);
                        loadCounters();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
}
