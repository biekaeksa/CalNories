package com.spiderman.calnories.data;

/**
 * Created by Biekaeksa on 3/14/2017.
 */

public class UserModel {
    private String id;
    private String displayName;
    private String email;
    private String photoUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public class UserDataModel extends BaseModel {
        private UserModel data;

        public UserModel getData(){
            return data;
        }

        public void setData(UserModel data){
            this.data = data;
        }
    }
}
