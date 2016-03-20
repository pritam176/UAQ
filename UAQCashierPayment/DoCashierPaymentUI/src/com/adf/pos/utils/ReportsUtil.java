package com.adf.pos.utils;


import com.bea.security.xacml.Logger;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.HashMap;

import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.DBTransaction;


public class ReportsUtil {
    //private static Logger log = Logger.getLogger(ReportsUtil.class);
    //    private static Map<String, String> config =
    //        TanmeyahSingleTone.getInstance().getConfig();

    public ReportsUtil() {
        super();
    }

    //  private static ResourceBundle properties = ResourceBundle.getBundle(TanmConstants.CONFIG_FILE_PATH);

    public static Connection getAppModuleConnection(ApplicationModuleImpl appModule) {
        try {
            DBTransaction n = appModule.getDBTransaction();
            return n.createStatement(0).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void GenerateReportExcel(HashMap parameterMap,
                                           String jasperFileNm, boolean subDir,
                                           boolean imgDir, Connection conn,
                                           OutputStream outputStream, String serviceId) {

        boolean jdbcConnection = false;
        try {
            JasperPrint jasperPrint = new JasperPrint();
            HttpServletResponse response =
                (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //            String japserFilesPath = "C:\\Egabi work\\jasperReports\\";
            //            String japserFilesPath = "D:/rep/";

            String japserFilesPath = "/u01/app/oracle/product/jasperReports/";
            response.setContentType("application/pdf; charset=Cp1252");

            // response.addHeader("Content-Disposition",
            //                  "attachment;filename=" + jasperFileNm + ".pdf");
            String jasperFile = japserFilesPath + jasperFileNm + ".jasper";
            FileInputStream reportStream = new FileInputStream(jasperFile);
            if (subDir)
                parameterMap.put("SUBREPORT_DIR", japserFilesPath);
                if (imgDir){
                    String jasperImgPath = japserFilesPath + File.separator + "jasperimg" + File.separator + serviceId + ".png";
                    parameterMap.put("IMG_PATH", jasperImgPath);
                }

            if (conn == null) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn =
DriverManager.getConnection("jdbc:oracle:thin:@172.17.1.222:1521:tsteng",
                            "moieng", "moieng222");
                jdbcConnection = true;
            }
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 conn);
            //            JRFiller.fillReport(reportStream, parameterMap,
            //                                                 conn);
            ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();


            //            JRRtfExporter exporter = new JRRtfExporter();
            JRXlsExporter exporter = new JRXlsExporter();


            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,
                                  jasperPrint);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
                                  outputStream);
            exporter.exportReport();


            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.flush();
            outputStream.close();


        } catch (FileNotFoundException e) {
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jdbcConnection && conn != null)
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

    }

    public static void GenerateReportPDF(HashMap parameterMap,
                                         String jasperFileNm, boolean subDir,
                                         boolean imgDir, Connection conn,
                                         OutputStream outputStream, String serviceId) {
        boolean jdbcConnection = false;
        try {
            JasperPrint jasperPrint = new JasperPrint();
            HttpServletResponse response =
                (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        ServletContext ext = (ServletContext)JSFUtils.getFacesContext().getExternalContext().getContext();
        String realPath = ext.getRealPath("/");
        String japserFilesPath = realPath + File.separator + "jasper" + File.separator;
            response.setContentType("application/pdf; charset=Cp1252");
            String jasperFile = japserFilesPath + jasperFileNm + ".jasper";
            FileInputStream reportStream = new FileInputStream(jasperFile);
            if (subDir)
                parameterMap.put("SUBREPORT_DIR", japserFilesPath);
            if (imgDir){
                String jasperImgPath = realPath + File.separator + "jasperimg" + File.separator + serviceId + ".png";
                parameterMap.put("IMG_PATH", jasperImgPath);
            }
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 conn);
            ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();

            JRPdfExporter exporter = new JRPdfExporter();

            exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT,
                                  jasperPrint);
            exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM,
                                  outputStream);
            exporter.exportReport();
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jdbcConnection && conn != null)
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

    }
}

