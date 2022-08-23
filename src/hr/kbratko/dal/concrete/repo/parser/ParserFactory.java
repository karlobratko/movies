/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.parser;

import java.io.InputStream;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author kbratko
 */
public final class ParserFactory {

  private ParserFactory() {
  }

  public static XMLEventReader getSAXParser(InputStream stream)
    throws XMLStreamException {
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLEventReader eventReader = factory.createXMLEventReader(stream);
    return eventReader;
  }

}
