package com.zsdev.gzh.webservice;


import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 *
 * <p>以下模式片段指定包含在此类中的预期内容。
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="app_key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="order_number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="thesisType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "appKey",
        "token",
        "spam",
        "orderNumber",
        "thesisType"
})
@XmlRootElement(name = "QueryPaperReport")
public class QueryPaperReport {

    @XmlElement(name = "app_key")
    protected String appKey;
    protected String token;
    protected String spam;
    @XmlElement(name = "order_number")
    protected String orderNumber;
    protected String thesisType;

    /**
     * 获取appKey属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * 设置appKey属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAppKey(String value) {
        this.appKey = value;
    }

    /**
     * 获取token属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * 获取spam属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getSpam() {
        return spam;
    }

    /**
     * 设置spam属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSpam(String value) {
        this.spam = value;
    }

    /**
     * 获取orderNumber属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置orderNumber属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * 获取thesisType属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getThesisType() {
        return thesisType;
    }

    /**
     * 设置thesisType属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setThesisType(String value) {
        this.thesisType = value;
    }

}
