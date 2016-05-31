package vee.apps.todolistrealm.db;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by binaryvi on 14/05/2016.
 */
public class TodoHelper {

    Realm realm;
    Context context;

    public TodoHelper(Context context){
        this.context = context;
        realm = Realm.getInstance(
                new RealmConfiguration.Builder(context)
                        .name("todolist.realm")
                        .build());
    }

    public void create(long id, String task, String description){
        realm.beginTransaction();
        TodoItem item = realm.createObject(TodoItem.class);
        item.setId(id);
        item.setTask(task);
        item.setDescription(description);
        realm.commitTransaction();
    }

    public ArrayList<TodoItem> getAllItems(){
        ArrayList<TodoItem> list = null;
        RealmResults<TodoItem> results = realm.where(TodoItem.class).findAll();
        if(results.size()>0){
            list = new ArrayList<>();
            for(TodoItem item : results){
                list.add(item);
            }
        }
        return  list;
    }

    public void deleteItem(long id){
        realm.beginTransaction();
        RealmResults<TodoItem> items = realm.where(TodoItem.class)
                .equalTo("id",id).findAll();
        TodoItem item = items.get(0);
        item.deleteFromRealm();
        realm.commitTransaction();
    }

    public void update(long id, String task, String description){
        realm.beginTransaction();
        TodoItem item = realm.where(TodoItem.class)
                .equalTo("id", id).findFirst();
        item.setTask(task);
        item.setDescription(description);
        realm.commitTransaction();
    }
}
