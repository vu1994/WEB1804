/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.dataprovider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import npvu.model.KhoaHocModel;
import npvu.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author npvu
 */
public class KhoaHocDataProvider implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(KhoaHocDataProvider.class);
    
    public List<Map> getDanhSachKhoaHoc(){
        Session session = HibernateUtil.currentSession();
        List<Map> dsKhoaHoc = new ArrayList();
        try {
            session.beginTransaction();
            dsKhoaHoc = session.createSQLQuery(" SELECT * "
                    + " FROM khoahoc ORDER BY khoahoc_ngaytao DESC").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            session.getTransaction().commit();
            int stt = 1;
            for(Map khoaHoc : dsKhoaHoc){
                khoaHoc.put("stt", stt);
                stt++;
            }
	} catch (Exception e) {
            log.error(">>> Lỗi lấy danh sách khóa học {}", e);
	} finally {
            session.close();
	}
        return dsKhoaHoc;
    }
    
    public boolean updateKhoaHoc(KhoaHocModel khoaHocModel){
        Session session = HibernateUtil.currentSession();
        try {
            session.beginTransaction();
            if(khoaHocModel.getKhoahoc_id()!= 0){
                
            } else {
                session.createSQLQuery("INSERT INTO "
                        + " khoahoc(khoahoc_ten, khoahoc_noidung, khoahoc_ngaykhaigiang, khoahoc_ngaytao)"
                        + " VALUES(:ten, :noidung, :ngaykhaigiang, :ngaytao)")
                        .setString("ten", khoaHocModel.getTen())
                        .setString("noidung", khoaHocModel.getNoiDung())
                        .setDate("ngaykhaigiang", khoaHocModel.getNgayKhaiGiang())
                        .setDate("ngaytao", khoaHocModel.getNgayTao())
                        .executeUpdate();
            }
            session.getTransaction().commit();
	} catch (Exception e) {            
            log.error(">>> Lỗi Cập nhật khóa học: "+khoaHocModel.getTen()+" {}", e);
            return false;
	} finally {
            session.close();
	}
        return true;
    }
    
    public boolean deleteKhoaHoc(KhoaHocModel mapKhoaHoc){
        Session session = HibernateUtil.currentSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM khoahoc WHERE khoahoc_id = "+mapKhoaHoc.getKhoahoc_id())
                    .executeUpdate();
            session.getTransaction().commit();
	} catch (Exception e) {            
            log.error(">>> Lỗi Xóa khóa học: "+mapKhoaHoc.getTen()+" {}", e);
            return false;
	} finally {
            session.close();
	}
        return true;
    }
}
