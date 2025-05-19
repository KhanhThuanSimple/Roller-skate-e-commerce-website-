package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.CategoryDao;
import vn.edu.hcmuaf.fit.doanweb.dao.ScreenDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Screen;

import java.sql.SQLException;
import java.util.ArrayList;

public class ScreenService {
    public boolean insertScreen(String idScreen,String nameScreen) throws SQLException {
        ScreenDao screenDao = new ScreenDao();
        return screenDao.insertScreen(idScreen,nameScreen);
    }
    public boolean updateScreen(String code,String name, int id) throws SQLException {
        ScreenDao screenDao = new ScreenDao();
        return screenDao.updateScreen(code,name,id);
    }
    public boolean deleteSreen(int id) throws SQLException {
        ScreenDao screenDao = new ScreenDao();
        return screenDao.deleteScreen(id);
    }
    public int getPageScreen( ) throws SQLException {
        ScreenDao screenDao = new ScreenDao();
        return screenDao.getPageScreen();
    }
    public ArrayList<Screen> getListScreen(int page) throws SQLException {
        ScreenDao screenDao = new ScreenDao();
        ArrayList<Screen> screens = screenDao.getListScreen(page);
        return screens;
    }
}
