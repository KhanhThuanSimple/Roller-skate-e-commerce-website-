package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.RightsDao;
import vn.edu.hcmuaf.fit.doanweb.dao.ScreenDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Rights;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Screen;

import java.sql.SQLException;
import java.util.ArrayList;

public class RightsService {
    public boolean insertRights(int id,String name) throws SQLException {
        RightsDao rightsDao = new RightsDao();
        return rightsDao.insertRights(id,name);
    }
    public boolean updateRights(String name, int id) throws SQLException {
        RightsDao rightsDao = new RightsDao();
        return rightsDao.updateRights(name,id);
    }
    public boolean deleteRights(int id) throws SQLException {
        RightsDao rightsDao = new RightsDao();
        return rightsDao.deleteRights(id);
    }
    public int getPageRights( ) throws SQLException {
        RightsDao rightsDao = new RightsDao();
        return rightsDao.getPageRights();
    }
    public ArrayList<Rights> getListRights(int page) throws SQLException {
        RightsDao rightsDao = new RightsDao();
        ArrayList<Rights> rightss = rightsDao.getListRights(page);
        return rightss;
    }
}
