package com.aolan.b365.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import com.aolan.b365.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;



/**
 * 可一键清除的EditText
 * 使用说明：可通过xml进行样式设置
 * app:drawableLeft  设置EditTextView的左icon
 * app:showPasswordMark 显示密码标记与否
 * app:drawableHidePasswordMark 隐藏密码标记icon(默认)
 * app:drawableShowPasswordMark 显示密码标记的icon
 * app:inputType="phone" 设置EditTextView的输入类型，用法与原生控件一样
 * app:drawableLine 设置下划线
 * app:passwordMaxLength;密码最大长度
 */

public class OnekeyEditTextView extends ConstraintLayout implements View.OnClickListener {

    private EditText etContent;
    private ImageView ivClear;
    private ImageView ivPasswordMark;
    private View line;
    private OnOnekeyClearListener listener;

    private int drawableHidePasswordMark;
    private int drawableShowPasswordMark;
    private boolean isPasswordShow;//密码是否可见

    public OnekeyEditTextView(Context context) {
        this(context, null);
    }

    public OnekeyEditTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OnekeyEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_onekey_clear_edittext, this);
        etContent = (EditText) findViewById(R.id.et_content);
        ivClear = (ImageView) findViewById(R.id.iv_clear);
        line = findViewById(R.id.line);
        ivPasswordMark = findViewById(R.id.iv_password_mark);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(
                    attrs, R.styleable.OnekeyEditTextView, defStyleAttr, 0);
            try {
                String hint = a.getString(R.styleable.OnekeyEditTextView_hint);
                if (!TextUtils.isEmpty(hint)) {
                    etContent.setHint(hint);
                }
                int inputType = a.getInt(R.styleable.OnekeyEditTextView_inputType, EditorInfo.TYPE_NULL);//
                etContent.setInputType(inputType);
                setPasswordTransformationMethod(inputType);
                int drawableLine = a.getResourceId(R.styleable.OnekeyEditTextView_drawableLine, R.drawable.shape_line_height_1dp);
                line.setBackgroundResource(drawableLine);
                int drawableLeft = a.getResourceId(R.styleable.OnekeyEditTextView_drawableLeft, 0);
                if (drawableLeft != 0) {
                    etContent.setBackgroundResource(R.color.color_00ffffff);
                    etContent.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, 0, 0, 0);
                }
                drawableHidePasswordMark = a.getResourceId(R.styleable.OnekeyEditTextView_drawableHidePasswordMark, R.mipmap.ic_eyeclose);
                ivPasswordMark.setImageResource(drawableHidePasswordMark);
                drawableShowPasswordMark = a.getResourceId(R.styleable.OnekeyEditTextView_drawableShowPasswordMark, R.mipmap.ic_eye);
                boolean isShowMark = a.getBoolean(R.styleable.OnekeyEditTextView_showPasswordMark, false);
                ivPasswordMark.setVisibility(isShowMark ? VISIBLE : GONE);
                if (isShowMark) {
                    ivPasswordMark.setOnClickListener(this);
                }
                int maxLength = a.getInteger(R.styleable.OnekeyEditTextView_maxLength, Integer.MAX_VALUE);
                etContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});//设置最大长度

            } finally {
                a.recycle();
            }
        }
        ivClear.setVisibility(GONE);
        ivClear.setOnClickListener(this);
        //监听文本变化，判断是否要显示"x"
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = etContent.getText().toString();
                String str = stringFilter(editable); //过滤特殊字符
                if (!editable.equals(str)) {
                    etContent.setText(str);
                }
                etContent.setSelection(etContent.length());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etContent.getText().length() > 0) {
                    ivClear.setVisibility(VISIBLE);
                } else {
                    ivClear.setVisibility(GONE);
                }
                if (listener != null) {
                    listener.onTextChanged(etContent.getText().length() <= 0);
                }
            }
        });
    }

    /**
     * 密码模式下，将小圆点转成*
     */
    private void setPasswordTransformationMethod(int inputType) {
        if (inputType == 129) {
            etContent.setTransformationMethod(new AsteriskPasswordTransformationMethod());
            etContent.setTextColor(getResources().getColor(R.color.color_4c4c4c));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_clear://清除文本
                etContent.setText("");
                if (listener != null) {
                    listener.onClearKeywords();
                }
                break;
            case R.id.iv_password_mark:
                if (isPasswordShow) {
                    ivPasswordMark.setImageResource(drawableShowPasswordMark);
                    etContent.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etContent.setTextColor(getResources().getColor(R.color.color_4c4c4c));
                } else {
                    ivPasswordMark.setImageResource(drawableHidePasswordMark);
                    etContent.setTransformationMethod(new AsteriskPasswordTransformationMethod());
                    etContent.setTextColor(getResources().getColor(R.color.color_4c4c4c));
                }
                isPasswordShow = !isPasswordShow;
                etContent.setSelection(etContent.getText().length());
                break;
        }
    }


    public void setListener(OnOnekeyClearListener listener) {
        this.listener = listener;
    }

    /**
     * 设置EditText的hint文本
     *
     * @param strHint
     */
    public void setHint(@StringRes int strHint) {
        if (strHint != 0) {
            etContent.setHint(strHint);
        }
    }

    /**
     * 设置EditText的hint文本
     *
     * @param strHint
     */
    public void setHint(String strHint) {
        etContent.setHint(strHint);
    }

    /**
     * 设置EditText的文本
     *
     * @param keywords
     */
    public void setText(String keywords) {
        etContent.setText(keywords);
    }

    private String stringFilter(String str) throws PatternSyntaxException {
        String regEx = "[\n\t]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    public EditText getEtContent() {
        return etContent;
    }

    /**
     * 设置EditText的输入类型
     *
     * @param inputType
     */
    public void setInputType(int inputType) {
        etContent.setInputType(inputType);
    }

    /**
     * 一键清除监听
     */
    public interface OnOnekeyClearListener {
        /**
         * 清除关键字
         */
        void onClearKeywords();

        /**
         * 监听文本是否空
         *
         * @param isEmpty
         */
        void onTextChanged(boolean isEmpty);

    }

    public class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        private class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;


            public PasswordCharSequence(CharSequence source) {
                mSource = source; // Store char sequence
            }

            public char charAt(int index) {
                return '●'; // This is the important part
            }

            public int length() {
                return mSource.length(); // Return default
            }

            public CharSequence subSequence(int start, int end) {
                return mSource.subSequence(start, end);// Return default
            }

        }
    }
}
