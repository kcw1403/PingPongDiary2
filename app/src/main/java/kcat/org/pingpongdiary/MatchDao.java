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
        values.put("hand_type", matchDto.getHandType());
        values.put("racket_type", matchDto.getRacket_type());
        values.put("front_rubber", matchDto.getFrontRubber());
        values.put("back_rubber", matchDto.getBackRubber());
        values.put("win_set", matchDto.getWinSet());
        values.put("rose_set", matchDto.getRoseSet());
        values.put("match_date", matchDto.getMatchDate());
        values.put("review", matchDto.getReview());

        long rowId = db.insert("MATCH_TABLE", values);
        if (rowId < 0) {
            throw new SQLException("Fail At Insert");
        }

    }

    public ArrayList<MatchDto> select(String s_name, int s_handType, int s_racketType, int s_fRubber, int s_bRubber) {
        ArrayList<MatchDto> list = new ArrayList<MatchDto>();

        list = db.select(s_name, s_handType, s_racketType, s_fRubber, s_bRubber);
        return list;
    }

    public ArrayList<MatchDto> selectName(){
        ArrayList<MatchDto> list = new ArrayList<MatchDto>();
        list = db.selectName();
        return list;
    }
    public void update(MatchDto matchDto)throws SQLException {
        ContentValues values = new ContentValues();

        values.put("_id",matchDto.getId());
        values.put("name", matchDto.getName());
        values.put("club_name", matchDto.getClubName());
        values.put("rank", matchDto.getRank());
        values.put("handy", matchDto.getHandy());
        values.put("hand_type", matchDto.getHandType());
        values.put("racket_type", matchDto.getRacket_type());
        values.put("front_rubber", matchDto.getFrontRubber());
        values.put("back_rubber", matchDto.getBackRubber());
        values.put("win_set", matchDto.getWinSet());
        values.put("rose_set", matchDto.getRoseSet());
        values.put("match_date", matchDto.getMatchDate());
        values.put("review", matchDto.getReview());

        long rowId = db.update("MATCH_TABLE", values);
        if (rowId < 0) {
            throw new SQLException("Fail At Insert");
        }
    }

    public long delete(MatchDto matchDto)
    {
        ContentValues values = new ContentValues();

        values.put("_id",matchDto.getId());
        return db.delete("MATCH_TABLE",values);
    }



}
