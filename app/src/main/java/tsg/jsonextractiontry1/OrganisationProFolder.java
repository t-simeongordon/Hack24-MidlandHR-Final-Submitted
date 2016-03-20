package tsg.jsonextractiontry1;

/**
 * Created by terrelsimeongordon on 19/03/16.
 */

import android.util.Log;

/**
 * Created by terrelsimeongordon on 09/10/15.
 */
public class OrganisationProFolder {
    private String skill;
    private String name;
    private String photo;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getName() {
        Log.e("what happened ","get name instance1 "+ name);

        return name;
    }

    public void setName(String name) {
        Log.e("what happened ","get name instance2 "+name);

        this.name = name;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
