
package ru.vetrf.api.schema.cdm.registry.ws_definitions.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ru.vetrf.api.schema.cdm.dictionary.v2.PurposeList;


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
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/dictionary/v2}purposeList"/>
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
    "purposeList"
})
@XmlRootElement(name = "getPurposeChangesListResponse")
public class GetPurposeChangesListResponse {

    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/dictionary/v2", required = true)
    protected PurposeList purposeList;

    /**
     * ?????? ??????? ?????????? ?????.
     *                   
     * 
     * @return
     *     possible object is
     *     {@link PurposeList }
     *     
     */
    public PurposeList getPurposeList() {
        return purposeList;
    }

    /**
     * Sets the value of the purposeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurposeList }
     *     
     */
    public void setPurposeList(PurposeList value) {
        this.purposeList = value;
    }

}
