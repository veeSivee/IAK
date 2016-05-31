package vee.apps.todolistrealm;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vee.apps.todolistrealm.db.TodoHelper;
import vee.apps.todolistrealm.db.TodoItem;

public class AddEditTaskActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtTask,edtDescription;
    private Button btnSubmit;
    private TodoHelper todoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        getSupportActionBar().setTitle("Add New Task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtDescription = (EditText)findViewById(R.id.edit_description);
        edtTask = (EditText)findViewById(R.id.edit_task);
        btnSubmit = (Button)findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(this);

        todoHelper = new TodoHelper(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_submit){
            String task = edtTask.getText().toString().trim();
            String description = edtDescription.getText().toString().trim();

            if(TextUtils.isEmpty(task)||TextUtils.isEmpty(description)){
                Toast.makeText(this,"All fields are mandatory",Toast.LENGTH_LONG).show();
            }else{
                TodoItem item = new TodoItem();
                item.setId(System.currentTimeMillis());
                item.setTask(task);
                item.setDescription(description);
                todoHelper.create(item.getId(), item.getTask(),item.getDescription());
                Toast.makeText(this, "One task added", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
