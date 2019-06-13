package kcat.org.pingpongdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;

public class MatchSQLiteOpenHelper extends SQLiteOpenHelper {

    private static MatchSQLiteOpenHelper instance;
    private static SQLiteDatabase db;

    private Context context;
    public static final MatchSQLiteOpenHelper getInstance(Context context) {
        initialize(context);
        return instance;
    }

    private static void initialize(Context context) {
        // TODO Auto-generated method stub

        if (instance == null) {
            instance = new MatchSQLiteOpenHelper(context);
            try {
                db = instance.getWritableDatabase();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }

    public MatchSQLiteOpenHelper(Context context) {
        super(context, "PINGPONG_DB", null, 1);
        this.context = context;
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "CREATE TABLE MATCH_TABLE(_id INTEGER PRIMARY KEY autoincrement,  name TEXT, club_name TEXT, rank INTEGER, handy INTEGER, racket_type INTEGER, front_rubber INTEGER, back_rubber INTEGER, win_set INTEGER, rose_set INTEGER, match_date TEXT, review TEXT);";

        db.execSQL(sql);
        Toast.makeText(context,"Success Create Match Table",Toast.LENGTH_SHORT).show();

    }

    public int delete(int id)
    {
        return db.delete("MATCH_TABLE", "_id = ?", new String[]{String.valueOf(id)});
    }

    public long insert(String table, ContentValues values) {
        return db.insert(table, null, values);
    }
    public ArrayList<MatchDto> select(String s_name,int s_handType, int s_racketType, int s_fRubber, int s_bRubber ) {
        ArrayList<MatchDto> list = new ArrayList<MatchDto>();

        db = instance.getReadableDatabase();

        Cursor c = db.query("MATCH_TABLE", null, null, null, null, null,
                "match_date desc", "10");

        //String sql= "SELECT * from MATCH_TABLE";

        //if(s_name!="")



        while (c.moveToNext()) {
            MatchDto matchDto = new MatchDto();
            int id = c.getInt(c.getColumnIndex("_id"));
            matchDto.setId(id);
            String name = c.getString(c.getColumnIndex("name"));
            matchDto.setName(name);

            String clubMame = c.getString(c.getColumnIndex("club_name"));
            matchDto.setClubName(clubMame);
            String writeDate = c.getString(c.getColumnIndex("match_date"));
            matchDto.setMatchDate(writeDate);

            int winSet = c.getInt(c.getColumnIndex("win_set"));
            matchDto.setWinSet(winSet);
            int roseSet = c.getInt(c.getColumnIndex("rose_set"));
            matchDto.setRoseSet(roseSet);
            list.add(matchDto);
        }

        return list;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        String sql = "drop table if exists MATCH_TABLE";
        db.execSQL(sql);
//        String sql2 = "drop table if exists SCORE_TABLE_HARD";
//        db.execSQL(sql2);
//        String sql3 = "drop table if exists ACHIEVEMENT_TABLE";
//        db.execSQL(sql3);
//        onCreate(db);
    }

}