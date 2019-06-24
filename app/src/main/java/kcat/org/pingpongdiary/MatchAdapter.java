package kcat.org.pingpongdiary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MatchAdapter extends BaseAdapter {

    private ArrayList<MatchDto> mItems = new ArrayList<>();
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public MatchDto getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        /* 'listview_match' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_match, parent, false);

        }

        /* 'listview_match'에 정의된 위젯에 대한 참조 획득 */

        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name) ;
        TextView tv_win = (TextView) convertView.findViewById(R.id.tv_win) ;
        TextView tv_rose = (TextView) convertView.findViewById(R.id.tv_rose) ;
        TextView tv_result = (TextView) convertView.findViewById(R.id.tv_result) ;
        LinearLayout background = (LinearLayout)convertView.findViewById(R.id.listColor);
        TextView tv_matchDate = (TextView) convertView.findViewById(R.id.tv_matchDate) ;
        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        MatchDto myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */

//        tv_name.setText(myItem.getName());
//        tv_contents.setText(myItem.getContents());
        tv_name.setText(myItem.getName());
        tv_win.setText(String.valueOf(myItem.getWinSet()));
        tv_rose.setText(String.valueOf(myItem.getRoseSet()));
        if(myItem.getWinSet() > myItem.getRoseSet())
        {
            tv_result.setText("승리");
            background.setBackgroundColor(Color.parseColor("#a3cfec"));
        }else{
            tv_result.setText("패배");
            background.setBackgroundColor(Color.parseColor("#e2b6b3"));
        }
        String date = myItem.getMatchDate();



        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddkkmm");

        try {
            Date to = transFormat.parse(date);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");
            tv_matchDate.setText(sdf.format(to));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }
    public void addItem(MatchDto matchDto) {
        /* mItems에 MyItem을 추가한다. */
        mItems.add(matchDto);
    }


}
