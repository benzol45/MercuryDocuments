
package ru.vetrf.api.schema.cdm.dictionary.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VaccinationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VaccinationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="UNDEFINED"/>
 *     &lt;enumeration value="APPLIED"/>
 *     &lt;enumeration value="UNVACCINATED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VaccinationType")
@XmlEnum
public enum VaccinationType {


    /**
     * ��������������
     * 
     */
    UNDEFINED,

    /**
     * ���������� ����������
     * 
     */
    APPLIED,

    /**
     * ���������� �� ����������
     * 
     */
    UNVACCINATED;

    public String value() {
        return name();
    }

    public static VaccinationType fromValue(String v) {
        return valueOf(v);
    }

}
