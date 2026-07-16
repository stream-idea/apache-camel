package com.camel.bean;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;

public class JsonFileFilter implements GenericFileFilter {

    @Override
    public boolean accept(GenericFile file) {
        // this filter accepts only .json files
        return file.getFileName().endsWith(".json");

    }


}