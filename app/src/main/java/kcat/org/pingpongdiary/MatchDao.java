package kcat.org.pingpongdiary;

import android.content.ContentValues;
import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;

public class MatchDao {
    MatchSQLiteOpenHelper db;

    MatchDao(Context context) {
        db = MatchSQLiteOpenHelper.getInstance(context);
    }

    public void close() {
        db.close();
    }

    public void insert(MatchDto matchDto) throws SQLException {
        ContentValues values = new ContentValues();

        values.put("name", matchDto.getName());
        values.put("club_name", matchDto.getClubName());
        values.put("rank", matchDto.getRank());
        values.put("handy", matchDto.getHandy());
        values.put("racket_type", matchDto.getRacket_type());
        values.put("front_rubber", matchDto.getFrontRubber());
        values.put("back_rubber", matchDto.getBackRubber());
        values.put("win_set", matchDto.getWinSet());
        values.put("rose_set", matchDto.getRoseSet());
        values.put("match_date", matchDto.getMatchDate());
        values.put("review", matchDto.getReview());

        long rowId = db.insert("MATCH_TABLE",values);
        if (rowId < 0) {
            throw new SQLException("Fail At Insert");
        }

    }
    public ArrayList<MatchDto> select() {
        ArrayList<MatchDto> list = new ArrayList<MatchDto>();
        list = db.select();
        return list;

    }



}
