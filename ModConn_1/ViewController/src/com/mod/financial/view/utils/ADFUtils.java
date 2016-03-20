package com.mod.financial.view.utils;


import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.model.binding.DCParameter;

import oracle.adf.model.binding.DCUtil;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.binding.ControlBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlValueBinding;
import oracle.jbo.uicli.binding.JUFormBinding;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


/**
 * A series of convenience functions for dealing with ADF Bindings.
 * Note: Updated for JDeveloper 11
 *
 * @author Duncan Mills
 * @author Steve Muench
 *
 * $Id: ADFUtils.java 2513 2007-09-20 20:39:13Z ralsmith $.
 */
public class ADFUtils {
    
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(ADFUtils.class);

    /**
     * Get application module for an application module data control by name.
     * @param name application module data control name
     * @return ApplicationModule
     */
    public static ApplicationModule getApplicationModuleForDataControl(String name) {
        return (ApplicationModule)JSFUtils.resolveExpression("#{data." + name + 
                                                             ".dataProvider}");
    }

    /**
     * A convenience method for getting the value of a bound attribute in the
     * current page context programatically.
     * @param attributeName of the bound value in the pageDef
     * @return value of the attribute
     */
    public static Object getBoundAttributeValue(String attributeName) {
        return findControlBinding(attributeName).getInputValue();
    }

    /**
     * A convenience method for setting the value of a bound attribute in the
     * context of the current page.
     * @param attributeName of the bound value in the pageDef
     * @param value to set
     */
    public static void setBoundAttributeValue(String attributeName, 
                                              Object value) {
        findControlBinding(attributeName).setInputValue(value);
    }

    /**
     * Returns the evaluated value of a pageDef parameter.
     * @param pageDefName reference to the page definition file of the page with the parameter
     * @param parameterName name of the pagedef parameter
     * @return evaluated value of the parameter as a String
     */
    public static Object getPageDefParameterValue(String pageDefName, 
                                                  String parameterName) {
        BindingContainer bindings = findBindingContainer(pageDefName);
        DCParameter param = 
            ((DCBindingContainer)bindings).findParameter(parameterName);
        return param.getValue();
    }

    /**
     * Convenience method to find a DCControlBinding as an AttributeBinding
     * to get able to then call getInputValue() or setInputValue() on it.
     * @param bindingContainer binding container
     * @param attributeName name of the attribute binding.
     * @return the control value binding with the name passed in.
     *
     */
    public static AttributeBinding findControlBinding(BindingContainer bindingContainer, 
                                                      String attributeName) {
        if (attributeName != null) {
            if (bindingContainer != null) {
                ControlBinding ctrlBinding = 
                    bindingContainer.getControlBinding(attributeName);
                if (ctrlBinding instanceof AttributeBinding) {
                    return (AttributeBinding)ctrlBinding;
                }
            }
        }
        return null;
    }

    /**
     * Convenience method to find a DCControlBinding as a JUCtrlValueBinding
     * to get able to then call getInputValue() or setInputValue() on it.
     * @param attributeName name of the attribute binding.
     * @return the control value binding with the name passed in.
     *
     */
    public static AttributeBinding findControlBinding(String attributeName) {
        return findControlBinding(getBindingContainer(), attributeName);
    }

    /**
     * Return the current page's binding container.
     * @return the current page's binding container
     */
    public static BindingContainer getBindingContainer() {
        return (BindingContainer)JSFUtils.resolveExpression("#{bindings}");
    }

