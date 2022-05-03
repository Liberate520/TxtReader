package ru.samsung.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_STORAGE = 101;
    MyEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!PermissionUtils.hasPermissions(MainActivity.this)) {
            PermissionUtils.requestPermissions(MainActivity.this, PERMISSION_STORAGE);
        }
        editText = findViewById(R.id.ed_text);
        editText.setShowSoftInputOnFocus(false);

        List<String> pages = readFile("Nochnoy_Dozor.txt");
        String page = readPage(pages, 0);
        editText.setText(pages, 0);
        Log.d("ReadFile", "text input");
        Log.d("ReadFile", "string size: " + pages.size());
        Log.d("ReadFile", page);
    }

    private List<String> readFile(String name){
        File storageDirectory = Environment.getExternalStorageDirectory();
        File downloadDirectory = new File(storageDirectory, "Download");
        File file = new File(downloadDirectory, name);
        if (file.exists() && file.canRead()){
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
                int line = 0;
                List<String> pages = new ArrayList<>();
                String page = "";
                while (scanner.hasNext()){
                    page += scanner.nextLine()+"\n";
                    line++;
                    if (line > 20){
                        pages.add(page);
                        page = "";
                        line = 0;
                    }
                }
                scanner.close();
                Log.d("ReadFile", "read file OK");
                return pages;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }
    }

    private String readPage(List<String> pages, int page){
        return pages.get(page);
    }
}

//        if (getIntent().getData() != null) {
//            String path = getIntent().getData().getPath();
//            Log.d("My", "Path: " + path);
//
//            String name = getCursorValue(MediaStore.MediaColumns.DISPLAY_NAME);
//            Log.d("My", "name: " + name);
//        }

//        getDirectory("document");

//    private String getCursorValue(String key) {
//        try {
//            Cursor cursor = null;
//            String result = null;
//            try {
//                cursor = getContentResolver().query(getIntent().getData(), new String[]{key}, null, null, null);
//                cursor.moveToFirst();
//                result = cursor.getColumnCount() > 0 ? cursor.getString(0) : null;
//            } catch (Exception e) {
//                Log.d("My", "cursor error: " + e.getMessage());
//            } finally {
//                if (cursor != null) {
//                    cursor.close();
//                }
//            }
//            Log.d("My", "cursor result: " + result);
//            return result;
//        } catch (Exception e) {
//            return null;
//        }
//    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        Log.d("My", "test");
//    }

//    private File getDirectory(String directory){
//        File file = Environment.getExternalStorageDirectory();
//        for (File f: file.listFiles()){
//            Log.d("My", f.getName());
//        }
//        return null;
//    }
