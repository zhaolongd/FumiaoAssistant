package com.fumiao.assistant.app;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.fumiao.assistant.config.HttpConfig;
import com.fumiao.assistant.config.KeyConfig;
import com.fumiao.core.app.CoreFragment;

/**
 * Created by zhaolong on 2019/9/10.
 */
public class BaseFragment extends CoreFragment implements KeyConfig, HttpConfig {

   public View root;

   public void jumpActivity(String activityUrl) {
      if (activityUrl == null || activityUrl.equals("")) {
         Toast.makeText(getActivity(),"功能暂未开放", Toast.LENGTH_SHORT).show();
         return;
      }
      try {
         Class clz = Class.forName(activityUrl);
         startActivity(new Intent(getContext(), clz));
      } catch (ClassNotFoundException e) {
         Toast.makeText(getActivity(),"功能暂未开放", Toast.LENGTH_SHORT).show();
      }
   }

}
