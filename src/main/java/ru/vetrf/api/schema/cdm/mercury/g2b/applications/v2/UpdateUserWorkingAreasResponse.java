
package ru.vetrf.api.schema.cdm.mercury.g2b.applications.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.vetrf.api.schema.cdm.application.ApplicationResultData;
import ru.vetrf.api.schema.cdm.mercury.vet_document.v2.User;


/**
 * ????? ?? ????????? ??? ??????????????? ????????????.
 * 
 * <p>Java class for UpdateUserWorkingAreasResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateUserWorkingAreasResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.vetrf.ru/schema/cdm/application}ApplicationResultData">
 *       &lt;sequence>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2}user" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateUserWorkingAreasResponse", propOrder = {
    "user"
})
public class UpdateUserWorkingAreasResponse
    extends ApplicationResultData
{

    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2")
    protected List<User> user;

    /**
     * ????????????, ??? ???????? ???? ???????? ???? ??????????????? Gets the value of the user property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the user property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUser().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     * 
     * 
     */
    public List<User> getUser() {
        if (user == null) {
            user = new ArrayList<User>();
        }
        return this.user;
    }

}
