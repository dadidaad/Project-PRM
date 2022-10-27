package com.example.projectprm.utils.converters;

import android.content.Context;
import android.net.Uri;

import com.example.projectprm.R;

public class PathConverter {
    public static int GetResource(Context context, String name ){
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
