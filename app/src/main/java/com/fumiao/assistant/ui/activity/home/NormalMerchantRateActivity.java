package com.fumiao.assistant.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;
import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.core.uitls.ToastUtil;
import com.fumiao.core.widget.KeyEditText;
import butterknife.BindView;
import butterknife.OnClick;

public class NormalMerchantRateActivity extends BaseActivity {
    @BindView(R.id.wechat_rate)
    KeyEditText wechatRate;
    @BindView(R.id.alipay_rate)
    KeyEditText alipayRate;
    @BindView(R.id.unionpay_rate)
    KeyEditText unionpayRate;
    @BindView(R.id.large_unionpay_rate)
    KeyEditText largeUnionpayRate;

    private InComing inComing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_merchant_rate);
        init();
    }

    private void init() {
        setTitle("费率信息");
        inComing = (InComing) getIntent().getSerializableExtra("incoming");
        filterRate(wechatRate.getEditText());
        filterRate(alipayRate.getEditText());
        filterRate(unionpayRate.getEditText());
        filterRate(largeUnionpayRate.getEditText());
        updateUI();
    }

    /**
     * 根据对界面重新赋值
     */
    private void updateUI() {
        if (inComing == null) {
            return;
        }
        wechatRate.setText(inComing.getWxQrcodeList());
        alipayRate.setText(inComing.getAliQrcodeList());
        unionpayRate.setText(inComing.getThousandQrcodeList());
        largeUnionpayRate.setText(inComing.getHighestQrcodeList());
    }


    /**
     * 过滤只能输入小数点两位数字
     */
    private void filterRate(EditText editText) {

        editText.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                if (dest.toString().length() == 0) {
                    if (source.length() == 1) {
                        if (source.equals(".")) {
                            return "0.";
                        } else if (source.equals("0")) {
                            return source;
                        } else {
                            return "0." + source;
                        }
                    }
                }
                if (dest.toString().contains(".")) {
                    int index = dest.toString().indexOf(".");
                    int length = dest.toString().substring(index).length();
                    if (length == 3) {
                        return "";
                    }
                }
                //以0开头不允许输入连续的0如00，000
                if (dest.toString().startsWith("0")) {
                    if (dest.toString().length() == 1 && !source.equals(".")) {
                        return "";
                    }
                }
                return null;
            }
        }});
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        if (wechatRate.isEmpty()) {
            ToastUtil.show("请输入微信费率");
            return;
        }
        if (Float.parseFloat(wechatRate.getText()) < 0.3 || Float.parseFloat(wechatRate.getText()) > 1) {
            ToastUtil.show("请输入微信费率：0.3～1.00");
            return;
        }
        if (alipayRate.isEmpty()) {
            ToastUtil.show("请输入支付宝费率");
            return;
        }
        if (Float.parseFloat(alipayRate.getText()) < 0.3 || Float.parseFloat(alipayRate.getText()) > 1) {
            ToastUtil.show("请输入支付宝费率：0.3～1.00");
            return;
        }
        if (unionpayRate.isEmpty()) {
            ToastUtil.show("请输入银联0-1000元费率");
            return;
        }
        if (Float.parseFloat(unionpayRate.getText()) < 0.3 || Float.parseFloat(unionpayRate.getText()) > 1) {
            ToastUtil.show("请输入银联0-1000元费率：0.3～1.00");
            return;
        }
        if (largeUnionpayRate.isEmpty()) {
            ToastUtil.show("请输入银联1000元以上费率");
            return;
        }
        if (Float.parseFloat(largeUnionpayRate.getText()) < 0.6 || Float.parseFloat(largeUnionpayRate.getText()) > 1) {
            ToastUtil.show("请输入银联1000元以上费率：0.6～1.00");
            return;
        }
        if (inComing == null) {
            inComing = new InComing();
        }
        inComing.setWxQrcodeList(wechatRate.getText());
        inComing.setAliQrcodeList(alipayRate.getText());
        inComing.setThousandQrcodeList(unionpayRate.getText());
        inComing.setHighestQrcodeList(largeUnionpayRate.getText());
        Intent intent = new Intent();
        intent.putExtra("incoming", inComing);
        setResult(RESULT_OK, intent);
        finish();
    }
}