    /**
     * Return the Binding Container as a DCBindingContainer.
     * @return current binding container as a DCBindingContainer
     */
    public static DCBindingContainer getDCBindingContainer() {
        return (DCBindingContainer)getBindingContainer();
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     * 
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     * 
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName name of the value attribute to use
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsForIterator(String iteratorName, 
                                                          String valueAttrName, 
                                                          String displayAttrName) {
        return selectItemsForIterator(findIterator(iteratorName), 
                                      valueAttrName, displayAttrName);
    }
    
    //Added by Amr Ahmed
   
    public static void cancelChanges(String iteratorName) {
        BindingContext bcx =
            DCUtil.getBindingContext((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest());
        JUFormBinding bindings = (JUFormBinding)bcx.getCurrentBindingsEntry();
        DCIteratorBinding iterBinding =
            bindings.findIteratorBinding(iteratorName);
        ViewObject vo = iterBinding.getViewObject();
        for (int y=0; y<vo.getEstimatedRowCount(); y++) {
            Row r=iterBinding.getRowAtRangeIndex(y);
          r.refresh(Row.REFRESH_REMOVE_NEW_ROWS |
                             Row.REFRESH_WITH_DB_FORGET_CHANGES);
        }
    }
    //Added by Amr Ahmed
    /** Method For Canceling any Changes Hapen In The Row (Create New Row or Update Row)*/
    public static void cancelChangesCurrentRow(String iteratorName) {
        BindingContext bcx =
            DCUtil.getBindingContext((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest());
        JUFormBinding bindings = (JUFormBinding)bcx.getCurrentBindingsEntry();
        DCIteratorBinding iterBinding =
            bindings.findIteratorBinding(iteratorName);
        ViewObject vo = iterBinding.getViewObject();
            Row r=vo.getCurrentRow();
          r.refresh(Row.REFRESH_REMOVE_NEW_ROWS |
                             Row.REFRESH_WITH_DB_FORGET_CHANGES);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with description.
     * 
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     * 
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName name of the value attribute to use
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute to use for description
     * @return ADF Faces SelectItem for an iterator binding with description
     */
    public static List<SelectItem> selectItemsForIterator(String iteratorName, 
                                                          String valueAttrName, 
                                                          String displayAttrName, 
                                                          String descriptionAttrName) {
        return selectItemsForIterator(findIterator(iteratorName), 
                                      valueAttrName, displayAttrName, 
                                      descriptionAttrName);
    }

    /**
     * Get List of attribute values for an iterator.
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName value attribute to use
     * @return List of attribute values for an iterator
     */
    public static List attributeListForIterator(String iteratorName, 
                                                String valueAttrName) {
        return attributeListForIterator(findIterator(iteratorName), 
                                        valueAttrName);
    }

    /**
     * Get List of Key objects for rows in an iterator.
     * @param iteratorName iterabot binding name
     * @return List of Key objects for rows
     */
    public static List<Key> keyListForIterator(String iteratorName) {
        return keyListForIterator(findIterator(iteratorName));
    }

    /**
     * Get List of Key objects for rows in an iterator.
     * @param iter iterator binding
     * @return List of Key objects for rows
     */
    public static List<Key> keyListForIterator(DCIteratorBinding iter) {
        List<Key> attributeList = new ArrayList<Key>();
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(r.getKey());
        }
        return attributeList;
    }

    /**
     * Get List of Key objects for rows in an iterator using key attribute.
     * @param iteratorName iterator binding name
     * @param keyAttrName name of key attribute to use
     * @return List of Key objects for rows
     */
    public static List<Key> keyAttrListForIterator(String iteratorName, 
                                                   String keyAttrName) {
        return keyAttrListForIterator(findIterator(iteratorName), keyAttrName);
    }

    /**
     * Get List of Key objects for rows in an iterator using key attribute.
     * 
     * @param iter iterator binding
     * @param keyAttrName name of key attribute to use
     * @return List of Key objects for rows
     */
    public static List<Key> keyAttrListForIterator(DCIteratorBinding iter, 
                                                   String keyAttrName) {
        List<Key> attributeList = new ArrayList<Key>();
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(new Key(new Object[] { r.getAttribute(keyAttrName) }));
        }
        return attributeList;
    }

    /**
     * Get a List of attribute values for an iterator.
     * 
     * @param iter iterator binding
     * @param valueAttrName name of value attribute to use
     * @return List of attribute values
     */
    public static List attributeListForIterator(DCIteratorBinding iter, 
                                                String valueAttrName) {
        List attributeList = new ArrayList();
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(r.getAttribute(valueAttrName));
        }
        return attributeList;
    }

    /**
     * Find an iterator binding in the current binding container by name.
     * 
     * @param name iterator binding name
     * @return iterator binding
     */
    public static DCIteratorBinding findIterator(String name) {
        DCIteratorBinding iter = 
            getDCBindingContainer().findIteratorBinding(name);
        if (iter == null) {
            throw new RuntimeException("Iterator '" + name + "' not found");
        }
        return iter;
    }

    /**
     * @param bindingContainer
     * @param iterator
     * @return
     */
    public static DCIteratorBinding findIterator(String bindingContainer, String iterator) {
        DCBindingContainer bindings = 
            (DCBindingContainer)JSFUtils.resolveExpression("#{" + bindingContainer + "}");
        if (bindings == null) {
            throw new RuntimeException("Binding container '" + 
                                       bindingContainer + "' not found");
        }
        DCIteratorBinding iter = bindings.findIteratorBinding(iterator);
        if (iter == null) {
            throw new RuntimeException("Iterator '" + iterator + "' not found");
        }
        return iter;
    }

    /**
     * @param name
     * @return
     */
    public static JUCtrlValueBinding findCtrlBinding(String name){
        JUCtrlValueBinding rowBinding = 
            (JUCtrlValueBinding)getDCBindingContainer().findCtrlBinding(name);    
        if (rowBinding == null) {
            throw new RuntimeException("CtrlBinding " + name + "' not found");
        }
        return rowBinding;
    }

    /**
     * Find an operation binding in the current binding container by name.
     * 
     * @param name operation binding name
     * @return operation binding
     */
    public static OperationBinding findOperation(String name) {
        OperationBinding op = 
            getDCBindingContainer().getOperationBinding(name);
        if (op == null) {
            throw new RuntimeException("Operation '" + name + "' not found");
        }
        return op;
    }

    /**
     * Find an operation binding in the current binding container by name.
     * 
     * @param bindingContianer binding container name
     * @param opName operation binding name
     * @return operation binding
     */
    public static OperationBinding findOperation(String bindingContianer, 
                                                 String opName) {
        DCBindingContainer bindings = 
            (DCBindingContainer)JSFUtils.resolveExpression("#{" + bindingContianer + "}");
        if (bindings == null) {
            throw new RuntimeException("Binding container '" + 
                                       bindingContianer + "' not found");
        }
        OperationBinding op = 
            bindings.getOperationBinding(opName);
        if (op == null) {
            throw new RuntimeException("Operation '" + opName + "' not found");
        }
        return op;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with description.
     * 
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     * 
     * @param iter ADF iterator binding
     * @param valueAttrName name of value attribute to use for key
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with description
     */
    public static List<SelectItem> selectItemsForIterator(DCIteratorBinding iter, 
                                                          String valueAttrName, 
                                                          String displayAttrName, 
                                                          String descriptionAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getAttribute(valueAttrName), 
                                           (String)r.getAttribute(displayAttrName), 
                                           (String)r.getAttribute(descriptionAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     * 
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     * 
     * @param iter ADF iterator binding
     * @param valueAttrName name of value attribute to use for key
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsForIterator(DCIteratorBinding iter, 
                                                          String valueAttrName, 
                                                          String displayAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getAttribute(valueAttrName), 
                                           (String)r.getAttribute(displayAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     * 
     * Uses the rowKey of each row as the SelectItem key.
     * 
     * @param iteratorName ADF iterator binding name
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsByKeyForIterator(String iteratorName, 
                                                               String displayAttrName) {
        return selectItemsByKeyForIterator(findIterator(iteratorName), 
                                           displayAttrName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with discription.
     * 
     * Uses the rowKey of each row as the SelectItem key.
     * 
     * @param iteratorName ADF iterator binding name
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with discription
     */
    public static List<SelectItem> selectItemsByKeyForIterator(String iteratorName, 
                                                               String displayAttrName, 
                                                               String descriptionAttrName) {
        return selectItemsByKeyForIterator(findIterator(iteratorName), 
                                           displayAttrName, 
                                           descriptionAttrName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with discription.
     * 
     * Uses the rowKey of each row as the SelectItem key.
     * 
     * @param iter ADF iterator binding
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with discription
     */
    public static List<SelectItem> selectItemsByKeyForIterator(DCIteratorBinding iter, 
                                                               String displayAttrName, 
                                                               String descriptionAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getKey(), 
                                           (String)r.getAttribute(displayAttrName), 
                                           (String)r.getAttribute(descriptionAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     * 
     * Uses the rowKey of each row as the SelectItem key.
     * 
     * @param iter ADF iterator binding
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return List of ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsByKeyForIterator(DCIteratorBinding iter, 
                                                               String displayAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getKey(), 
                                           (String)r.getAttribute(displayAttrName)));
        }
        return selectItems;
    }
    
    public static void refreshIterator(String iterator) {
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dciter = (DCIteratorBinding)bindings.get(iterator);
        dciter.executeQuery();
        dciter.refresh(DCIteratorBinding.RANGESIZE_UNLIMITED);
    }

    /**
     * Find the BindingContainer for a page definition by name.
     *
     * Typically used to refer eagerly to page definition parameters. It is
     * not best practice to reference or set bindings in binding containers
     * that are not the one for the current page.
     *
     * @param pageDefName name of the page defintion XML file to use
     * @return BindingContainer ref for the named definition
     */
    private static BindingContainer findBindingContainer(String pageDefName) {
        BindingContext bctx = getDCBindingContainer().getBindingContext();
        BindingContainer foundContainer = 
            bctx.findBindingContainer(pageDefName);
        return foundContainer;
    }

    /**
     * @param opList
     */
    public static void printOperationBindingExceptions(List opList){
        if(opList != null && !opList.isEmpty()){
            for(Object error:opList){
                LOGGER.severe( error.toString() );
            }
        }
    }
    
    /**
    * Programmatic evaluation of EL.
    *
    * @param el EL to evaluate
    * @return Result of the evaluation
    */
    public static Object evaluateEL(String el) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ELContext elContext = facesContext.getELContext();
    ExpressionFactory expressionFactory =
    facesContext.getApplication().getExpressionFactory();
    ValueExpression exp =
    expressionFactory.createValueExpression(elContext, el,
    Object.class);

    return exp.getValue(elContext);
    }

    /**
    * Programmatic invocation of a method that an EL evaluates to.
    * The method must not take any parameters.
    *
    * @param el EL of the method to invoke
    * @return Object that the method returns
    */
    public static Object invokeEL(String el) {
    return invokeEL(el, new Class[0], new Object[0]);
    }

    /**
    * Programmatic invocation of a method that an EL evaluates to.
    *
    * @param el EL of the method to invoke
    * @param paramTypes Array of Class defining the types of the parameters
    * @param params Array of Object defining the values of the parametrs
    * @return Object that the method returns
    */
    public static Object invokeEL(String el, Class[] paramTypes,
    Object[] params) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ELContext elContext = facesContext.getELContext();
    ExpressionFactory expressionFactory =
    facesContext.getApplication().getExpressionFactory();
    MethodExpression exp =
    expressionFactory.createMethodExpression(elContext, el,
    Object.class, paramTypes);

    return exp.invoke(elContext, params);
    }

    /**
    * Sets a value into an EL object. Provides similar functionality to
    * the <af:setActionListener> tag, except the from is
    * not an EL. You can get similar behavior by using the following...

    * setEL(to, evaluateEL(from))
    *
    * @param el EL object to assign a value
    * @param val Value to assign
    */
    public static void setEL(String el, Object val) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ELContext elContext = facesContext.getELContext();
    ExpressionFactory expressionFactory =
    facesContext.getApplication().getExpressionFactory();
    ValueExpression exp =
    expressionFactory.createValueExpression(elContext, el,
    Object.class);

    exp.setValue(elContext, val);
    }
    
    //Adde by Eid on 05/10/2015//
    /**
     * Programmatic Refresh for current Page .
     */
    public static void refreshPage() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String refreshpage = fc.getViewRoot().getViewId();
        ViewHandler ViewH = fc.getApplication().getViewHandler();
        UIViewRoot UIV = ViewH.createView(fc, refreshpage);
        UIV.setViewId(refreshpage);
        fc.setViewRoot(UIV);
    }

    public static void executeJavaScript(String script) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks =
            Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        erks.addScript(fctx, script);
    }
    

    public static HttpServletRequest getReuqestObject(){
        FacesContext fctx = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fctx.getExternalContext().getRequest();
        
        return request;
    }
       /** Created by Amr Ahmed 
       Method For Opening URL in new Tab  */
    public static void openUrlInNewTab(String url) {
        ExtendedRenderKitService erks =
            Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                        ExtendedRenderKitService.class);
        StringBuilder strb = new StringBuilder("window.open('" + url + "');");
        erks.addScript(FacesContext.getCurrentInstance(), strb.toString());

    }
       
    /** Created by Amr Ahmed 
    Method For Opening URL in new Window  */
        public static void openUrlInNewWindow(String taskflowURL, boolean inTab){
                ExtendedRenderKitService erks = Service.getRenderKitService(
                                                FacesContext.getCurrentInstance(),
                                                ExtendedRenderKitService.class);
                StringBuilder script = new StringBuilder();
                if(inTab){
                    script.append("window.open(\""+taskflowURL+"\");");
                }
                else{
                    script.append("window.open(\""+taskflowURL+"\"").append(",\"Edit Department\",\"location=0,status=0,scrollbars=0\");");   
                }
                erks.addScript(FacesContext.getCurrentInstance(), script.toString());
        }
    
    /** Created by Amr Ahmed 
    Method For Entity Status Weather Insert or Update   */
        public static int getEntityStatus(String iteratorName){
        return Integer.parseInt(JSFUtils.resolveExpression("#{bindings."+iteratorName+".currentRow.entities[0].entityState}").toString());

    }

    
}