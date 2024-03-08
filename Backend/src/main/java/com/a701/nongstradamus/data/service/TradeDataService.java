package com.a701.nongstradamus.data.service;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.xml.sax.SAXException;

public interface TradeDataService {
    void updateTradeData() throws ParserConfigurationException, IOException, SAXException;
}
