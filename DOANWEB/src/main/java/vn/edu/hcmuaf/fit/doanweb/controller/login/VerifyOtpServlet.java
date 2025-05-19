package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;

@WebServlet("/verify-otp")
public class VerifyOtpServlet extends HttpServlet {

    private static final long OTP_VALID_DURATION = 2 * 60 * 1000; // 2 phút
    private static final int MAX_OTP_ATTEMPTS = 3;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String inputOtp = request.getParameter("otp");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("otp") == null || session.getAttribute("pendingUser") == null) {
            request.setAttribute("error", "Phiên làm việc hết hạn. Vui lòng đăng nhập lại.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        Object otpCreationObj = session.getAttribute("otpCreationTime");
        if (!(otpCreationObj instanceof Long)) {
            session.invalidate();
            request.setAttribute("error", "Phiên làm việc không hợp lệ. Vui lòng đăng nhập lại.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        long otpCreationTime = (Long) otpCreationObj;

        if (inputOtp == null || inputOtp.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập mã OTP");
            request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
            return;
        }

        if (isOtpExpired(otpCreationTime)) {
            session.invalidate();
            request.setAttribute("error", "Mã OTP đã hết hạn. Vui lòng thử lại.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String sessionOtp = (String) session.getAttribute("otp");

        if (!sessionOtp.equals(inputOtp)) {
            // Lấy số lần thử hiện tại, nếu chưa có thì khởi tạo 0
            Integer otpAttempts = (Integer) session.getAttribute("otpAttempts");
            if (otpAttempts == null) {
                otpAttempts = 0;
            }
            otpAttempts++;

            if (otpAttempts >= MAX_OTP_ATTEMPTS) {
                // Quá số lần thử, xóa session và yêu cầu đăng nhập lại
                session.invalidate();
                request.setAttribute("error", "Bạn đã nhập sai OTP quá 3 lần. Vui lòng đăng nhập lại.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            } else {
                // Cập nhật lại số lần thử trong session
                session.setAttribute("otpAttempts", otpAttempts);
                request.setAttribute("error", "Mã OTP không đúng. Bạn còn " + (MAX_OTP_ATTEMPTS - otpAttempts) + " lần thử.");
                request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
                return;
            }
        }

        // Nếu OTP đúng thì xóa các thuộc tính liên quan đến OTP và số lần thử
        session.removeAttribute("otp");
        session.removeAttribute("otpCreationTime");
        session.removeAttribute("otpAttempts");
        User pendingUser = (User) session.getAttribute("pendingUser");
        session.removeAttribute("pendingUser");

        session.setAttribute("auth", pendingUser);
        session.setAttribute("user", pendingUser.getId());

        if (pendingUser.getType() == 1) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    private boolean isOtpExpired(long otpCreationTime) {
        return System.currentTimeMillis() - otpCreationTime > OTP_VALID_DURATION;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển hướng về trang nhập OTP để tránh lỗi 405
        request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
    }
}
