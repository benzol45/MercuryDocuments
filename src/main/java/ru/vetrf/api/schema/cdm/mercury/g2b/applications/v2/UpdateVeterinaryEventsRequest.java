
package ru.vetrf.api.schema.cdm.mercury.g2b.applications.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.vetrf.api.schema.cdm.dictionary.v2.Enterprise;
import ru.vetrf.api.schema.cdm.mercury.vet_document.v2.StockEntry;


/**
 * «а€вка на внесение сведений о ветеринарных меропри€ти€х с партией продукции таких,
 *             как лабораторные исследовани€, карантинирование, иммунизации и обработки дл€ живых животных.
 *             ѕоддерживаетс€ обновление сведений о лабораторных исследовани€х, дл€ которых ранее не был указан результат исследовани€.
 *          
 * 
 * <p>Java class for UpdateVeterinaryEventsRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateVeterinaryEventsRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.vetrf.ru/schema/cdm/mercury/g2b/applications/v2}MercuryApplicationRequest">
 *       &lt;sequence>
 *         &lt;element name="enterprise" type="{http://api.vetrf.ru/schema/cdm/dictionary/v2}Enterprise"/>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2}stockEntry" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateVeterinaryEventsRequest", propOrder = {
    "enterprise",
    "stockEntry"
})
public class UpdateVeterinaryEventsRequest
    extends MercuryApplicationRequest
{

    @XmlElement(required = true)
    protected Enterprise enterprise;
    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2", required = true)
    protected List<StockEntry> stockEntry;

    /**
     * Gets the value of the enterprise property.
     * 
     * @return
     *     possible object is
     *     {@link Enterprise }
     *     
     */
    public Enterprise getEnterprise() {
        return enterprise;
    }

    /**
     * Sets the value of the enterprise property.
     * 
     * @param value
     *     allowed object is
     *     {@link Enterprise }
     *     
     */
    public void setEnterprise(Enterprise value) {
        this.enterprise = value;
    }

    /**
     * «апись складского журнала, дл€ которой внос€тс€/обновл€ютс€ сведени€ о меропри€ти€х.
     *                         ћожет быть указано более одной записи складского журнала.
     *                         ƒл€ каждой записи должен быть указан идентификатор (GUID) и список меропри€тий stockEntry/vetEventList.
     *                         ѕри обновлении результатов лабораторных исследований у соответствующего stockEntry/vetEventList/laboratoryResearch
     *                         должен быть указан идентификатор ID.
     *                      Gets the value of the stockEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stockEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStockEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StockEntry }
     * 
     * 
     */
    public List<StockEntry> getStockEntry() {
        if (stockEntry == null) {
            stockEntry = new ArrayList<StockEntry>();
        }
        return this.stockEntry;
    }

}
