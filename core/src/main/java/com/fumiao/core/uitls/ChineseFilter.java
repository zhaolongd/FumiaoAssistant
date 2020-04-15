package com.fumiao.core.uitls;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Pattern;

public class ChineseFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
        String regex = "^[\u4E00-\u9FA5]+$";
        boolean isChinese = Pattern.matches(regex, charSequence.toString());
        //if (!Character.isLetterOrDigit(charSequence.charAt(i)) || isChinese) {
        if (isChinese) {
            return "";
        }
        return null;
    }
}
