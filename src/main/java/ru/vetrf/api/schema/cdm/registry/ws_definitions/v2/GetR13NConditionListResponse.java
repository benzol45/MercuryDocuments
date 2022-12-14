
package ru.vetrf.api.schema.cdm.registry.ws_definitions.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ru.vetrf.api.schema.cdm.dictionary.v2.RegionalizationConditionList;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/dictionary/v2}r13nConditionList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "r13NConditionList"
})
@XmlRootElement(name = "getR13nConditionListResponse")
public class GetR13NConditionListResponse {

    @XmlElement(name = "r13nConditionList", namespace = "http://api.vetrf.ru/schema/cdm/dictionary/v2", required = true)
    protected RegionalizationConditionList r13NConditionList;

    /**
     * ?????? ??????? ??????????? ?????.
     * 
     * @return
     *     possible object is
     *     {@link RegionalizationConditionList }
     *     
     */
    public RegionalizationConditionList getR13NConditionList() {
        return r13NConditionList;
    }

    /**
     * Sets the value of the r13NConditionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegionalizationConditionList }
     *     
     */
    public void setR13NConditionList(RegionalizationConditionList value) {
        this.r13NConditionList = value;
    }

}
