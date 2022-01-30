
package ru.vetrf.api.schema.cdm.mercury.vet_document.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * ���, ����������� ������������ ������ �����.
 *             ��� ���������� �������������� ������������ �����: ���� ��������� ������ RegisterProductionOperationRequest
 *             �������� �������� � ������������ ��������� � ��������� ������� ������ (Batch/batchID)),
 *             � ����� � ������ ���� �� ���������������� ���������� ��� ���� ��������� ������������ ��������� � ��� ��
 *             ������� ������,
 *             �� ����� ������������ ����� ��������� ����� ����������� �� �� �� ������ ���������� ������� � ����������� �
 *             ������
 *             (�.�. ����� ��������� �������������� ������������� ������� �������, guid ������ ������� ��������� �������).
 *          
 * 
 * <p>Java class for ProductiveBatch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductiveBatch">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2}Batch">
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductiveBatch")
public class ProductiveBatch
    extends Batch
{

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
