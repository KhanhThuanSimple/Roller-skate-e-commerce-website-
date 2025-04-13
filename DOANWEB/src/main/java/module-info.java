module DOANWEB {
    requires com.google.gson;
    requires jakarta.servlet;
    requires jakarta.servlet.jsp.jstl;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires java.sql;
    requires org.apache.httpcomponents.httpclient.fluent;
    requires mysql.connector.j;
    requires org.jdbi.v3.core; // Nhập khẩu HttpClient
    requires org.apache.logging.log4j;
    requires jakarta.mail;
}