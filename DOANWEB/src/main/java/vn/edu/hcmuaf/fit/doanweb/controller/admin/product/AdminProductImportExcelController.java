package vn.edu.hcmuaf.fit.doanweb.controller.admin.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.poi.ss.usermodel.*;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.JDBCType.BOOLEAN;
import static java.sql.JDBCType.NUMERIC;
import static javax.management.openmbean.SimpleType.STRING;

@WebServlet(name = "AdminProductImportExcelController", value = "/admin/product/import-excel")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1 MB
        maxFileSize = 1024 * 1024 * 10,    // 10 MB
        maxRequestSize = 1024 * 1024 * 50  // 50 MB
)
public class AdminProductImportExcelController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService authService = new AuthService();
        List<String> errors = new ArrayList<>();
        int successCount = 0;
        int errorCount = 0;

        Part filePart = request.getPart("excelFile");
        if (filePart == null) {
            request.setAttribute("errorMessage", "Không tìm thấy file Excel!");
            request.getRequestDispatcher("/admin/product").forward(request, response);
            return;
        }

        String fileName = filePart.getSubmittedFileName();
        if (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
            request.setAttribute("errorMessage", "File không đúng định dạng Excel (.xlsx hoặc .xls)!");
            request.getRequestDispatcher("/admin/product").forward(request, response);
            return;
        }

        try (InputStream inputStream = filePart.getInputStream()) {
            Workbook workbook = null;

            if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else {
                workbook = new HSSFWorkbook(inputStream);
            }

            Sheet sheet = workbook.getSheetAt(0);

            // Skip header row
            int startRow = 1;

            for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    // Get cell values
                    String name = getCellValueAsString(row.getCell(0));
                    String img = getCellValueAsString(row.getCell(1));
                    double price = getCellValueAsDouble(row.getCell(2));
                    String title = getCellValueAsString(row.getCell(3));
                    String description = getCellValueAsString(row.getCell(4));
                    int cateID = (int) getCellValueAsDouble(row.getCell(5));
                    String offer = getCellValueAsString(row.getCell(6));

                    // Validate data
                    if (name == null || name.trim().isEmpty()) {
                        errors.add("Dòng " + (i + 1) + ": Tên sản phẩm không được để trống");
                        errorCount++;
                        continue;
                    }

                    if (price <= 0) {
                        errors.add("Dòng " + (i + 1) + ": Giá sản phẩm phải lớn hơn 0");
                        errorCount++;
                        continue;
                    }

                    // Insert product
                    boolean result = authService.insertProduct(name, img, price, title, description, cateID, offer);
                    if (result) {
                        successCount++;
                    } else {
                        errors.add("Dòng " + (i + 1) + ": Không thể thêm sản phẩm");
                        errorCount++;
                    }
                } catch (Exception e) {
                    errors.add("Dòng " + (i + 1) + ": " + e.getMessage());
                    errorCount++;
                }
            }

            workbook.close();
        }

        // Set attributes for response
        request.getSession().setAttribute("importSuccess", successCount);
        request.getSession().setAttribute("importErrors", errorCount);
        request.getSession().setAttribute("importErrorDetails", errors);

        response.sendRedirect(request.getContextPath() + "/admin/product");

    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
//            case FORMULA:
//                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
//                CellValue cellValue = evaluator.evaluate(cell);
//                return cellValue.formatAsString();
            default:
                return "";
        }
    }

    private double getCellValueAsDouble(Cell cell) {
        if (cell == null) return 0.0;

        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0.0;
                }
            case BOOLEAN:
                return cell.getBooleanCellValue() ? 1.0 : 0.0;
            case FORMULA:
                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                return cellValue.getNumberValue();
            default:
                return 0.0;
        }
    }
}