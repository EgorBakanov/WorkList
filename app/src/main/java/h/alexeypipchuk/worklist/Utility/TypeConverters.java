package h.alexeypipchuk.worklist.Utility;

import android.net.Uri;

import androidx.room.TypeConverter;


public class TypeConverters {

    @TypeConverter
    public static String UritoString(Uri uri){
        return uri != null ? uri.toString() : "";
    }

    @TypeConverter
    public static Uri StringtoUri(String str){
        return (str == null || str.isEmpty()) ? null : Uri.parse(str);
    }
}
