package org.hbrs.se1.ws22.uebung9;

public class GraficDocument extends CoreDocument{
    private String url;

    public GraficDocument( String url){
        this.url=url;
    }

    public String getUrl( ){
        return url;
    }

    @Override
    public int size() {
        return 1200;
    }
}
