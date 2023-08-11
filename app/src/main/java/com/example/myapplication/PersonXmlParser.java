package com.example.myapplication;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;

public class PersonXmlParser extends DefaultHandler {
    private final String TAG = "demo";
    private StringBuilder buffer = new StringBuilder();
    private Person person;

    // Parse the XML data from the given InputStream and return a Person object
    public Person parse(InputStream inputStream) throws IOException, SAXException {
        Xml.parse(inputStream, Xml.Encoding.UTF_8, this);
        return person;
    }

    // Called when the XML document parsing starts
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        Log.d(TAG, "startDocument: ");
    }

    // Called when the XML document parsing ends
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        Log.d(TAG, "endDocument: ");
    }

    // Called when the parser encounters the opening tag of an XML element
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        // Check if the element is a "person" element
        if (localName.equals("person")) {
            person = new Person();
            person.setId(attributes.getValue("id"));
        }

        Log.d(TAG, "startElement: " + localName);

        // Clear the buffer to accumulate character data within the element
        buffer.setLength(0);
    }

    // Called when the parser encounters the closing tag of an XML element
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        // Populate the Person object based on the element's local name
        if (localName.equals("name")) {
            person.setName(buffer.toString());
        } else if (localName.equals("age")) {
            person.setAge(buffer.toString());
        } else if (person.getAddress() != null) {
            switch (localName) {
                case "line1":
                    person.getAddress().setLine(buffer.toString());
                    break;
                case "city":
                    person.getAddress().setCity(buffer.toString());
                    break;
                case "state":
                    person.getAddress().setState(buffer.toString());
                    break;
                case "zip":
                    person.getAddress().setZip(buffer.toString());
                    break;
            }
        }

        Log.d(TAG, "endElement: " + localName);
    }

    // Called when the parser encounters character data within an XML element
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        // Append the character data to the buffer
        buffer.append(ch, start, length);

        Log.d(TAG, "characters: " + buffer.toString());
    }
}
