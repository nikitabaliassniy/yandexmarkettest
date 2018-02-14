package tests;

import models.GadgetData;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.testng.annotations.DataProvider;
import org.jdom.input.SAXBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Получение списка объектов для поиска из xml-файла
 */
public class DataProviderClass {

    @DataProvider(name = "dataProvider")
    public static Iterator<Object[]> getDataFromXMLFile() throws NullPointerException{
        final String testDataFile = "src/test/resources/testData.xml";
        SAXBuilder saxBuilder = new SAXBuilder();
        List<GadgetData> gadgets = new ArrayList<>();
        File xmlFile = new File(System.getProperty("user.dir")
                + File.separator + testDataFile);
        try {
            Document document = saxBuilder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> gadgetList = rootNode.getChildren("gadget");
            for (Element currentGadget : gadgetList) {
                ArrayList<String> firmNames = new ArrayList<>();
                List<Element> firms = currentGadget.getChild("firmNames").getChildren("firmName");
                for (Element currentFirm : firms)
                    firmNames.add(currentFirm.getText());
                GadgetData gagdet = new GadgetData(
                        currentGadget.getChildText("type"),
                        firmNames,
                        currentGadget.getChildText("priceFrom")
                );
                gadgets.add(gagdet);
            }
        } catch (JDOMException | IOException ex)

        {
            ex.printStackTrace();
        }
        return gadgets.stream().map((g)->new Object[] {g}).collect(Collectors.toList()).iterator();
    }

}


