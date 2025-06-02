package vn.edu.hcmuaf.fit.doanweb.controller.admin.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "ExcelTemplateServlet", value = "/templates/product_import_template.xlsx")
public class ExcelTemplateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=product_import_template.xlsx");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Product Import");

            // Create headers
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Nhà Sản Xuất", "Link Hình Ảnh", "Giá", "Tên Sản Phẩm", "Mô Tả", "ID Loại Sản Phẩm", "Khuyến Mãi"};

            // Create header style
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);

            // Set headers
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
                sheet.autoSizeColumn(i);
            }

            // Create example row
            Row exampleRow = sheet.createRow(1);
            exampleRow.createCell(0).setCellValue("Nike");
            exampleRow.createCell(1).setCellValue("https://example.com/images/shoe1.jpg");
            exampleRow.createCell(2).setCellValue(1500000);
            exampleRow.createCell(3).setCellValue("Giày Nike Air Max");
            exampleRow.createCell(4).setCellValue("Giày thể thao cao cấp, phù hợp cho các hoạt động thể thao");
            exampleRow.createCell(5).setCellValue(1);
            exampleRow.createCell(6).setCellValue("Tặng kèm vớ");

            // Add column descriptions
            Sheet instructionSheet = workbook.createSheet("Hướng Dẫn");
            Row instructionHeader = instructionSheet.createRow(0);
            instructionHeader.createCell(0).setCellValue("Hướng dẫn nhập sản phẩm");

            Row instructionRow1 = instructionSheet.createRow(1);
            instructionRow1.createCell(0).setCellValue("1. Nhà Sản Xuất: Tên nhà sản xuất (bắt buộc)");

            Row instructionRow2 = instructionSheet.createRow(2);
            instructionRow2.createCell(0).setCellValue("2. Link Hình Ảnh: Đường dẫn đến hình ảnh sản phẩm");

            Row instructionRow3 = instructionSheet.createRow(3);
            instructionRow3.createCell(0).setCellValue("3. Giá: Giá sản phẩm (bắt buộc, phải là số dương)");

            Row instructionRow4 = instructionSheet.createRow(4);
            instructionRow4.createCell(0).setCellValue("4. Tên Sản Phẩm: Tên hiển thị của sản phẩm (bắt buộc)");

            Row instructionRow5 = instructionSheet.createRow(5);
            instructionRow5.createCell(0).setCellValue("5. Mô Tả: Mô tả chi tiết về sản phẩm");

            Row instructionRow6 = instructionSheet.createRow(6);
            instructionRow6.createCell(0).setCellValue("6. ID Loại Sản Phẩm: Mã danh mục sản phẩm (bắt buộc, phải là số nguyên)");

            Row instructionRow7 = instructionSheet.createRow(7);
            instructionRow7.createCell(0).setCellValue("7. Khuyến Mãi: Thông tin về các ưu đãi, khuyến mãi kèm theo");

            // Auto-size columns
            for (int i = 0; i < 7; i++) {
                sheet.autoSizeColumn(i);
            }
            instructionSheet.autoSizeColumn(0);

            // Write to response
            try (OutputStream outputStream = response.getOutputStream()) {
                workbook.write(outputStream);
            }
        }
    }
}