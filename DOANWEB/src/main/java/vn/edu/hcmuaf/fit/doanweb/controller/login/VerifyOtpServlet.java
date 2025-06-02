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
            request.setAttribute("otpModal", true);
            request.setAttribute("otpError", "Vui lòng nhập mã OTP");
            request.setAttribute("emailForOtp", ((User) session.getAttribute("pendingUser")).getEmail());
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
            Integer otpAttempts = (Integer) session.getAttribute("otpAttempts");
            if (otpAttempts == null) {
                otpAttempts = 0;
            }
            otpAttempts++;

            if (otpAttempts >= MAX_OTP_ATTEMPTS) {
                session.invalidate();
                request.setAttribute("error", "Bạn đã nhập sai OTP quá 3 lần. Vui lòng đăng nhập lại.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            } else {
                session.setAttribute("otpAttempts", otpAttempts);
                request.setAttribute("otpModal", true);
                request.setAttribute("otpError", "Mã OTP không đúng. Bạn còn " + (MAX_OTP_ATTEMPTS - otpAttempts) + " lần thử.");
                request.setAttribute("emailForOtp", ((User) session.getAttribute("pendingUser")).getEmail());
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        }

        // OTP hợp lệ, xóa các thuộc tính liên quan OTP
        session.removeAttribute("otp");
        session.removeAttribute("otpCreationTime");
        session.removeAttribute("otpAttempts");

        User pendingUser = (User) session.getAttribute("pendingUser");
        session.removeAttribute("pendingUser");

        session.setAttribute("auth", pendingUser);
        session.setAttribute("user", pendingUser.getId());

        // Chuyển về trang home sau khi xác thực OTP thành công
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private boolean isOtpExpired(long otpCreationTime) {
        return System.currentTimeMillis() - otpCreationTime > OTP_VALID_DURATION;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển hướng về trang login để tránh lỗi 405 và mở modal nếu cần
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
