package tsg.jsonextractiontry1;

/**
 * Created by terrelsimeongordon on 19/03/16.
 */

        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by terrelsimeongordon on 09/10/15.
 */
public class ListViewSingleAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    //    ImageLoader imageLoader;
    private List<OrganisationProFolder> organisationProList = null;

    private ArrayList<OrganisationProFolder> arraylist;

    public ListViewSingleAdapter(Context context,
                               List<OrganisationProFolder> worldpopulationlist) {
        this.context = context;
        this.organisationProList = worldpopulationlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<OrganisationProFolder>();
        this.arraylist.addAll(worldpopulationlist);
//        imageLoader = new ImageLoader(context);
    }

    public class ViewHolder {
        TextView user;
        TextView skill;
        TextView userID;

        ImageView photo;
    }

    @Override
    public int getCount() {
        return organisationProList.size();
    }

    @Override
    public Object getItem(int position) {
        return organisationProList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.organise_pro_custom_layout, null);
            // Locate the TextViews in listview_item.xml
            holder.user = (TextView) view.findViewById(R.id.user_name);
            holder.skill = (TextView) view.findViewById(R.id.skill);
            holder.userID = (TextView) view.findViewById(R.id.id_);
            holder.userID.setVisibility(View.INVISIBLE);

            // Locate the ImageView in listview_item.xml
            /**
             holder.photo = (ImageView) view.findViewById(R.id.doggie_recipe_img);
             **/
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.user.setText(organisationProList.get(position).getSkill());
        holder.skill.setText(organisationProList.get(position).getName());
        holder.userID.setText(organisationProList.get(position).getUserId());

        // Set the results into ImageView
        /**
         imageLoader.DisplayImage(_DoggieRecipesList.get(position).getPhoto(),
         holder.photo);
         */
//         Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleCustomLayout.class);
                // Pass all data title
//                intent.putExtra("skill",
//                        (_DoggieRecipesList.get(position).getSkill()));
//                // Pass all data name
//                intent.putExtra("name",
//                        (_DoggieRecipesList.get(position).getName()));

                intent.putExtra("userID",
                        (organisationProList.get(position).getUserId()));
//                // Pass all data photo
//                intent.putExtra("photo",
//                        (_DoggieRecipesList.get(position).getPhoto()));

                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });
        return view;
    }




}
