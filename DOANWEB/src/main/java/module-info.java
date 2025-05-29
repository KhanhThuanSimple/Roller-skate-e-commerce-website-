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
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires org.apache.poi.ooxml;
    requires org.apache.logging.log4j; // Để sử dụng ObjectMapper

}