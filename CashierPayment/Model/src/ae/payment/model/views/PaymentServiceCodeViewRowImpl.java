package ae.payment.model.views;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Feb 22 16:39:50 GST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PaymentServiceCodeViewRowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        ServiceId {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getServiceId();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setServiceId((String)value);
            }
        }
        ,
        DepartmentId {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getDepartmentId();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setDepartmentId((String)value);
            }
        }
        ,
        ServiceCode {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getServiceCode();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setServiceCode((String)value);
            }
        }
        ,
        Isvar {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getIsvar();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setIsvar((String)value);
            }
        }
        ,
        Isfixed {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getIsfixed();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setIsfixed((String)value);
            }
        }
        ,
        MerchantId {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getMerchantId();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setMerchantId((String)value);
            }
        }
        ,
        TerminalId {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getTerminalId();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setTerminalId((String)value);
            }
        }
        ,
        BankId {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getBankId();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setBankId((String)value);
            }
        }
        ,
        SecureKey {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getSecureKey();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setSecureKey((String)value);
            }
        }
        ,
        BackToBackUrl {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getBackToBackUrl();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setBackToBackUrl((String)value);
            }
        }
        ,
        RedirectUrl {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getRedirectUrl();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setRedirectUrl((String)value);
            }
        }
        ,
        Other {
            public Object get(PaymentServiceCodeViewRowImpl obj) {
                return obj.getOther();
            }

            public void put(PaymentServiceCodeViewRowImpl obj, Object value) {
                obj.setOther((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(PaymentServiceCodeViewRowImpl object);

        public abstract void put(PaymentServiceCodeViewRowImpl object,
                                 Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int SERVICEID = AttributesEnum.ServiceId.index();
    public static final int DEPARTMENTID = AttributesEnum.DepartmentId.index();
    public static final int SERVICECODE = AttributesEnum.ServiceCode.index();
    public static final int ISVAR = AttributesEnum.Isvar.index();
    public static final int ISFIXED = AttributesEnum.Isfixed.index();
    public static final int MERCHANTID = AttributesEnum.MerchantId.index();
    public static final int TERMINALID = AttributesEnum.TerminalId.index();
    public static final int BANKID = AttributesEnum.BankId.index();
    public static final int SECUREKEY = AttributesEnum.SecureKey.index();
    public static final int BACKTOBACKURL = AttributesEnum.BackToBackUrl.index();
    public static final int REDIRECTURL = AttributesEnum.RedirectUrl.index();
    public static final int OTHER = AttributesEnum.Other.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PaymentServiceCodeViewRowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute ServiceId.
     * @return the ServiceId
     */
    public String getServiceId() {
        return (String) getAttributeInternal(SERVICEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ServiceId.
     * @param value value to set the  ServiceId
     */
    public void setServiceId(String value) {
        setAttributeInternal(SERVICEID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DepartmentId.
     * @return the DepartmentId
     */
    public String getDepartmentId() {
        return (String) getAttributeInternal(DEPARTMENTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DepartmentId.
     * @param value value to set the  DepartmentId
     */
    public void setDepartmentId(String value) {
        setAttributeInternal(DEPARTMENTID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ServiceCode.
     * @return the ServiceCode
     */
    public String getServiceCode() {
        return (String) getAttributeInternal(SERVICECODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ServiceCode.
     * @param value value to set the  ServiceCode
     */
    public void setServiceCode(String value) {
        setAttributeInternal(SERVICECODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Isvar.
     * @return the Isvar
     */
    public String getIsvar() {
        return (String) getAttributeInternal(ISVAR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Isvar.
     * @param value value to set the  Isvar
     */
    public void setIsvar(String value) {
        setAttributeInternal(ISVAR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Isfixed.
     * @return the Isfixed
     */
    public String getIsfixed() {
        return (String) getAttributeInternal(ISFIXED);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Isfixed.
     * @param value value to set the  Isfixed
     */
    public void setIsfixed(String value) {
        setAttributeInternal(ISFIXED, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MerchantId.
     * @return the MerchantId
     */
    public String getMerchantId() {
        return (String) getAttributeInternal(MERCHANTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MerchantId.
     * @param value value to set the  MerchantId
     */
    public void setMerchantId(String value) {
        setAttributeInternal(MERCHANTID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TerminalId.
     * @return the TerminalId
     */
    public String getTerminalId() {
        return (String) getAttributeInternal(TERMINALID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TerminalId.
     * @param value value to set the  TerminalId
     */
    public void setTerminalId(String value) {
        setAttributeInternal(TERMINALID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BankId.
     * @return the BankId
     */
    public String getBankId() {
        return (String) getAttributeInternal(BANKID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BankId.
     * @param value value to set the  BankId
     */
    public void setBankId(String value) {
        setAttributeInternal(BANKID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SecureKey.
     * @return the SecureKey
     */
    public String getSecureKey() {
        return (String) getAttributeInternal(SECUREKEY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SecureKey.
     * @param value value to set the  SecureKey
     */
    public void setSecureKey(String value) {
        setAttributeInternal(SECUREKEY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BackToBackUrl.
     * @return the BackToBackUrl
     */
    public String getBackToBackUrl() {
        return (String) getAttributeInternal(BACKTOBACKURL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BackToBackUrl.
     * @param value value to set the  BackToBackUrl
     */
    public void setBackToBackUrl(String value) {
        setAttributeInternal(BACKTOBACKURL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute RedirectUrl.
     * @return the RedirectUrl
     */
    public String getRedirectUrl() {
        return (String) getAttributeInternal(REDIRECTURL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute RedirectUrl.
     * @param value value to set the  RedirectUrl
     */
    public void setRedirectUrl(String value) {
        setAttributeInternal(REDIRECTURL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Other.
     * @return the Other
     */
    public String getOther() {
        return (String) getAttributeInternal(OTHER);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Other.
     * @param value value to set the  Other
     */
    public void setOther(String value) {
        setAttributeInternal(OTHER, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}