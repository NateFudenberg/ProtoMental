package bmental.corgis.protomental;

/**
 * Created by Licha on 6/2/2015.
 */
import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private final SparseArray<Group> groups;
    public LayoutInflater inflater;
    public Activity activity;
    private int flag;

    public MyExpandableListAdapter(Activity act, SparseArray<Group> groups, int info_flag) {
        activity = act;
        this.flag = info_flag;
        this.groups = groups;
        inflater = act.getLayoutInflater();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView text;
        if (convertView == null) {
            if(this.flag == 0){ convertView = inflater.inflate(R.layout.listrow_details_info, null);}
            else{
                convertView = inflater.inflate(R.layout.listrow_details_task, null);
            }
        }
        text = (TextView) convertView.findViewById(R.id.textView1);
        text.setText(children);

        String toastyText = "This would pull up a closer view with the ability to add info!";
        if(this.flag == 0){toastyText = "This would redirect to an appropriate website if there is internet. Otherwise will go to a small local set of information if possible.";}
        final String toastedText = toastyText;

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, toastedText,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_group, null);
        }
        Group group = (Group) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}