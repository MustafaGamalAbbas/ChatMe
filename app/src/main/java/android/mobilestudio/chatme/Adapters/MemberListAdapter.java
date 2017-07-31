package android.mobilestudio.chatme.Adapters;

import android.mobilestudio.chatme.Models.Person;
import android.mobilestudio.chatme.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pisoo on 7/30/2017.
 */

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberHolder>  {

    List<Person> memberList;
    private static RecyclerViewClickListener itemListener;

    public  MemberListAdapter (List<Person> list , RecyclerViewClickListener listener)
    {
        this.memberList = list ;
        this.itemListener = listener ;
    }

    @Override
    public MemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_people_item, parent,false);
        return new MemberHolder (itemView);
    }

    @Override
    public void onBindViewHolder(MemberHolder holder, int position) {
         holder.mName.setText(memberList.get(position).getFirstName() +" "+
                              memberList.get(position).getLastName());
        holder.mEmail.setText(memberList.get(position).getEmail());
        if(memberList.get(position).getGender().equals("Female")){
            holder.mPhoto.setImageResource(R.drawable.female_img);
        }
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public static class MemberHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mName  , mEmail ;
        ImageView mPhoto ;
        public MemberHolder(View itemView) {
            super(itemView);
            mName=(TextView) itemView.findViewById(R.id.cardview_name);
            mEmail=(TextView) itemView.findViewById(R.id.cardview_email);
            mPhoto = (ImageView)  itemView.findViewById(R.id.cardview_imgmember);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }
    }
    public interface RecyclerViewClickListener {

        void recyclerViewListClicked(View v, int position);
    }

}
