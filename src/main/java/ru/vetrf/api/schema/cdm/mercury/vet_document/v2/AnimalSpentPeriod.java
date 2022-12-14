
package ru.vetrf.api.schema.cdm.mercury.vet_document.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AnimalSpentPeriod.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AnimalSpentPeriod">
 *   &lt;restriction base="{http://api.vetrf.ru/schema/cdm/base}String255">
 *     &lt;enumeration value="FROM_BIRTH"/>
 *     &lt;enumeration value="NOT_LESS_SIX_MONTHS"/>
 *     &lt;enumeration value="IN_MONTHS"/>
 *     &lt;enumeration value="ZERO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AnimalSpentPeriod")
@XmlEnum
public enum AnimalSpentPeriod {


    /**
     * ???????? ?????????? ?? ?????????? ?? ? ????????
     * 
     */
    FROM_BIRTH,

    /**
     * ???????? ?????????? ?? ?????????? ?? ?? ????? 6 ???????
     * 
     */
    NOT_LESS_SIX_MONTHS,

    /**
     * ???????? ?????????? ?? ?????????? ?? ????????? ???-?? ???????
     * 
     */
    IN_MONTHS,

    /**
     * ???????? ?? ?????????? ?? ?????????? ??
     * 
     */
    ZERO;

    public String value() {
        return name();
    }

    public static AnimalSpentPeriod fromValue(String v) {
        return valueOf(v);
    }

}
