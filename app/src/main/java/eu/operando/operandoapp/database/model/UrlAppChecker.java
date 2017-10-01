package eu.operando.operandoapp.database.model;

/**
 * Created by fanis on 1/1/2017.
 */
public class UrlAppChecker {
    public String app_name;
    public String domainurl;
    public int count;
    public int duration;
    public UrlAppChecker(String app_name,String domainurl, int count, int secs){
        this.app_name = app_name;
        this.domainurl = domainurl;
        this.count = count;
        this.duration = duration;
    }
    public UrlAppChecker(){
        this.app_name = null;
        this.domainurl = null;
        this.count = 0;
        this.duration = 0;
    }
}
