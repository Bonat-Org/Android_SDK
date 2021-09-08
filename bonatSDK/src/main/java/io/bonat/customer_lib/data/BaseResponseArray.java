
package io.bonat.customer_lib.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class BaseResponseArray<T> implements Parcelable {

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private ArrayList<T> data;

    @SerializedName("errors")
    @Expose
    private ArrayList<String> errors;

    protected BaseResponseArray(Parcel in) {
        if (in.readByte() == 0) {
            code = null;
        } else {
            code = in.readInt();
        }
        message = in.readString();
        errors = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (code == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(code);
        }
        dest.writeString(message);
        dest.writeStringList(errors);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BaseResponseArray> CREATOR = new Creator<BaseResponseArray>() {
        @Override
        public BaseResponseArray createFromParcel(Parcel in) {
            return new BaseResponseArray(in);
        }

        @Override
        public BaseResponseArray[] newArray(int size) {
            return new BaseResponseArray[size];
        }
    };

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }
}