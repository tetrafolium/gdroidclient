package org.gdroid.gdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CollectionGridAdapter extends RecyclerView.Adapter<CollectionGridAdapter.MyViewHolder> {

    private Context mContext;
    private List<AppCollectionDescriptor> appCollectionDescriptorList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public RecyclerView inner_recycler_view;

        private AppGridAdapter adapter;
        private List<AppDescriptor> appDescriptorList;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.collection_headline);
            count = (TextView) view.findViewById(R.id.more_button);
            inner_recycler_view = (RecyclerView) view.findViewById(R.id.inner_recycler_view);

            appDescriptorList = new ArrayList<>();
            adapter = new AppGridAdapter(mContext, appDescriptorList);

            //inner_recycler_view.setLayoutManager(mLayoutManager);
            //recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
            //inner_recycler_view.addItemDecoration(new MainActivity.GridSpacingItemDecoration(1, dpToPx(10), true));
            inner_recycler_view.setItemAnimator(new DefaultItemAnimator());

            inner_recycler_view.setAdapter(adapter);

            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 1);
            LinearLayoutManager layoutManager2
                    = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);

            inner_recycler_view.setLayoutManager(layoutManager2);

            prepareAlbums();

//            thumbnail = (LinearLayout) view.findViewById(R.id.collection_content);
//
//            final Activity activity = (Activity) mContext;
//            thumbnail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent myIntent = new Intent(activity, AppDetailActivity.class);
//                    //myIntent.putExtra("key", value); //Optional parameters
//                    activity.startActivity(myIntent);
//
//                }
//            });
        }

        /**
         * Converting dp to pixel
         */
        private int dpToPx(int dp) {
            Resources r = mContext.getResources();
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
        }

        private void prepareAlbums() {
            int[] covers = new int[]{
                    R.drawable.a1,
                    R.drawable.a1,
                    R.drawable.a1,
                    R.drawable.a1,
                    R.drawable.a1,
                    R.drawable.a1,
                    R.drawable.a1,
                    R.drawable.a1,
                    R.drawable.a1,
                    R.drawable.a1,
                    R.drawable.a1};
//        R.drawable.album1,
//                R.drawable.album2,
//                R.drawable.album3,
//                R.drawable.album4,
//                R.drawable.album5,
//                R.drawable.album6,
//                R.drawable.album7,
//                R.drawable.album8,
//                R.drawable.album9,
//                R.drawable.album10,
//                R.drawable.album11};

            AppDescriptor a = new AppDescriptor("True Romance", 4.5f, covers[0]);
            appDescriptorList.add(a);

            a = new AppDescriptor("Xscpae", 2, covers[1]);
            appDescriptorList.add(a);

            a = new AppDescriptor("Maroon 5", 4.5f, covers[2]);
            appDescriptorList.add(a);

            a = new AppDescriptor("Born to Die", 4.5f, covers[3]);
            appDescriptorList.add(a);

            a = new AppDescriptor("Honeymoon", 4.5f, covers[4]);
            appDescriptorList.add(a);

            a = new AppDescriptor("I Need a Doctor", 1, covers[5]);
            appDescriptorList.add(a);

            a = new AppDescriptor("Loud", 4.5f, covers[6]);
            appDescriptorList.add(a);

            a = new AppDescriptor("Legend", 4.5f, covers[7]);
            appDescriptorList.add(a);

            a = new AppDescriptor("Hello", 4, covers[8]);
            appDescriptorList.add(a);

            a = new AppDescriptor("Greatest Hits", 2, covers[9]);
            appDescriptorList.add(a);

            adapter.notifyDataSetChanged();
        }

    }

    public CollectionGridAdapter(Context mContext, List<AppCollectionDescriptor> appCollectionDescriptorList) {
        this.mContext = mContext;
        this.appCollectionDescriptorList = appCollectionDescriptorList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collection_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        AppCollectionDescriptor appDescriptor = appCollectionDescriptorList.get(position);
        holder.title.setText(appDescriptor.getName());

        // loading appDescriptor cover using Glide library
        //Glide.with(mContext).load(appDescriptor.getThumbnail()).into(holder.thumbnail);
//        holder.thumbnail.setImageDrawable();

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return appCollectionDescriptorList.size();
    }
}