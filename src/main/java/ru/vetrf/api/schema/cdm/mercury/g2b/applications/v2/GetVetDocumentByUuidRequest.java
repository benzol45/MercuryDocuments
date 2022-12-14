
package ru.vetrf.api.schema.cdm.mercury.g2b.applications.v2;

import javax.xml.bind.annotation.*;


/**
 * ?????? ?? ????????? ?????? ???. ??????????? ?? ??????????????.
 * 
 * <p>Java class for GetVetDocumentByUuidRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetVetDocumentByUuidRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.vetrf.ru/schema/cdm/mercury/g2b/applications/v2}MercuryApplicationRequest">
 *       &lt;sequence>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/base}uuid"/>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/dictionary/v2}enterpriseGuid"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetVetDocumentByUuidRequest", propOrder = {
    "uuid",
    "enterpriseGuid"
})
@XmlRootElement(name = "getVetDocumentByUuidRequest")
public class GetVetDocumentByUuidRequest
    extends MercuryApplicationRequest
{

    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/base", required = true)
    protected String uuid;
    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/dictionary/v2", required = true)
    protected String enterpriseGuid;

    /**
     * ????????????? ??????, ?? ???????? ???????????? ?????.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

    /**
     * ????????????? ???????????, ?? ???????? ???????????? ????? ??????.
     *                      
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnterpriseGuid() {
        return enterpriseGuid;
    }

    /**
     * Sets the value of the enterpriseGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnterpriseGuid(String value) {
        this.enterpriseGuid = value;
    }

}
