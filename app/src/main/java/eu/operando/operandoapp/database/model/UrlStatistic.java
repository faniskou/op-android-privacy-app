package eu.operando.operandoapp.database.model;

/**
 * Created by periklismaravelias on 31/05/16.
 */
public class UrlStatistic {
    public String info;
    public String exfiltrated;

    public UrlStatistic(String info, String exfiltrated){
        this.info = info.replaceAll("\\(.+?\\)", "");
        this.exfiltrated = exfiltrated;
    }
}