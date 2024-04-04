package com.a701.nongstradamus.data.service;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public interface ProductDataService {
    void updateProductData() throws IOException, ParserConfigurationException, SAXException;
}
