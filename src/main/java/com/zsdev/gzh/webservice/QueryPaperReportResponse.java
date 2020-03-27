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
 *         &lt;element name="QueryPaperReportResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "queryPaperReportResult"
})
@XmlRootElement(name = "QueryPaperReportResponse")
public class QueryPaperReportResponse {

    @XmlElement(name = "QueryPaperReportResult")
    protected String queryPaperReportResult;

    /**
     * 获取queryPaperReportResult属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getQueryPaperReportResult() {
        return queryPaperReportResult;
    }

    /**
     * 设置queryPaperReportResult属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setQueryPaperReportResult(String value) {
        this.queryPaperReportResult = value;
    }

}
