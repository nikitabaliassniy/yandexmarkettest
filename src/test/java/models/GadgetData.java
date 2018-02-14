package models;

import java.util.ArrayList;

/*
 * Содержит описание объектов-гаджетов
 */
public class GadgetData {

    //Тип товара
    private String type;
    //Название фирм-производителей
    private ArrayList<String> firmNames;
    //Начальная стоимость
    private String priceFrom;

    public GadgetData(String type, ArrayList<String> firmNames, String priceFrom){
        setType(type);
        setFirmNames(firmNames);
        setPriceFrom(priceFrom);
    }

    public String getType(){
        return type;
    }

    public ArrayList<String> getFirmNames(){
        return firmNames;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    private void setType(String type){
        this.type = type;
    }

    private void setFirmNames(ArrayList<String> firmNames){
        this.firmNames = firmNames;
    }

    private void setPriceFrom(String priceFrom){
        this.priceFrom = priceFrom;
    }

}
