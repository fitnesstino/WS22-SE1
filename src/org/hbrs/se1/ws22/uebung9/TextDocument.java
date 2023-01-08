package org.hbrs.se1.ws22.uebung9;

import java.io.UnsupportedEncodingException;

public class TextDocument extends CoreDocument{

    enum Encoding{
        UTF8,
        UTF16,
        UTF32
    }

    private String[] encodingNames={ "UTF-8" , "UTF-16" , "UTF-32" };
    private Encoding encoding;
    private String content;

    public TextDocument( String inhalt, Encoding encoding){
        content = inhalt;
        this.encoding = encoding;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int size() {
        try {
            return content.getBytes(encodingNames[encoding.ordinal()]).length;
        }catch(UnsupportedEncodingException e){}
        return 0;
    }

}
