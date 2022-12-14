
package ru.vetrf.api.schema.cdm.registry.enterprise.service.v2;

import javax.xml.ws.WebFault;
import ru.vetrf.api.schema.cdm.base.FaultInfo;


/**
 * ??????? ??????? ?? ???????????? ??????. ?????? ????? ? ??????? ?? ?????? ?????????????
 *                     ???? ?????????.
 *                 
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "incorrectRequestFault", targetNamespace = "http://api.vetrf.ru/schema/cdm/base/ws-definitions")
public class IncorrectRequestFault
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private FaultInfo faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public IncorrectRequestFault(String message, FaultInfo faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public IncorrectRequestFault(String message, FaultInfo faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ru.vetrf.api.schema.cdm.base.FaultInfo
     */
    public FaultInfo getFaultInfo() {
        return faultInfo;
    }

}
