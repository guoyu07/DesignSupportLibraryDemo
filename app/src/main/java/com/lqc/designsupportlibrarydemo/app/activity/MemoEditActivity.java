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
    private String sourceCon;
    private boolean isExited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoedit);
        editText = (EditText)findViewById(R.id.memoedit_EditText);

        Intent intent = getIntent();
        try{
            isExited = intent.getExtras().getBoolean("isExited");
        }catch (NullPointerException e){
            e.printStackTrace();
            isExited = false;
        }
        try{
            sourceCon = intent.getExtras().getString("editTextCon");
            editText.setText(sourceCon);
        }catch (NullPointerException e){
            e.printStackTrace();
            editText.setText("");
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            String text = editText.getText().toString();
            text = text == null?"empty":text;
            if(text!=null){
//                new TodoDao(MemoEditActivity.this).add(new Todos(text));
                Intent intent = new Intent();
                intent.putExtra("result", text);
                intent.putExtra("isNew", true);
                MemoEditActivity.this.setResult(RESULT_OK, intent);
            }

            MemoEditActivity.this.finish();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        if (isExited == true){
            Intent intent = new Intent();
            intent.setClass(MemoEditActivity.this, MemoActivity.class)
                    .putExtra("sourceCon", sourceCon)
                    .putExtra("result", editText.getText().toString())
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        super.onDestroy();
    }
}
