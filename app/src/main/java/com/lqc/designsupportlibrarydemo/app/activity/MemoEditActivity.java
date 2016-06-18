package com.lqc.designsupportlibrarydemo.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import com.lqc.designsupportlibrarydemo.app.R;
import com.lqc.designsupportlibrarydemo.app.data.bean.Todos;
import com.lqc.designsupportlibrarydemo.app.data.db.TodoDao;

/**
 * Created by albert on 16-6-19.
 */
public class MemoEditActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoedit);
        editText = (EditText)findViewById(R.id.memoedit_EditText);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            String text = editText.getText().toString();
            text = text == null?"add test":text;
            if(text!=null){
                new TodoDao(MemoEditActivity.this).add(new Todos(text));
                Intent intent = new Intent();
                intent.putExtra("result", "success");
                MemoEditActivity.this.setResult(RESULT_OK, intent);
            }

            MemoEditActivity.this.finish();
        }
        return false;
    }
}
