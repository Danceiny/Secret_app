package cc.cannot.secret.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cc.cannot.secret.Config;
import cc.cannot.secret.R;
import cc.cannot.secret.net.Comment;
import cc.cannot.secret.net.Message;

import static android.R.attr.data;

/**
 * Created by huangzhen on 2017/4/27.
 */

public class atyMsgCommentListAdapter extends BaseAdapter{

    private final Context context;
//    private List<Message> data = new ArrayList<Message>();
    private List<Comment> comments = new ArrayList<Comment>();
    public atyMsgCommentListAdapter(Context context){
        this.context = context;
    }
    
    public void clear(){
       comments.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.aty_timeline_list_cell,null);
            convertView.setTag(new ListCell((TextView) convertView.findViewById(R.id.tvCellLabel)));
        }

        ListCell lc = (ListCell)convertView.getTag();
        Comment comment = getItem(position);
        lc.getTvCellLabel().setText(comment.getContent());
        return convertView;
    }

    public Context getContext() {
        return context;
    }

    public void addAll(List<Comment> data){
        comments.addAll(data);
        notifyDataSetChanged();
    }

    private static class ListCell{
        public ListCell(TextView tvCellLabel){
            this.tvCellLabel = tvCellLabel;
        }
        private TextView tvCellLabel;
        public TextView getTvCellLabel(){
            return tvCellLabel;
        }
    }
}
