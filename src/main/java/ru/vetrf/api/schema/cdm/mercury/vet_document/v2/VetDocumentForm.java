
package ru.vetrf.api.schema.cdm.mercury.vet_document.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VetDocumentForm.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VetDocumentForm">
 *   &lt;restriction base="{http://api.vetrf.ru/schema/cdm/base}String255">
 *     &lt;enumeration value="CERTCU1"/>
 *     &lt;enumeration value="LIC1"/>
 *     &lt;enumeration value="CERTCU2"/>
 *     &lt;enumeration value="LIC2"/>
 *     &lt;enumeration value="CERTCU3"/>
 *     &lt;enumeration value="LIC3"/>
 *     &lt;enumeration value="NOTE4"/>
 *     &lt;enumeration value="CERT5I"/>
 *     &lt;enumeration value="CERT61"/>
 *     &lt;enumeration value="CERT62"/>
 *     &lt;enumeration value="CERT63"/>
 *     &lt;enumeration value="PRODUCTIVE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VetDocumentForm")
@XmlEnum
public enum VetDocumentForm {


    /**
     * ����� 1 ������������� ����������� ��
     * 
     */
    @XmlEnumValue("CERTCU1")
    CERTCU_1("CERTCU1"),

    /**
     * ����� 1 ������������� �������������
     * 
     */
    @XmlEnumValue("LIC1")
    LIC_1("LIC1"),

    /**
     * ����� 2 ������������� ����������� ��
     * 
     */
    @XmlEnumValue("CERTCU2")
    CERTCU_2("CERTCU2"),

    /**
     * ����� 2 ������������� �������������
     * 
     */
    @XmlEnumValue("LIC2")
    LIC_2("LIC2"),

    /**
     * ����� 3 ������������� ����������� ��
     * 
     */
    @XmlEnumValue("CERTCU3")
    CERTCU_3("CERTCU3"),

    /**
     * ����� 3 ������������� �������������
     * 
     */
    @XmlEnumValue("LIC3")
    LIC_3("LIC3"),

    /**
     * ����� 4 ������������ �������
     * 
     */
    @XmlEnumValue("NOTE4")
    NOTE_4("NOTE4"),

    /**
     * ����� 5i ������������� �����������
     * 
     */
    @XmlEnumValue("CERT5I")
    CERT_5_I("CERT5I"),

    /**
     * ����� 6.1 ������������� �����������
     * 
     */
    @XmlEnumValue("CERT61")
    CERT_61("CERT61"),

    /**
     * ����� 6.2 ������������� �����������
     * 
     */
    @XmlEnumValue("CERT62")
    CERT_62("CERT62"),

    /**
     * ����� 6.3 ������������� �����������
     * 
     */
    @XmlEnumValue("CERT63")
    CERT_63("CERT63"),

    /**
     * ����� ����������������� ������������� �����������
     * 
     */
    PRODUCTIVE("PRODUCTIVE");
    private final String value;

    VetDocumentForm(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VetDocumentForm fromValue(String v) {
        for (VetDocumentForm c: VetDocumentForm.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
