package com.belspec.app.adapters;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.belspec.app.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> implements Parcelable {
    private List<Bitmap> ivList;
    private List<String> filePathList;
    private String mCurrentPath;
    public ImageListAdapter(){
        ivList = new ArrayList<>();
        filePathList = new ArrayList<>();
    }

    protected ImageListAdapter(Parcel in) {
        ivList = in.createTypedArrayList(Bitmap.CREATOR);
        filePathList = in.createStringArrayList();
        mCurrentPath = in.readString();
    }

    public static final Creator<ImageListAdapter> CREATOR = new Creator<ImageListAdapter>() {
        @Override
        public ImageListAdapter createFromParcel(Parcel in) {
            return new ImageListAdapter(in);
        }

        @Override
        public ImageListAdapter[] newArray(int size) {
            return new ImageListAdapter[size];
        }
    };

    public String getFilePath(int pos){
        if (filePathList!=null) {
            return filePathList.get(pos);
        }else{
            return null;
        }
    }

    public void addFilePath(String path){
        mCurrentPath = path;
        filePathList.add(path);
    }

    public List<String> getFilePathList(){
        return filePathList;
    }

    public void setFilePathList(List<String> paths){
        filePathList = paths;
    }

    public String getCurrentPath(){
        return mCurrentPath;
    }

    public void setCurrentPath(String path){
        mCurrentPath = path;
        filePathList.add(path);
    }

    public void deleteCurrentPath(){
        if (mCurrentPath != null){
            for(int i = 0; i< filePathList.size(); i++){
                if (filePathList.get(i) == mCurrentPath){
                    File file = new File(filePathList.get(i));
                    if (file.exists()){
                        file.delete();
                    }
                    filePathList.remove(i);
                }
            }
            mCurrentPath = null;
        }
    }
    public void add(Bitmap bitmap){
        ivList.add(bitmap);
        notifyDataSetChanged();
    }

    public void addList(List<Bitmap> bitmapList){
        ivList.addAll(bitmapList);
        notifyDataSetChanged();
    }


    public void clear(){
        ivList.clear();
        for (int i = 0; i<filePathList.size(); i++){
            File file = new File(filePathList.get(i));
            if (file.exists()) {
                file.delete();
            }
        }
        filePathList.clear();
        notifyDataSetChanged();
    }

    public Bitmap getByPosition(int pos){
        return ivList.get(pos);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.foto_item, parent, false);
        ImageViewHolder pvh = new ImageViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.iv.setImageBitmap(ivList.get(position));
    }

    @Override
    public int getItemCount() {
        return ivList.size();
    }

    public int getFilePathCount(){
        return filePathList.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(ivList);
        parcel.writeStringList(filePathList);
        parcel.writeString(mCurrentPath);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        ImageViewHolder(View itemView){
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.imvImage);
        }
    }
}
