package com.oocl.packagebooking.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Packaging {

    public Packaging() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String billno;
    @Column
    private String receiver;
    @Column
    private String phonenum;
    @Column
    private String status = "未取件";
    @Column
    private Long apptime;
    @Column
    private Integer weight;

    public Packaging(String billno, String receiver, String phonenum, String status, Long apptime, Integer weight) {
        this.billno = billno;
        this.receiver = receiver;
        this.phonenum = phonenum;
        this.status = status;
        this.apptime = apptime;
        this.weight = weight;
    }

    public Packaging(String billno, String receiver, String phonenum) {
        this.billno = billno;
        this.receiver = receiver;
        this.phonenum = phonenum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getApptime() {
        return apptime;
    }

    public void setApptime(Long apptime) {
        this.apptime = apptime;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}