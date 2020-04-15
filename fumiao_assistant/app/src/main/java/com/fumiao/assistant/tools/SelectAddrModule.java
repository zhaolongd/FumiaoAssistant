package com.fumiao.assistant.tools;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.home.AddrBean;
import com.fumiao.assistant.bean.home.AeraBean;
import com.fumiao.assistant.bean.home.AreasBean;
import com.fumiao.assistant.config.HttpConfig;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.Callback;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by chee on 2018/9/1.
 */
public class SelectAddrModule {

    OptionsPickerView pvOptions;
    private ArrayList<AeraBean> aeraBeans = new ArrayList<>();
    private ArrayList<ArrayList<AeraBean.RegionEntitysBeanX>> regionEntitysBeanXs = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<AeraBean.RegionEntitysBeanX.RegionEntitysBean>>> regionEntitysBeans = new ArrayList<>();

    Activity context;

    private int option1 = 0;
    private int option2 = 0;
    private int option3 = 0;

    public SelectAddrModule(Activity context, Callback<String> callback) {
        this.context = context;
        getArea(callback);
    }

    public void showPickerView(final Callback<String> callback, View btnAddr, ViewGroup decorView) {// 弹出选择器

        pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                boolean t2State = false, t3State = false;
                t2State = regionEntitysBeanXs.get(options1)!= null && regionEntitysBeanXs.get(options1).size() != 0;
                if (t2State) {
                    t3State = t2State
                            &&
                            regionEntitysBeans.get(options1).get(options2) != null
                            &&
                            regionEntitysBeans.get(options1).get(options2).size() != 0;
                }
                callback.onSuccess(
                        aeraBeans.get(options1).getRegion(),
                        aeraBeans.get(options1).getRegion_code(),
                        regionEntitysBeanXs.get(options1).get(options2) != null ? regionEntitysBeanXs.get(options1).get(options2).getRegion() : "",
                        regionEntitysBeanXs.get(options1).get(options2) != null ? regionEntitysBeanXs.get(options1).get(options2).getCode() : "",
                        (t2State && t3State) ? regionEntitysBeans.get(options1).get(options2).get(options3).getRegion() : "",
                        (t2State && t3State) ? regionEntitysBeans.get(options1).get(options2).get(options3).getCode() : "");
                        option1 = options1;
                        option2 = options2;
                        option3 = options3;
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(context.getResources().getColor(R.color.colorAccent))
                .setTextColorCenter(context.getResources().getColor(R.color.colorAccent))
                .setLineSpacingMultiplier(3.0f)
                .setDecorView(decorView) //必须是RelativeLayout，不设置setDecorView的话，底部虚拟导航栏会显示在弹出的选择器区域
                .setLayoutRes(R.layout.dialog_addr, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView titleTv = v.findViewById(R.id.tvTitle);
                        v.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });
                        v.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.dismiss();
                            }
                        });
                    }
                })
                .build();
        if(aeraBeans.size() != 0){
            pvOptions.setPicker(aeraBeans, regionEntitysBeanXs, regionEntitysBeans);//三级选择器
        }
        Dialog mDialog = pvOptions.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvOptions.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
        pvOptions.setSelectOptions(option1, option2, option3);
        pvOptions.show(btnAddr);
    }

    public ArrayList<AddrBean> parseData(String result) {//Gson 解析
        ArrayList<AddrBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                AddrBean entity = gson.fromJson(data.optJSONObject(i).toString(), AddrBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    public void getArea(Callback<String> callback) {
        OkGo.<BaseResponse<AreasBean>>get(HttpConfig.AREA)
                .execute(new JsonCallback<BaseResponse<AreasBean>>(context) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<AreasBean>> response) {
                        aeraBeans.addAll(response.body().data.getAreas());
                        for (int i = 0; i < aeraBeans.size(); i++) {
                            ArrayList<AeraBean.RegionEntitysBeanX> regionEntitysBeanXES = new ArrayList<>();
                            ArrayList<ArrayList<AeraBean.RegionEntitysBeanX.RegionEntitysBean>> arrayLists = new ArrayList<>();
                            if (aeraBeans.get(i).getRegionEntitys() != null) {
                                for (int j = 0; j < aeraBeans.get(i).getRegionEntitys().size(); j++) {
                                    ArrayList<AeraBean.RegionEntitysBeanX.RegionEntitysBean> regionEntitysBeans = new ArrayList<>();
                                    if (aeraBeans.get(i).getRegionEntitys().get(j).getRegionEntitys() != null) {
                                        regionEntitysBeans.addAll(aeraBeans.get(i).getRegionEntitys().get(j).getRegionEntitys());
                                    } else {
                                        regionEntitysBeans.addAll(new ArrayList<AeraBean.RegionEntitysBeanX.RegionEntitysBean>());
                                    }
                                    regionEntitysBeanXES.add(aeraBeans.get(i).getRegionEntitys().get(j));
                                    arrayLists.add(regionEntitysBeans);
                                }
                            } else {
                                regionEntitysBeanXES.add(new AeraBean.RegionEntitysBeanX());
                                arrayLists.add(new ArrayList<AeraBean.RegionEntitysBeanX.RegionEntitysBean>());
                            }
                            regionEntitysBeanXs.add(regionEntitysBeanXES);
                            regionEntitysBeans.add(arrayLists);
                        }
                    }
                });
    }

}
