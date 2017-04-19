
package sample.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sample.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddNumberResponse_QNAME = new QName("http://jws.sample/", "addNumberResponse");
    private final static QName _SubNumber_QNAME = new QName("http://jws.sample/", "subNumber");
    private final static QName _AddNumber_QNAME = new QName("http://jws.sample/", "addNumber");
    private final static QName _SubNumberResponse_QNAME = new QName("http://jws.sample/", "subNumberResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sample.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddNumber }
     * 
     */
    public AddNumber createAddNumber() {
        return new AddNumber();
    }

    /**
     * Create an instance of {@link SubNumberResponse }
     * 
     */
    public SubNumberResponse createSubNumberResponse() {
        return new SubNumberResponse();
    }

    /**
     * Create an instance of {@link AddNumberResponse }
     * 
     */
    public AddNumberResponse createAddNumberResponse() {
        return new AddNumberResponse();
    }

    /**
     * Create an instance of {@link SubNumber }
     * 
     */
    public SubNumber createSubNumber() {
        return new SubNumber();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNumberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jws.sample/", name = "addNumberResponse")
    public JAXBElement<AddNumberResponse> createAddNumberResponse(AddNumberResponse value) {
        return new JAXBElement<AddNumberResponse>(_AddNumberResponse_QNAME, AddNumberResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubNumber }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jws.sample/", name = "subNumber")
    public JAXBElement<SubNumber> createSubNumber(SubNumber value) {
        return new JAXBElement<SubNumber>(_SubNumber_QNAME, SubNumber.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNumber }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jws.sample/", name = "addNumber")
    public JAXBElement<AddNumber> createAddNumber(AddNumber value) {
        return new JAXBElement<AddNumber>(_AddNumber_QNAME, AddNumber.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubNumberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jws.sample/", name = "subNumberResponse")
    public JAXBElement<SubNumberResponse> createSubNumberResponse(SubNumberResponse value) {
        return new JAXBElement<SubNumberResponse>(_SubNumberResponse_QNAME, SubNumberResponse.class, null, value);
    }

}
