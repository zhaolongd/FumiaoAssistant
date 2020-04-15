package com.fumiao.assistant.bean.home;


public class DeviceDetailBean {
    private DeviceInfoBean device_info;

    public DeviceInfoBean getDevice_info() {
        return device_info;
    }

    public void setDevice_info(DeviceInfoBean device_info) {
        this.device_info = device_info;
    }

    /**
     *"id": 10,
     *"type": 1,
     *"name": "111",
     *"sn": "KD5800G8935",
     *"union_qrcode_number": "FM2003373949991",
     *"vendor": "11111",
     *"status": 2,
     *"isdel": 0,
     *"update_time": 0,
     *"is_vendor_bind": 0,
     *"vendor_info": "",
     *"add_time": 1573454329,
     *"bind_time": "2019-11-11 15:33:08",
     *"store_name": "我只做鸭",
     * "store_id": 252,
     *"type_name": "语音盒子",
     *"status_name": "已激活"
     */
    public static class DeviceInfoBean{
        private String name;
        private String sn;
        private String union_qrcode_number;
        private String vendor;
        private String bind_time;
        private String store_name;
        private String store_id;
        private String type_name;
        private String status_name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getUnion_qrcode_number() {
            return union_qrcode_number;
        }

        public void setUnion_qrcode_number(String union_qrcode_number) {
            this.union_qrcode_number = union_qrcode_number;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getBind_time() {
            return bind_time;
        }

        public void setBind_time(String bind_time) {
            this.bind_time = bind_time;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }
    }
}
