
package braunimmobilien.client.contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://braunimmobilien/webapp/person" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="telefoneModel">
 *   &lt;xs:sequence>
 *     &lt;xs:element name="telefon" minOccurs="0" maxOccurs="unbounded">
 *       &lt;!-- Reference to inner class Telefon -->
 *     &lt;/xs:element>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class TelefoneModel implements Serializable
{
    private List<Telefon> telefonList = new ArrayList<Telefon>();

    /** 
     * Get the list of 'telefon' element items.
     * 
     * @return list
     */
    public List<Telefon> getTelefonList() {
        return telefonList;
    }

    /** 
     * Set the list of 'telefon' element items.
     * 
     * @param list
     */
    public void setTelefonList(List<Telefon> list) {
        telefonList = list;
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://braunimmobilien/webapp/person" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="telefon" minOccurs="0" maxOccurs="unbounded">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="xs:string" name="art" minOccurs="0"/>
     *       &lt;xs:element type="xs:string" name="telefon" minOccurs="0"/>
     *     &lt;/xs:sequence>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class Telefon implements Serializable
    {
        private String art;
        private String telefon;

        /** 
         * Get the 'art' element value.
         * 
         * @return value
         */
        public String getArt() {
            return art;
        }

        /** 
         * Set the 'art' element value.
         * 
         * @param art
         */
        public void setArt(String art) {
            this.art = art;
        }

        /** 
         * Get the 'telefon' element value.
         * 
         * @return value
         */
        public String getTelefon() {
            return telefon;
        }

        /** 
         * Set the 'telefon' element value.
         * 
         * @param telefon
         */
        public void setTelefon(String telefon) {
            this.telefon = telefon;
        }
    }
}
