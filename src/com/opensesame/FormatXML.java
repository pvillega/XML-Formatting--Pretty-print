/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensesame;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

import java.io.File;
import java.io.FileOutputStream;
import org.w3c.dom.Document;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 *
 * @author pvillega
 */
public class FormatXML {

    private static DocumentBuilderFactory dbf = null;
    private static DocumentBuilder db = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Wrong arguments");
            System.exit(1);
        }
        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();   
        File path = new File(args[0]);
        process(path);
    }

    public static void process(File path) throws Exception {
        File[] xml = path.listFiles();
        for (int i = 0; i < xml.length; i++) {
            if (xml[i].isDirectory()) {
                process(xml[i]);
            } else if (xml[i].isFile() && xml[i].getName().endsWith(".xml")) {
                System.out.println(xml[i].getName());
                serialize(db.parse(xml[i]), new FileOutputStream(xml[i]));
            }
        }
    }

    public static void serialize(Document doc, OutputStream out) throws Exception {
        OutputFormat format = new OutputFormat(doc);
        format.setLineWidth(100);
        format.setIndenting(true);
        format.setIndent(2);
        XMLSerializer serializer = new XMLSerializer(out, format);
        serializer.serialize(doc);
    }
}
