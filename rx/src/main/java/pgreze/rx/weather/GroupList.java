package pgreze.rx.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupList<T> {
    @SerializedName("list")
    public List<T> list;
}
