package curry.stephen.universalanroidtv.model;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by LingChong on 2016/4/11 0011.
 */
public class TabDataModel implements Serializable {

    private int mID;
    private Uri mPictureNormal;
    private Uri mPictureSelected;

    public TabDataModel(Builder builder) {
        mID = builder.mID;
        mPictureNormal = builder.mPictureNormal;
        mPictureSelected = builder.mPictureSelected;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public Uri getPictureNormal() {
        return mPictureNormal;
    }

    public void setPictureNormal(Uri pictureNormal) {
        mPictureNormal = pictureNormal;
    }

    public Uri getPictureSelected() {
        return mPictureSelected;
    }

    public void setPictureSelected(Uri pictureSelected) {
        mPictureSelected = pictureSelected;
    }

    public class Builder {

        private int mID;
        private Uri mPictureNormal;
        private Uri mPictureSelected;

        public TabDataModel build() {
            return new TabDataModel(this);
        }

        public Builder id(int id) {
            mID = id;
            return this;
        }

        public Builder picureNormal(Uri pictureNormal) {
            mPictureNormal = pictureNormal;
            return this;
        }

        public Builder pictureSelected(Uri pictureSelected) {
            mPictureSelected = pictureSelected;
            return this;
        }
    }
}
