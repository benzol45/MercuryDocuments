
package ru.vetrf.api.schema.cdm.mercury.g2b.applications.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.vetrf.api.schema.cdm.application.ApplicationResultData;
import ru.vetrf.api.schema.cdm.mercury.vet_document.v2.StockEntryList;


/**
 * ????????? ?????? ?? ????????? ?????? ??????.
 * 
 * <p>Java class for GetStockEntryChangesListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetStockEntryChangesListResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.vetrf.ru/schema/cdm/application}ApplicationResultData">
 *       &lt;sequence>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2}stockEntryList"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetStockEntryChangesListResponse", propOrder = {
    "stockEntryList"
})
public class GetStockEntryChangesListResponse
    extends ApplicationResultData
{

    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2", required = true)
    protected StockEntryList stockEntryList;

    /**
     * ?????? ??????.
     * 
     * @return
     *     possible object is
     *     {@link StockEntryList }
     *     
     */
    public StockEntryList getStockEntryList() {
        return stockEntryList;
    }

    /**
     * Sets the value of the stockEntryList property.
     * 
     * @param value
     *     allowed object is
     *     {@link StockEntryList }
     *     
     */
    public void setStockEntryList(StockEntryList value) {
        this.stockEntryList = value;
    }

}
