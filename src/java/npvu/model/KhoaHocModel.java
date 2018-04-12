/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author npvu
 */
@Entity
@Table(name = "taikhoan")
public class KhoaHocModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "khoahoc_khoahoc_id_seq")
    @SequenceGenerator(name = "khoahoc_khoahoc_id_seq", sequenceName = "khoahoc_khoahoc_id_seq")
    @Column(name = "khoahoc_id", unique = true, nullable = false)
    private int khoahoc_id;
    
    @Column(name = "khoahoc_ma")
    private String ma;
    
    @Column(name = "khoahoc_ten")
    private String ten;
    
    @Column(name = "khoahoc_noidung")
    private String noiDung;
    
    @Column(name = "khoahoc_ngaykhaigiang")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayKhaiGiang;
    
    @Column(name = "khoahoc_ngaytao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayTao;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayKhaiGiang() {
        return ngayKhaiGiang;
    }

    public void setNgayKhaiGiang(Date ngayKhaiGiang) {
        this.ngayKhaiGiang = ngayKhaiGiang;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public int getKhoahoc_id() {
        return khoahoc_id;
    }

    public void setKhoahoc_id(int khoahoc_id) {
        this.khoahoc_id = khoahoc_id;
    }
    
}
