package com.fumiao.core.uitls;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.fumiao.core.R;
import com.fumiao.core.widget.MyKeyBoardView;

import java.lang.reflect.Method;
import java.text.DecimalFormat;

/**
 * 自定义键盘
 * Created by xuejinwei on 16/3/5.
 */
public class KeyboardUtil {
    private Activity mActivity;
    private boolean  mIfRandom;

    private MyKeyBoardView mKeyboardView;
    private Keyboard mKeyboardNumber;//数字键盘
    private EditText mEditText;
    private TextView mTextView;
    public KeyboardUtil(Activity activity) {
        this(activity, false);
        decimalToString(mEditText.getText().toString());
    }

    public KeyboardUtil(Activity activity, boolean ifRandom) {
        this.mActivity = activity;
        this.mIfRandom = ifRandom;
        mKeyboardNumber = new Keyboard(mActivity, R.xml.keyboardnumber);
        mKeyboardView = (MyKeyBoardView) mActivity.findViewById(R.id.keyboard_view);
    }

    public String decimalToString(String num){
        if(num.isEmpty()){
            return "";
        }
        Double cny = Double.parseDouble(num);//转换成Double
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(cny);
    }


    /**
     * edittext绑定自定义键盘
     *
     * @param editText 需要绑定自定义键盘的edittext
     */
    public void attachTo(EditText editText) {
        this.mEditText = editText;
        hideSystemSofeKeyboard(mActivity.getApplicationContext(), mEditText);
        showSoftKeyboard();
    }
    /**
     * edittext绑定自定义键盘
     *
     * @param textView 需要绑定自定义键盘的textView
     */
    public void attachTos(TextView textView) {
        this.mTextView = textView;
        hideSystemSofeKeyboards(mActivity.getApplicationContext(), mTextView);
        showSoftKeyboard();
    }
    public void showSoftKeyboard() {
        if (mKeyboardNumber == null) {
            mKeyboardNumber = new Keyboard(mActivity, R.xml.keyboardnumber);
        }
        if (mKeyboardView == null) {
            mKeyboardView = (MyKeyBoardView) mActivity.findViewById(R.id.keyboard_view);
        }

            mKeyboardView.setKeyboard(mKeyboardNumber);

        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setVisibility(View.VISIBLE);
        mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);

    }

    private KeyboardView.OnKeyboardActionListener mOnKeyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = mEditText.getText();
            int start = mEditText.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            } else if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 隐藏键盘
                hideKeyboard();
                if (mOnCancelClick != null) {
                    mOnCancelClick.onCancellClick();
                }
            } else if (primaryCode == Keyboard.KEYCODE_DONE) {// 隐藏键盘
                if (mOnOkClick != null) {
                    mOnOkClick.onOkClick();
                }
            } else {
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }

        @Override
        public void onText(CharSequence charSequence) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }


    };


    /**
     * 隐藏系统键盘
     *
     * @param editText
     */
    public static void hideSystemSofeKeyboard(Context context, EditText editText) {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 11) {
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            editText.setInputType(InputType.TYPE_NULL);
        }
        // 如果软键盘已经显示，则隐藏
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    /**
     * 隐藏系统键盘
     *
     * @param textView
     */
    public static void hideSystemSofeKeyboards(Context context, TextView textView) {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 11) {
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(textView, false);

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            textView.setInputType(InputType.TYPE_NULL);
        }
        // 如果软键盘已经显示，则隐藏
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
    }

    public interface OnOkClick {
        void onOkClick();
    }

    public interface onCancelClick {
        void onCancellClick();
    }

    public OnOkClick mOnOkClick = null;
    public onCancelClick mOnCancelClick;

    public void setOnOkClick(OnOkClick onOkClick) {
        mOnOkClick = onOkClick;
    }

    public void setOnCancelClick(onCancelClick onCancelClick) {
        mOnCancelClick = onCancelClick;
    }


    private boolean isNumber(String str) {
        String wordstr = "0123456789";
        return wordstr.contains(str);
    }



    public void hideKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.GONE);
        }
    }
}