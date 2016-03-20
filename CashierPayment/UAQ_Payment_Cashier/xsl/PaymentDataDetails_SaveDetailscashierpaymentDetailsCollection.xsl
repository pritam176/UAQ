<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="../xsd/PaymentData.xsd"/>
      <rootElement name="Input" namespace="http://PaymentData"/>
      <param name="PaymentDataDetails" />
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="../xsd/Cashier_Payment_Details_Service_table.xsd"/>
      <rootElement name="CashierPaymentDetailsCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/Cashier_Payment_Details_Service"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.7.8(build 150622.2350.0222) AT [SUN JAN 24 16:54:07 GST 2016]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/top/Cashier_Payment_Details_Service"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:pymnt="http://PaymentData"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl pymnt xsd ns0 bpws xp20 mhdr bpel oraext dvm hwf med ids bpm xdk xref bpmn ora socket ldap">
  <xsl:template match="/">
    <ns0:CashierPaymentDetailsCollection>
      <ns0:CashierPaymentDetails>
        <ns0:id>
          <xsl:value-of select='oraext:sequence-next-val("CASHIER_PAYMENT_PRI_SEQ","jdbc/LocalUAQDB")'/>
        </ns0:id>
        <ns0:userId>
          <xsl:value-of select="/pymnt:Input/pymnt:UserDetails/pymnt:Username"/>
        </ns0:userId>
        <ns0:requestNo>
          <xsl:value-of select="/pymnt:Input/pymnt:RequestDetails/pymnt:RequestNumber"/>
        </ns0:requestNo>
        <ns0:reqId>
          <xsl:value-of select='substring-after(substring-after(substring-after(/pymnt:Input/pymnt:RequestDetails/pymnt:RequestNumber,"-"),"-"),"-")'/>
        </ns0:reqId>
        <xsl:choose>
          <xsl:when test='/pymnt:Input/pymnt:RequestDetails/pymnt:FeeTypeCode = "S001"'>
            <ns0:typeOfPaymentId>
              <xsl:text disable-output-escaping="no">2</xsl:text>
            </ns0:typeOfPaymentId>
          </xsl:when>
          <xsl:otherwise>
            <ns0:typeOfPaymentId>
              <xsl:text disable-output-escaping="no">1</xsl:text>
            </ns0:typeOfPaymentId>
          </xsl:otherwise>
        </xsl:choose>
        <ns0:amount>
          <xsl:value-of select="/pymnt:Input/pymnt:PaymentDetails/pymnt:TotalAmount"/>
        </ns0:amount>
        <ns0:status>
          <xsl:text disable-output-escaping="no">21</xsl:text>
        </ns0:status>
        <ns0:createdby>
          <xsl:text disable-output-escaping="no">FrontDesk Cashier</xsl:text>
        </ns0:createdby>
        <ns0:modifiedby>
          <xsl:text disable-output-escaping="no">FrontDesk Cashier</xsl:text>
        </ns0:modifiedby>
        <ns0:createdDate>
          <xsl:value-of select="xp20:current-date()"/>
        </ns0:createdDate>
        <ns0:modifiedDate>
          <xsl:value-of select="xp20:current-date()"/>
        </ns0:modifiedDate>
        <ns0:serviceId>
          <xsl:value-of select="/pymnt:Input/pymnt:RequestDetails/pymnt:ServiceId"/>
        </ns0:serviceId>
      </ns0:CashierPaymentDetails>
    </ns0:CashierPaymentDetailsCollection>
  </xsl:template>
</xsl:stylesheet>