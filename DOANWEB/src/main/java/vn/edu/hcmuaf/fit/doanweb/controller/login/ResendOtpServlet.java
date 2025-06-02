package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;

@WebServlet("/resend-otp")
public class ResendOtpServlet extends HttpServlet {

    private static final long OTP_VALID_DURATION = 2 * 60 * 1000; // 2 phút

    private String generateOtp() {
        int otp = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }

    private void sendOtpEmail(String email, String otp) throws Exception {
        // Gọi hàm gửi mail của bạn (EmailUtil hoặc tương tự)
        EmailUtil.sendEmail(email, "Mã OTP đăng nhập của bạn", "Mã OTP của bạn là: " + otp + ". Vui lòng không chia sẻ mã này với ai.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("pendingUser") == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Phiên làm việc hết hạn hoặc không hợp lệ. Vui lòng đăng nhập lại.");
            return;
        }

        User pendingUser = (User) session.getAttribute("pendingUser");

        String newOtp = generateOtp();

        try {
            sendOtpEmail(pendingUser.getEmail(), newOtp);

            session.setAttribute("otp", newOtp);
            session.setAttribute("otpCreationTime", System.currentTimeMillis());
            session.removeAttribute("otpAttempts"); // reset số lần nhập sai

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Mã OTP mới đã được gửi đến email của bạn.");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Lỗi khi gửi mã OTP. Vui lòng thử lại sau.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển hướng về trang nhập OTP nếu gọi GET
        request.getRequestDispatcher("verify-otp.jsp").forward(request, response);
    }
}
