package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;
import java.util.List;

public class CateListBean {


    private List<CateBean> cate;

    public List<CateBean> getCate() {
        return cate;
    }

    public void setCate(List<CateBean> cate) {
        this.cate = cate;
    }

    public static class CateBean extends CoreBean {
        /**
         * id : 1
         * pid : 0
         * name : 美食
         * _child : [{"id":2,"pid":1,"name":"中式餐饮"},{"id":3,"pid":1,"name":"西式餐饮"},{"id":4,"pid":1,"name":"小吃快餐"},{"id":5,"pid":1,"name":"甜品饮料"},{"id":6,"pid":1,"name":"咖啡西餐"},{"id":7,"pid":1,"name":"烧烤自助"},{"id":8,"pid":1,"name":"烤鱼火锅"},{"id":9,"pid":1,"name":"排档海鲜"},{"id":10,"pid":1,"name":"港澳粤菜"},{"id":11,"pid":1,"name":"江浙沪菜"},{"id":12,"pid":1,"name":"川湘鄂菜"},{"id":13,"pid":1,"name":"闽菜客家"},{"id":14,"pid":1,"name":"新疆西北"},{"id":15,"pid":1,"name":"鲁菜京菜"},{"id":16,"pid":1,"name":"东北面食"},{"id":17,"pid":1,"name":"特色私房"},{"id":18,"pid":1,"name":"汤粥炖菜"},{"id":19,"pid":1,"name":"东南亚菜"},{"id":20,"pid":1,"name":"日韩料理"},{"id":21,"pid":1,"name":"面包甜品"},{"id":22,"pid":1,"name":"咖啡厅"},{"id":23,"pid":1,"name":"酒吧"},{"id":24,"pid":1,"name":"饮吧"},{"id":25,"pid":1,"name":"代金券"},{"id":26,"pid":1,"name":"聚餐宴请"},{"id":27,"pid":1,"name":"创意菜"}]
         */
        public int count = 0;
        private int id;
        private int pid;
        private String name;
        private boolean isSelect = false;
        private List<ChildBean> _child;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildBean> get_child() {
            return _child;
        }

        public void set_child(List<ChildBean> _child) {
            this._child = _child;
        }

        public static class ChildBean extends CoreBean {
            /**
             * id : 2
             * pid : 1
             * name : 中式餐饮
             */

            private int id;
            private int pid;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String toString(){
                return getName();
            }
        }
    }
}
