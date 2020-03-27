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
 *         &lt;element name="DeleteWeixinPaperInfoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "deleteWeixinPaperInfoResult"
})
@XmlRootElement(name = "DeleteWeixinPaperInfoResponse")
public class DeleteWeixinPaperInfoResponse {

    @XmlElement(name = "DeleteWeixinPaperInfoResult")
    protected String deleteWeixinPaperInfoResult;

    /**
     * 获取deleteWeixinPaperInfoResult属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getDeleteWeixinPaperInfoResult() {
        return deleteWeixinPaperInfoResult;
    }

    /**
     * 设置deleteWeixinPaperInfoResult属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDeleteWeixinPaperInfoResult(String value) {
        this.deleteWeixinPaperInfoResult = value;
    }

}
