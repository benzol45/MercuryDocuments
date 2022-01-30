
package ru.vetrf.api.schema.cdm.mercury.g2b.applications.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.vetrf.api.schema.cdm.dictionary.v2.SubProduct;
import ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ShipmentRoute;


/**
 * ������ �� �������� ������ �������������� ��� ������������ ��������� ����� � �������� ����������.
 *          
 * 
 * <p>Java class for CheckShipmentRegionalizationRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckShipmentRegionalizationRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.vetrf.ru/schema/cdm/mercury/g2b/applications/v2}MercuryApplicationRequest">
 *       &lt;sequence>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/dictionary/v2}cargoType" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2}shipmentRoute"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckShipmentRegionalizationRequest", propOrder = {
    "cargoType",
    "shipmentRoute"
})
public class CheckShipmentRegionalizationRequest
    extends MercuryApplicationRequest
{

    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/dictionary/v2", required = true)
    protected List<SubProduct> cargoType;
    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2", required = true)
    protected ShipmentRoute shipmentRoute;

    /**
     * ��������� �����, ��� ������� ������������� ������� ����������� �� ��������.
     *                      Gets the value of the cargoType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cargoType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCargoType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubProduct }
     * 
     * 
     */
    public List<SubProduct> getCargoType() {
        if (cargoType == null) {
            cargoType = new ArrayList<SubProduct>();
        }
        return this.cargoType;
    }

    /**
     * ������� ���������� �����. � ������� �� �������� ����������,
     *                         ������� ����������� � ������������ ����������� � � ������� �� ���������� ��������� ������,
     *                         `shipmentRoute` ����� ������ �������� ����� ����������� � ����� ����������.
     *                         ��� ������ ����� �������� ����������� ������ ���� �������� �����:
     *                         �������� `shipmentRoute/routePoint/location/address` � `shipmentRoute/routePoint/enterprise/address`.
     *                         ������ � ���� ������ �������� ������ ����������� (��������������) ���� ���������� ���������.
     *                      
     * 
     * @return
     *     possible object is
     *     {@link ShipmentRoute }
     *     
     */
    public ShipmentRoute getShipmentRoute() {
        return shipmentRoute;
    }

    /**
     * Sets the value of the shipmentRoute property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentRoute }
     *     
     */
    public void setShipmentRoute(ShipmentRoute value) {
        this.shipmentRoute = value;
    }

}
