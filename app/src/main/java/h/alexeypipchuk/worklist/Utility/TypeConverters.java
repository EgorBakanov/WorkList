package h.alexeypipchuk.worklist.Utility;

import android.net.Uri;

import androidx.room.TypeConverter;

import java.util.Date;


public class TypeConverters {

    @TypeConverter
    public static String UriToString(Uri uri){
        return uri != null ? uri.toString() : "";
    }

    @TypeConverter
    public static Uri StringToUri(String str){
        return (str == null || str.isEmpty()) ? null : Uri.parse(str);
    }

    @TypeConverter
    public static Long DateToLong(Date date){
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date LongToDate(Long l){
        return l == null ? null : new Date(l);
    }
}
