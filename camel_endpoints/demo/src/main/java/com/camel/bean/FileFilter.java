package com.camel.bean;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;

public class FileFilter implements GenericFileFilter {

    @Override
    public boolean accept(GenericFile file) {
        // this filter will accept only .txt files
        return file.getFileName().endsWith(".txt");

    }
    
}
