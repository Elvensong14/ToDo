package space.elvensong.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ToDoDatabaseHelper dbHelper;
    private List<ToDoItem> todoItems;
    private ToDoAdapter todoAdapter;
    private EditText todoEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new ToDoDatabaseHelper(this);
        todoItems = dbHelper.allItems();  // empty placeholder
        todoAdapter = new ToDoAdapter(this, R.layout.todo_item, todoItems);
        todoAdapter.setItemClickListener(item -> dbHelper.update(item));
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(todoAdapter);
    }

    public void mainAddBtnOnClick(View view) {
        todoEdit = findViewById(R.id.main_edit_text);
        String description = todoEdit.getText().toString();
        ToDoItem item = new ToDoItem(description);
        dbHelper.addItem(item);
        todoAdapter.add(item);
        todoAdapter.notifyDataSetChanged();  // Use for your Price Tracker app
    }

    public void mainRemoveBtnOnClick(View view) {
        dbHelper.deleteAll();
        todoAdapter.clear();
        todoAdapter.notifyDataSetChanged();
    }
}
