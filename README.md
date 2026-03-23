🛼 Roller Skate E-commerce Website
📌 Overview

This project is a fullstack e-commerce platform designed for selling roller skates and related accessories.
It simulates a real-world online shopping system with complete business workflows, including product management, order lifecycle handling, and secure payment processing.

The system is built with a focus on scalability, maintainability, and real-world integration, especially in handling payment callbacks and transactional data consistency.

🔥 Developed as a Team Project, where I acted as Team Leader, responsible for system architecture, backend development, and integration of core business features.

🚀 Key Features
🛍️ Customer Features
Browse products by categories and filters
View detailed product information (price, stock, description)
Search products by keyword
Add/remove/update items in shopping cart
Place orders with full checkout flow
Secure online payment via VNPay
Track order status (Pending → Paid → Shipping → Completed)
🔐 Authentication & Authorization
User registration & login system
Session-based authentication
Role-based access control (User / Admin)
💳 Payment Integration
Integrated VNPay payment gateway
Secure transaction handling
Implemented callback endpoint (vnpay_return)
Automatic order status update after payment confirmation
🧑‍💼 Admin Dashboard
Manage products (CRUD)
Manage categories
Manage orders and update status
Monitor transaction flow
🏗️ System Architecture

The application follows a layered architecture:

Presentation Layer: JSP (View)
Controller Layer: Servlet (Request handling)
Business Logic Layer: Service/DAO
Data Layer: MySQL Database
Key Design Highlights:
Designed ERD with 15+ relational tables
Normalized database to ensure data consistency
Structured backend with clear separation of concerns
Implemented REST-like endpoints for frontend-backend communication
🔄 Order Processing Workflow
User adds products to cart
Proceeds to checkout
Selects VNPay payment
System generates payment request
VNPay processes transaction
Callback (vnpay_return) updates order status
Order marked as Paid / Failed
🧠 My Contributions
👨‍💻 Led a team of 3 developers and managed task distribution
🧩 Designed system architecture, ERD, and data relationships
⚙️ Developed backend logic using Java (JSP/Servlet)
💳 Integrated VNPay API and handled secure payment callbacks
⚡ Optimized MySQL queries for large product datasets
🔗 Built full product module (frontend & backend)
🗂️ Managed Git workflow and version control
🛠️ Technologies Used
🔹 Frontend
HTML5, CSS3, JavaScript
🔹 Backend
Java (JSP/Servlet)
🔹 Database
MySQL
🔹 Tools & Integrations
Git & GitHub
Postman (API testing)
VNPay Payment Gateway
📊 Outcome
✅ Developed a fully functional e-commerce system with complete order lifecycle and payment flow
✅ Successfully handled real-world payment scenarios, including asynchronous callbacks
✅ Improved system performance through query optimization
✅ Ensured data consistency during transaction processing
📂 Project Structure
src/
 ├── controller/
 ├── dao/
 ├── model/
 ├── service/

web/
 ├── views/ (JSP)
 ├── assets/ (CSS, JS)
⚙️ Installation & Setup
1. Clone repository
git clone https://github.com/your-username/roller-skate-ecommerce.git
2. Setup Database
Import database from provided SQL file
Configure database connection in project
3. Run Application
Deploy project on Apache Tomcat
Access via:
http://localhost:8080/your-project
🔮 Future Improvements
Upgrade to Spring Boot (RESTful API architecture)
Implement JWT Authentication
Add AI-based product recommendation
Improve scalability and performance (caching, pagination)
Build mobile app using React Native
👤 Author

Do Khanh Thuan

GitHub: https://github.com/KhanhThuanSimple
Email: thuan.dokhanh04@gmail.com
⭐ Highlights

This project demonstrates:

Fullstack web development
Backend system design
Payment gateway integration
Real-world transaction handling
Team leadership and collaboration
