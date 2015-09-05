
package util.login;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "IFManagerBeanService", targetNamespace = "http://login.util/", wsdlLocation = "http://localhost:8080/IFManagerBeanService/IFManagerBean?wsdl")
public class IFManagerBeanService
    extends Service
{

    private final static URL IFMANAGERBEANSERVICE_WSDL_LOCATION;
    private final static WebServiceException IFMANAGERBEANSERVICE_EXCEPTION;
    private final static QName IFMANAGERBEANSERVICE_QNAME = new QName("http://login.util/", "IFManagerBeanService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/IFManagerBeanService/IFManagerBean?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        IFMANAGERBEANSERVICE_WSDL_LOCATION = url;
        IFMANAGERBEANSERVICE_EXCEPTION = e;
    }

    public IFManagerBeanService() {
        super(__getWsdlLocation(), IFMANAGERBEANSERVICE_QNAME);
    }

    public IFManagerBeanService(WebServiceFeature... features) {
        super(__getWsdlLocation(), IFMANAGERBEANSERVICE_QNAME, features);
    }

    public IFManagerBeanService(URL wsdlLocation) {
        super(wsdlLocation, IFMANAGERBEANSERVICE_QNAME);
    }

    public IFManagerBeanService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, IFMANAGERBEANSERVICE_QNAME, features);
    }

    public IFManagerBeanService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IFManagerBeanService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IFManagerBean
     */
    @WebEndpoint(name = "IFManagerBeanPort")
    public IFManagerBean getIFManagerBeanPort() {
        return super.getPort(new QName("http://login.util/", "IFManagerBeanPort"), IFManagerBean.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IFManagerBean
     */
    @WebEndpoint(name = "IFManagerBeanPort")
    public IFManagerBean getIFManagerBeanPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://login.util/", "IFManagerBeanPort"), IFManagerBean.class, features);
    }

    private static URL __getWsdlLocation() {
        if (IFMANAGERBEANSERVICE_EXCEPTION!= null) {
            throw IFMANAGERBEANSERVICE_EXCEPTION;
        }
        return IFMANAGERBEANSERVICE_WSDL_LOCATION;
    }

}