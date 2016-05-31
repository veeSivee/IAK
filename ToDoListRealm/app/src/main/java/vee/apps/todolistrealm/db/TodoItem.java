package vee.apps.todolistrealm.db;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by binaryvi on 14/05/2016.
 */
public class TodoItem extends RealmObject implements Parcelable {

    private long id;
    private String task;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.task);
        dest.writeString(this.description);
    }

    public TodoItem() {
    }

    protected TodoItem(Parcel in) {
        this.id = in.readLong();
        this.task = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<TodoItem> CREATOR = new Parcelable.Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel source) {
            return new TodoItem(source);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };
}
