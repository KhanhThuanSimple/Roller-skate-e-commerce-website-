package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.ImportDao;
import vn.edu.hcmuaf.fit.doanweb.dao.StocksDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ImportOrders;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class ImportService {

    public ImportOrders findById(int id ) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.findById(id);
    }

    public boolean deleteImport(int id) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.deleteImport(id);
    }


}
