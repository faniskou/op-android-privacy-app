package eu.operando.operandoapp.database.model;

/**
 * Created by fanis on 1/1/2017.
 */
public class UrlStatistic {
    public String domainurl;
    public int count;
    public String modified;
    public String sourceactivity;
    public int hidden;
    public String category;

    public UrlStatistic(String domainurl,int count,String modified,int hidden,String sourceactivity,String category){
        this.domainurl = domainurl;
        this.count = count;
        this.hidden = hidden;
        this.modified = modified;
        this.sourceactivity = sourceactivity;
        this.category = category;
    }
    public UrlStatistic(){
        this.domainurl = null;
        this.count = 0;
        this.hidden = 0;
        this.modified = null;
        this.sourceactivity = null;
        this.category = null;
    }
}
