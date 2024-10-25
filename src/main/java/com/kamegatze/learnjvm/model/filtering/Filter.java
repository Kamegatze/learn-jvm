package com.kamegatze.learnjvm.model.filtering;

import org.springframework.data.domain.Sort;

import java.util.Objects;

public class Filter implements Cloneable {

    private String direction;
    private String property;
    private String value;
    private String nameAsc;
    private String nameDesc;
    private String label;

    public Filter(Sort.Direction direction, String property, String nameAsc,
                  String nameDesc, String label) {
        this.direction = Objects.isNull(direction) ? "" : direction.name().toLowerCase();
        this.property = property;
        this.label = label;
        this.value = Objects.isNull(direction) ? "" : String.format("%s,%s", property, direction.name().toLowerCase());
        this.nameAsc = nameAsc;
        this.nameDesc = nameDesc;
    }

    public Filter(String direction, String property, String nameAsc,
                  String nameDesc, String label) {
        this.direction = Objects.isNull(direction) ? "" : direction.toLowerCase();
        this.property = property;
        this.label = label;
        this.value = Objects.isNull(direction) ? "" : String.format("%s,%s", property, direction.toLowerCase());
        this.nameAsc = nameAsc;
        this.nameDesc = nameDesc;
    }
    
    private Filter(Filter filter) {
        this(filter.getDirection(), filter.getProperty(), filter.getNameAsc(), filter.getNameDesc(), filter.getLabel());
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = Objects.isNull(direction) ? "" : direction.name().toLowerCase();;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getNameDesc() {
        return nameDesc;
    }

    public void setNameDesc(String nameDesc) {
        this.nameDesc = nameDesc;
    }

    public void setNameAsc(String nameAsc) {
        this.nameAsc = nameAsc;
    }

    public String getNameAsc() {
        return nameAsc;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Filter clone() {
        try {
            return  (Filter) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Filter(this);
        }
    }
}
