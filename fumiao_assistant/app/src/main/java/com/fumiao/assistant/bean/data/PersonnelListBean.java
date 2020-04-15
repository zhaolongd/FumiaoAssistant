package com.fumiao.assistant.bean.data;

import java.util.List;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/17 0017 13:44
 */
public class PersonnelListBean {
    private List<PersonnelBean> people_list;
    private int total;

    public List<PersonnelBean> getPeople_list() {
        return people_list;
    }

    public void setPeople_list(List<PersonnelBean> people_list) {
        this.people_list = people_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
