/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import npvu.dataprovider.KhoaHocDataProvider;
import npvu.model.KhoaHocModel;
import npvu.util.DateUtils;
import npvu.util.ShowGrowlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author npvu
 */
@ManagedBean(name="KhoaHocController")
@ViewScoped
public class KhoaHocController implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(KhoaHocController.class);
    private ShowGrowlUtils showGrowl = new ShowGrowlUtils();
    private final KhoaHocDataProvider khoaHocProvider = new KhoaHocDataProvider();
    private List<Map> listKhoaHoc = new ArrayList<>();
    private KhoaHocModel khoaHocModel = new KhoaHocModel();
    private int viewMode = 0;
    
    public KhoaHocController() {
        log.info("<<< Khởi tạo khóa học controller >>>");
        actionGetListKhoaHoc();
    }
    
    public void selectKhoaHoc(Map mapKhoaHoc){
        khoaHocModel = new KhoaHocModel();
        if(mapKhoaHoc != null){
            khoaHocModel.setKhoahoc_id(Integer.parseInt(mapKhoaHoc.get("khoahoc_id").toString()));
            khoaHocModel.setTen(mapKhoaHoc.get("khoahoc_ten").toString());
            khoaHocModel.setNoiDung(mapKhoaHoc.get("khoahoc_noidung").toString());
            khoaHocModel.setNgayKhaiGiang((Date) mapKhoaHoc.get("khoahoc_ngaykhaigiang"));
            khoaHocModel.setNgayTao((Date) mapKhoaHoc.get("khoahoc_ngaytao"));
        } else {
            showGrowl.showMessageError("Khóa học không tồn tại");
        }
    }
    
    private void actionGetListKhoaHoc(){
        listKhoaHoc = khoaHocProvider.getDanhSachKhoaHoc();
        viewMode = 0;
    }
    
    public void preActionThemKhoaHoc(){
        khoaHocModel = new KhoaHocModel();
        khoaHocModel.setNgayKhaiGiang(DateUtils.getCurrentDate());
        viewMode = 1;
    }
    
    public void actionCapNhatKhoaHoc(){
        if(vaildForm()){
            khoaHocModel.setNgayTao(DateUtils.getCurrentDate());            
            if(khoaHocProvider.updateKhoaHoc(khoaHocModel)){
                showGrowl.showMessageSuccess("Cập nhật khóa học thành công.");
                actionGetListKhoaHoc();
            } else {
                showGrowl.showMessageError("Cập nhật khóa học thất bại.");
            }
        }
    }
    
    public void actionXoaKhoaHoc(KhoaHocModel objKhoaHoc){
        if(khoaHocProvider.deleteKhoaHoc(objKhoaHoc)){
            showGrowl.showMessageSuccess("Xóa khóa học '"+objKhoaHoc.getTen()+"' thành công.");
            actionGetListKhoaHoc();
        } else {
                showGrowl.showMessageError("Xóa khóa học '"+objKhoaHoc.getTen()+"' thất bại.");
            }
    }
    
    public boolean vaildForm(){
        boolean validation = true;
        Map msg;
        List<Map> listMsg;
//        if(khoaHocModel.getTen() == null || khoaHocModel.getTen().isEmpty() || khoaHocModel.getTen().length() == 0){
//            validation = false;
//            
//        }
        return validation;
    }
    
    public void changeViewMode(int mode){
        viewMode = mode;
    }
    
    /**
     * Getter & Setter     
     * @return 
     */
    
    public List<Map> getListKhoaHoc() {
        return listKhoaHoc;
    }

    public void setListKhoaHoc(List<Map> listKhoaHoc) {
        this.listKhoaHoc = listKhoaHoc;
    }

    public int getViewMode() {
        return viewMode;
    }

    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;
    }

    public KhoaHocDataProvider getKhoaHocProvider() {
        return khoaHocProvider;
    }

    public KhoaHocModel getKhoaHocModel() {
        return khoaHocModel;
    }

    public void setKhoaHocModel(KhoaHocModel khoaHocModel) {
        this.khoaHocModel = khoaHocModel;
    }

    public ShowGrowlUtils getShowGrowl() {
        return showGrowl;
    }

    public void setShowGrowl(ShowGrowlUtils showGrowl) {
        this.showGrowl = showGrowl;
    }
}
