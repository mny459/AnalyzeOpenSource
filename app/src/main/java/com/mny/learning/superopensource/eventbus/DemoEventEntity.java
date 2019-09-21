package com.mny.learning.superopensource.eventbus;

/**
 * @author mny on 2019-06-02.
 * Email：mny9@outlook.com
 * Desc:
 */
public class DemoEventEntity {
    private String desc;

    public DemoEventEntity(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "DemoEventEntity{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
