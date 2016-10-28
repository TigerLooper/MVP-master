package com.my.mvp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 3;//数据库版本
    private static String DB_PATH = "/data/data/com.example.lp.zhi/databases/";//数据库保存路劲
    private static String DB_NAME = "meituan_cities.db";//要创建的db数据库
    private static String ASSETS_NAME = "meituan_cities.db";//要读的本地db文件
    private SQLiteDatabase myDataBase = null;
    private final Context myContext;
    private static final int ASSETS_SUFFIX_BEGIN = 101;
    private static final int ASSETS_SUFFIX_END = 103;

    public DBHelper(Context context, String name, CursorFactory factory,
                    int version) {
        super(context, name, null, version);
        this.myContext = context;
    }

    public DBHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    public DBHelper(Context context, String name) {
        this(context, name, DB_VERSION);
    }

    public DBHelper(Context context) {
        this(context, DB_PATH + DB_NAME);
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {

            try {
                File dir = new File(DB_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File dbf = new File(DB_PATH + DB_NAME);
                if (dbf.exists()) {
                    dbf.delete();
                }
                SQLiteDatabase.openOrCreateDatabase(dbf, null);//在该文件路径下创建emty DB
                copyDataBase();
            } catch (IOException e) {
                throw new Error("创建异常");
            }
        }
    }


    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        String myPath = DB_PATH + DB_NAME;
        try {
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {

        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }


    private void copyDataBase() throws IOException {
        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(ASSETS_NAME);//以流的方式读出来
        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;//写入文件数据库路径
        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];//以字节流的形式将输入流传化为输出流
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    private void copyBigDataBase() throws IOException {
        InputStream myInput;
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        for (int i = ASSETS_SUFFIX_BEGIN; i < ASSETS_SUFFIX_END + 1; i++) {
            myInput = myContext.getAssets().open(ASSETS_NAME + "." + i);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myInput.close();
        }
        myOutput.close();
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}