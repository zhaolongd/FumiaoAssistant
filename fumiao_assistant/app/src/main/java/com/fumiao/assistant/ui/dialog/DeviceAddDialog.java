package com.fumiao.assistant.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.core.ui.CoreDialog;

public class DeviceAddDialog extends CoreDialog {
    TextView btn_add_box, btn_add_card;

    public DeviceAddDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_device_add);
        btn_add_box = findViewById(R.id.btn_add_box);
        btn_add_card = findViewById(R.id.btn_add_card);
    }

    public void show(View.OnClickListener boxClick, View.OnClickListener cardClick) {
        btn_add_box.setOnClickListener(boxClick);
        btn_add_card.setOnClickListener(cardClick);
        super.show();
    }

}
