package vee.apps.todolistrealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vee.apps.todolistrealm.adapter.TodoAdapter;
import vee.apps.todolistrealm.db.TodoHelper;
import vee.apps.todolistrealm.db.TodoItem;

public class MainActivity extends AppCompatActivity {

    private ListView lvItem;
    private TodoHelper helper;
    private ArrayList<TodoItem> list;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItem = (ListView)findViewById(R.id.lv_item);
        helper = new TodoHelper(this);
        list = new ArrayList<>();

        loadData();

    }

    private void loadData() {

        list = helper.getAllItems();
        if(list!=null && !list.isEmpty()){
            adapter = new TodoAdapter(this,list);
            lvItem.setAdapter(adapter);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(list.size()>0){
            loadData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add){
            Intent intent = new Intent(MainActivity.this,
                    AddEditTaskActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
