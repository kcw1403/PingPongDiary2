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

public class TrainingAdapter extends BaseAdapter {

    private ArrayList<String> mItems = new ArrayList<>();


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public String getItem(int position) {
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
            convertView = inflater.inflate(R.layout.listview_training, parent, false);
        }

        /* 'listview_match'에 정의된 위젯에 대한 참조 획득 */


        LinearLayout background = (LinearLayout)convertView.findViewById(R.id.listColor);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */


        /* 각 위젯에 세팅된 아이템을 뿌려준다 */


        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }
    public void addItem(String trainingName) {
        /* mItems에 MyItem을 추가한다. */
        mItems.add(trainingName);
    }


}
