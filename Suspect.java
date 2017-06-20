import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Greg on 28.05.2017.
 */
public class Suspect {
    private String contextInfo;
    private String contextMain;
    private String contextVisits;
    private String url;
    private String name;
    private int friends;
    private int photoes;
    private int visits;
    private List<String> job = new ArrayList<>();
    private List<String> edu = new ArrayList<>();
    private List<Pare> prop = new ArrayList<>();
    private List<Action> actions = new ArrayList<>();

    public Suspect(String url) throws IOException, GeneralSecurityException {
        this.url = url;
        contextInfo = GetPageSourceAfterJS.getSource(url+"/about");
        contextMain = GetPageSourceAfterJS.getSource(url+"/albums/152207648136825/");
        contextVisits = GetPageSourceAfterJS.getSource("https://mobile.facebook.com/timeline/app_section/?section_token=100000427733037%3A302324425790");
        contextInfo = contextInfo.substring(contextInfo.indexOf("<div class=\"bo\""));
        this.name = Parser.getSingleParam(contextInfo).trim();
        contextInfo = contextInfo.substring(contextInfo.indexOf("<div class=\"bh\""));
        this.friends = Integer.parseInt(Parser.getSingleParam(contextInfo).replaceAll("\\D+",""));
        readJobs();
        contextInfo = contextInfo.substring(contextInfo.indexOf("<div id=\"education\""));
        readEdu();
        contextInfo = contextInfo.substring(contextInfo.indexOf("<div id=\"living\""));
        readProp();
        contextInfo = contextInfo.substring(contextInfo.indexOf("<div class=\"cv\""));
        readActions();
        readPhotoes();
        readVisits();
    }

    public static double result(Suspect suspect){
        int friends = suspect.getFriends();
        int photoes = suspect.getPhotoes();
        int visits = suspect.getVisits();
        int jobs = suspect.getJob().size();
        int edu = suspect.getEdu().size();
        int prop = suspect.getProp().size();
        int actions = suspect.getActions().size();

        if(friends>100)
            friends = 100;
        if(photoes>10) {
            photoes = 100;
        }else {
            photoes *=10;
        }
        if(visits>25){
            visits = 100;
        }else {
            visits *= 4;
        }
        actions +=prop;
        if(actions>10){
            actions = 100;
        }else {
            actions *= 10;
        }
        jobs += edu;
        if(jobs>5){
            jobs = 100;
        }else {
            jobs *=20;
        }
        return (friends+photoes+visits+actions+jobs)/5;
    }

    private void readVisits(){


        while (contextVisits.indexOf("<span class=\"bw bx by bz\"")!=-1){
            contextVisits = contextVisits.substring(contextVisits.indexOf("<span class=\"bw bx by bz\""));
            visits += Integer.parseInt(Parser.getSingleParam(contextVisits).trim());
            contextVisits = contextVisits.substring(0,1)+"x"+contextVisits.substring(2);
        }

    }
    private void readPhotoes(){
       contextMain = contextMain.substring(contextMain.indexOf("<div style=\""));
       int counter = 0;
       while (contextMain.indexOf("<a class=\"bu bv\"")!=-1){
           contextMain = contextMain.substring(contextMain.indexOf("<a class=\"bu bv\""));
           counter++;
           contextMain = contextMain.substring(0,1)+"x"+contextMain.substring(2);

       }
       this.photoes = counter;
    }
    private void readEdu(){
        while (contextInfo.indexOf("<div class=\"cu cv\"")!=-1){
            contextInfo = contextInfo.substring(contextInfo.indexOf("<div class=\"cu cv\""));
            edu.add(Parser.getSingleParam(contextInfo).trim());
            contextInfo = contextInfo.substring(0,2)+"x"+contextInfo.substring(3);
        }
    }

    private void readActions(){
        while (contextInfo.indexOf("<div class=\"dt du\"")!=-1){
            contextInfo = contextInfo.substring(contextInfo.indexOf("<div class=\"dt du\""));
            String year = Parser.getSingleParam(contextInfo).trim().substring(0,4);
            contextInfo = contextInfo.substring(0,2)+"x"+contextInfo.substring(3);
            while (contextInfo.indexOf("<div class=\"dx bo\"")!=-1 && contextInfo.indexOf("<div class=\"dt du\"")>contextInfo.indexOf("<div class=\"dx bo\"")){
                contextInfo = contextInfo.substring(contextInfo.indexOf("<div class=\"dx bo\""));
                String act = Parser.getSingleParam(contextInfo).trim();
                actions.add(new Action(act,Integer.parseInt(year)));
                contextInfo = contextInfo.substring(0,2)+"x"+contextInfo.substring(3);
            }

        }
    }
    private void readJobs(){
        while (contextInfo.indexOf("<div class=\"cu cv\"")<contextInfo.indexOf("<div id=\"education\"") && contextInfo.indexOf("<div class=\"cu cv\"")!=-1){
            contextInfo = contextInfo.substring(contextInfo.indexOf("<div class=\"cu cv\""));
            job.add(Parser.getSingleParam(contextInfo).trim());
            contextInfo = contextInfo.substring(0,2)+"x"+contextInfo.substring(3);
        }

    }
    private void readProp(){

        while (contextInfo.indexOf("<div class=\"cu cv di")!= -1 && contextInfo.indexOf("<div class=\"cu cv di")< contextInfo.indexOf("<div id=\"nicknames\"")){

            contextInfo = contextInfo.substring(contextInfo.indexOf("<div class=\"cu cv di"));
            prop.add(Parser.getPareFromDiv(contextInfo));
            contextInfo = contextInfo.substring(0,2)+"x"+contextInfo.substring(3);
        }
    }



    public void addProp(Pare pare){
        prop.add(pare);
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public int getPhotoes() {
        return photoes;
    }

    public void setPhotoes(int photoes) {
        this.photoes = photoes;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public List<String> getJob() {
        return job;
    }

    public void setJob(List<String> job) {
        this.job = job;
    }

    public List<String> getEdu() {
        return edu;
    }

    public void setEdu(List<String> edu) {
        this.edu = edu;
    }

    public List<Pare> getProp() {
        return prop;
    }

    public void setProp(List<Pare> prop) {
        this.prop = prop;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Suspect{" +
                "\n url='" + url + '\'' +
                "\n, name='" + name + '\'' +
                "\n, friends=" + friends +
                "\n, visits=" + visits +
                "\n photoes='" + photoes + '\'' +
                "\n, job=" + job +
                "\n, edu=" + edu +
                "\n, prop=" + prop +
                "\n, actions=" + actions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Suspect suspect = (Suspect) o;

        if (!url.equals(suspect.url)) return false;
        if (!name.equals(suspect.name)) return false;
        if (job != null ? !job.equals(suspect.job) : suspect.job != null) return false;
        if (edu != null ? !edu.equals(suspect.edu) : suspect.edu != null) return false;
        if (prop != null ? !prop.equals(suspect.prop) : suspect.prop != null) return false;
        return actions != null ? actions.equals(suspect.actions) : suspect.actions == null;
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (edu != null ? edu.hashCode() : 0);
        result = 31 * result + (prop != null ? prop.hashCode() : 0);
        result = 31 * result + (actions != null ? actions.hashCode() : 0);
        return result;
    }
}
