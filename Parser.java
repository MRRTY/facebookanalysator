import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Greg on 28.05.2017.
 */
public class Parser {


    public static int getEndDivIndex(String content){
        String c = ""+content;
        int temp;
        int divCounter = 0;
        while (c.indexOf("<div")!=-1 && c.indexOf("<div")<c.indexOf("</div>")){
            divCounter++;

            c = c.substring(0,c.indexOf("<div")+2)+"x"+c.substring(c.indexOf("<div")+3);

        }
        for (int i = 1; i<divCounter; i++){
            c = c.substring(0,c.indexOf("</div")+2)+"x"+c.substring(c.indexOf("</div")+3);

        }
        return c.indexOf("</div>");
    }
    public static String getDivByIndex(String content, int index){
        return content.substring(0,index+6);
    }
    public static Pare getPareFromDiv(String content){
        String param1;
        String param2;
        while (content.trim().substring(0,1).equals("<")){
            content = content.substring(content.indexOf(">")+1);
        }

        int index = content.indexOf("<");
        param1 = content.substring(0,index).trim();
        content = content.substring(index);
        while (content.trim().substring(0,1).equals("<")){
            content = content.substring(content.indexOf(">")+1);
        }
        index = content.indexOf("<");
        param2 = content.substring(0,index).trim();

        return new Pare(param1,param2);
    }
    private static String getSingleParamFromDiv(String content){

        while (content.trim().substring(0,1).equals("<")){
            content = content.substring(content.indexOf(">")+1);
        }

        int index = content.indexOf("<");
        return content.substring(0,index);


    }


    public static String getSingleParam(String content){
       return getSingleParamFromDiv(getDivByIndex(content,getEndDivIndex(content)));
    }
}
