package eu.operando.operandoapp.util;

import android.content.Context;
import android.util.Log;

import java.util.*;


import eu.operando.operandoapp.MainContext;
import eu.operando.operandoapp.database.model.UrlAppChecker;

/**
 * Created by fanis on 4/30/2017.
 */

public class URLCheckerUtils {
    private static MainContext mainContext = MainContext.INSTANCE;

    private static List<UrlAppChecker> currentURLAppChecker = new ArrayList<>();
    private static boolean recording = false;
    private static String appName = null;
    private static Date startDate = new java.util.Date();




    private static class NamedUrlAppChecker extends UrlAppChecker
    {
        private String checker_name;
        public NamedUrlAppChecker(String checker_name,String app_name, String domainurl, int count, int secs){
            this.app_name = app_name;
            this.domainurl = domainurl;
            this.count = count;
            this.checker_name = checker_name;
        }
        public NamedUrlAppChecker(String checker_name,UrlAppChecker urlAppChecker){
            this.app_name = urlAppChecker.app_name;
            this.domainurl = urlAppChecker.domainurl;
            this.count = urlAppChecker.count;
            this.checker_name = checker_name;
        }
        public NamedUrlAppChecker(){
            UrlAppChecker urlAppChecker = new UrlAppChecker();
            this.app_name = urlAppChecker.app_name;
            this.domainurl = urlAppChecker.domainurl;
            this.count = urlAppChecker.count;
            this.checker_name = "";
        }

    }
    private static List<NamedUrlAppChecker> RunningURLAppCheckers = new ArrayList<>();

    public static void addToMonitor(String checker_name,List<UrlAppChecker> urlAppCheckers){
        for (UrlAppChecker urlAppChecker:urlAppCheckers) {
            if (checker_name.equals(urlAppChecker.app_name)) {
                NamedUrlAppChecker namedUrlAppChecker = new NamedUrlAppChecker(checker_name, urlAppChecker);
                RunningURLAppCheckers.add(namedUrlAppChecker);
            }
        }


    }
    public static void removeFromMonitor(String checker_name){

        Iterator<NamedUrlAppChecker> itr = RunningURLAppCheckers.iterator();
        // remove all even numbers
        while (itr.hasNext()) {
            NamedUrlAppChecker curr = itr.next();
            if (curr.app_name.equals(checker_name)) { itr.remove(); }
        }

    }
    public static void startRecording(String app_name){
        currentURLAppChecker =  new ArrayList<>();
        appName = app_name;
        recording = true;
        startDate = new java.util.Date();
    }
    public static List<UrlAppChecker> stopRecording(String app_name) {
        recording = false;
        java.util.Date now = new java.util.Date();
        int duration = (int)((now.getTime() - startDate.getTime()) / 1000);
        if (app_name.toString() == "mock".toString()) {
            for (int x = 10; x < 200; x = x + 9) {
                UrlAppChecker addme = new UrlAppChecker(app_name,"http://appname" + x,  1,duration);
                currentURLAppChecker.add(addme);
            }
        }
        else {
            for (UrlAppChecker a : currentURLAppChecker) {
                a.duration = duration;
                a.count=1;
            }
        }
        appName = null;
        return currentURLAppChecker;
    }
    public static boolean isRecording(){
        if (appName ==null) {return false;}
        else {return true;}
    }
    public static void checkURLAppChecker(Context context, String domainurl){

        Iterator<NamedUrlAppChecker> itr = RunningURLAppCheckers.iterator();
        // remove all even numbers
        while (itr.hasNext()) {
            NamedUrlAppChecker curr = itr.next();
            if (curr.domainurl.equalsIgnoreCase(domainurl)) {
                int possision =RunningURLAppCheckers.indexOf(curr);
                curr.count = 0;
                RunningURLAppCheckers.set(possision,curr);
        //        checkForCompletedURLAppChecker(context);
            }
        }


        checkForCompletedURLAppChecker(context);
        return;
    }
    public static void checkForCompletedURLAppChecker(Context context){
        List<NamedUrlAppChecker> groups = new ArrayList<>();
        for (NamedUrlAppChecker named :RunningURLAppCheckers) {
            boolean notfound = true;
            for (UrlAppChecker group:groups) {
                if (group.app_name.equalsIgnoreCase(named.app_name)) {
                    groups.get(groups.indexOf(group)).count = groups.get(groups.indexOf(group)).count + group.count;
                    notfound =false;
                }
            }
            if(notfound) {
                named.domainurl = "";
                groups.add(named);
            }
        }
        for (UrlAppChecker group:groups) {
            if (group.count<=0){
                removeFromMonitor(group.app_name);
                int notificationId = mainContext.getNotificationId();
                mainContext.getNotificationUtil().displayFoundNotification(context, group.app_name, notificationId);
            }
        }
        return ;
    }
    public static boolean addURLAppChecker( String domainurl){
        Log.d("forrecording",domainurl);
        boolean notfound = true;
        for (UrlAppChecker a :currentURLAppChecker) {
            if (a.app_name.equalsIgnoreCase(appName) && a.domainurl.equalsIgnoreCase(domainurl))
            {
                notfound = false;
                RunningURLAppCheckers.get(currentURLAppChecker.indexOf(a)).count = a.count+1;
                return true;
            }
        }
        Log.d("forrecording","recorded on " + appName);
        if (notfound){
            currentURLAppChecker.add(new UrlAppChecker(appName,domainurl,1,0));
        }
        Log.d("forrecording","-----------");
        return true;
    }


}
