import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.io.*;
import java.util.*;

public class Utilities {
	//getting front components of each part
	public static String parseNameRegex(String s) {
    	String name = "";
        String pattern = "<android\\.[a-zA-Z0-9]*\\.[a-zA-Z0-9]*\\s";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);
        while (m.find()) {
        	 //  System.out.println(m.group());
        	   name = name + m.group();
        }
        if(name.equals("")) {
        	System.out.println("ERROR:regex parse");
        	return "ERROR";
        }else {
        	return name;
        }
    	
    }
	//will have to modify clickableCount to consider NAF
	public static int clickableCount(String s) {
		int i =0;
		 //String pattern = "<android.*clickable=\"true\".*>";
		 String pattern = "clickable=\"true\"";
	     Pattern r = Pattern.compile(pattern);
	     Matcher m = r.matcher(s);
	     while (m.find()) {
	        //  System.out.println(m.group());
	        i++;
	     }
		return i;
		
	}
	
	public static boolean isLoadPage(String s) {
		 String pattern = "YES|Yes|Accept|ACCEPT|NEXT|Next|AGREE|Agree|START|Start";
	     Pattern r = Pattern.compile(pattern);
	     Matcher m = r.matcher(s);
		boolean status = false;
		status = clickableCount(s)<3 && m.find();
		return status;
	}
	
	public static String foundString(String s) {
		 String txt="";
		 String pattern = "YES|Yes|Accept|ACCEPT|NEXT|Next|AGREE|Agree|START|Start";
	     Pattern r = Pattern.compile(pattern);
	     Matcher m = r.matcher(s);
	     if(m.find()) {
	    	 txt=m.group();
	     }
	     return txt;
	}
	
	public static String getPackageName(String s) {
		String packageName ="";
		String pattern = "package=[\".a-zA-Z0-9-]*";
	    Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(s);
	    if(m.find()) {
	        packageName = packageName + m.group();
	    }
	    return packageName;
	}
	
	//LevenShteinDistance
	//will need to see if 80% of the total length of string
	public static int levenshteinDistance (CharSequence lhs, CharSequence rhs) {                          
	    int len0 = lhs.length() + 1;                                                     
	    int len1 = rhs.length() + 1;                                                     
	                                                                                    
	    // the array of distances                                                       
	    int[] cost = new int[len0];                                                     
	    int[] newcost = new int[len0];                                                  
	                                                                                    
	    // initial cost of skipping prefix in String s0                                 
	    for (int i = 0; i < len0; i++) cost[i] = i;                                     
	                                                                                    
	    // dynamically computing the array of distances                                  
	                                                                                    
	    // transformation cost for each letter in s1                                    
	    for (int j = 1; j < len1; j++) {                                                
	        // initial cost of skipping prefix in String s1                             
	        newcost[0] = j;                                                             
	                                                                                    
	        // transformation cost for each letter in s0                                
	        for(int i = 1; i < len0; i++) {                                             
	            // matching current letters in both strings                             
	            int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;             
	                                                                                    
	            // computing cost for each transformation                               
	            int cost_replace = cost[i - 1] + match;                                 
	            int cost_insert  = cost[i] + 1;                                         
	            int cost_delete  = newcost[i - 1] + 1;                                  
	                                                                                    
	            // keep minimum cost                                                    
	            newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
	        }                                                                           
	                                                                                    
	        // swap cost/newcost arrays                                                 
	        int[] swap = cost; cost = newcost; newcost = swap;                          
	    }                                                                               
	                                                                                    
	    // the distance is the cost for transforming all letters in both strings        
	    return cost[len0 - 1];                                                          
	}
	
	public static boolean isSimilar(String s1, String s2) {
		if(s1.equals(s2)) {
			System.out.println("Leven Distance is: 0");
			return true;
		}
		boolean similarity = false;
		float maxLength = Math.max(s1.length(), s2.length());
		float levenDis = levenshteinDistance(s1,s2);
		System.out.println("Leven Distance is: "+levenDis/maxLength);
		if(0.1> levenDis/maxLength) {
			
			similarity = true;
			System.out.println("similar");
		}else {
			System.out.println("not similar");
		}
		
		return similarity;
	}
	//for loging current time
    public static void log(String info){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt", true));
            String now = LocalDateTime.now().toString();
            System.out.println(now);
            bw.append(info+" "+now);
            bw.newLine();
            bw.close();
        }catch(Exception e){
            System.out.println("error in logging");
        }
    }

	
	
	
	
	
	
	
	
public static void main(String[] args) {
		
		String s = "firstPage name is: <?xml version=\"1.0\" encoding=\"UTF-8\"?><hierarchy rotation=\"0\"><android.widget.FrameLayout index=\"0\" text=\"\" class=\"android.widget.FrameLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,1812]\" resource-id=\"\" instance=\"0\"><android.widget.LinearLayout index=\"0\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,1812]\" resource-id=\"\" instance=\"0\"><android.widget.FrameLayout index=\"0\" text=\"\" class=\"android.widget.FrameLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,1812]\" resource-id=\"\" instance=\"1\"><android.widget.LinearLayout index=\"0\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,1812]\" resource-id=\"com.apps.go.clean.boost.master:id/eh\" instance=\"1\"><android.widget.FrameLayout index=\"0\" text=\"\" class=\"android.widget.FrameLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,1812]\" resource-id=\"android:id/content\" instance=\"2\"><android.widget.RelativeLayout index=\"0\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,1812]\" resource-id=\"com.apps.go.clean.boost.master:id/abu\" instance=\"0\"><android.support.v4.view.ViewPager index=\"0\" text=\"\" class=\"android.support.v4.view.ViewPager\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"true\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,72][1080,1674]\" resource-id=\"com.apps.go.clean.boost.master:id/m8\" instance=\"0\"><android.widget.RelativeLayout index=\"0\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,72][1080,1674]\" resource-id=\"com.apps.go.clean.boost.master:id/abu\" instance=\"1\"><android.widget.LinearLayout index=\"0\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,72][1080,1674]\" resource-id=\"\" instance=\"2\"><android.widget.RelativeLayout index=\"0\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,72][1080,1186]\" resource-id=\"com.apps.go.clean.boost.master:id/ae8\" instance=\"2\"><android.view.ViewGroup index=\"0\" text=\"\" class=\"android.view.ViewGroup\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,72][1080,240]\" resource-id=\"com.apps.go.clean.boost.master:id/f7\" instance=\"0\"><android.widget.TextView index=\"0\" text=\"Super Cleaner\" class=\"android.widget.TextView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[48,115][423,196]\" resource-id=\"\" instance=\"0\"/></android.view.ViewGroup><android.support.v4.view.ViewPager index=\"2\" text=\"\" class=\"android.support.v4.view.ViewPager\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,240][1080,1186]\" resource-id=\"com.apps.go.clean.boost.master:id/m8\" instance=\"1\"><android.widget.RelativeLayout index=\"0\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,240][1080,1186]\" resource-id=\"com.apps.go.clean.boost.master:id/a2z\" instance=\"3\"><android.view.ViewGroup index=\"0\" text=\"\" class=\"android.view.ViewGroup\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[225,324][855,954]\" resource-id=\"com.apps.go.clean.boost.master:id/aeu\" instance=\"1\"><android.widget.ImageView index=\"0\" text=\"\" class=\"android.widget.ImageView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[225,324][855,954]\" resource-id=\"com.apps.go.clean.boost.master:id/aev\" instance=\"0\"/><android.widget.RelativeLayout index=\"1\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[291,390][789,888]\" resource-id=\"\" instance=\"4\"><android.view.View index=\"0\" text=\"\" class=\"android.view.View\" package=\"com.apps.go.clean.boost.master\" content-desc=\"56\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[291,509][789,707]\" resource-id=\"com.apps.go.clean.boost.master:id/a0a\" instance=\"0\"/><android.widget.TextView index=\"1\" text=\"Boost\" class=\"android.widget.TextView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[481,707][599,768]\" resource-id=\"com.apps.go.clean.boost.master:id/aex\" instance=\"1\"/></android.widget.RelativeLayout></android.view.ViewGroup></android.widget.RelativeLayout></android.support.v4.view.ViewPager></android.widget.RelativeLayout><android.widget.LinearLayout index=\"1\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,1186][1080,1674]\" resource-id=\"\" instance=\"3\"><android.widget.LinearLayout index=\"0\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,1186][1080,1430]\" resource-id=\"\" instance=\"4\"><android.widget.RelativeLayout index=\"0\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,1186][540,1430]\" resource-id=\"com.apps.go.clean.boost.master:id/aeg\" instance=\"5\"><android.widget.LinearLayout index=\"0\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[164,1202][376,1413]\" resource-id=\"\" instance=\"5\"><android.widget.FrameLayout index=\"0\" text=\"\" class=\"android.widget.FrameLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[216,1232][324,1340]\" resource-id=\"\" instance=\"3\"><android.widget.ImageView index=\"0\" text=\"\" class=\"android.widget.ImageView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[216,1232][324,1340]\" resource-id=\"com.apps.go.clean.boost.master:id/ag8\" instance=\"1\"/><android.widget.ImageView index=\"1\" text=\"\" class=\"android.widget.ImageView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[297,1232][324,1259]\" resource-id=\"com.apps.go.clean.boost.master:id/ag9\" instance=\"2\"/></android.widget.FrameLayout><android.widget.TextView index=\"1\" text=\"Junk Files\" class=\"android.widget.TextView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"true\" bounds=\"[164,1352][367,1413]\" resource-id=\"com.apps.go.clean.boost.master:id/ag5\" instance=\"2\"/></android.widget.LinearLayout><android.view.View index=\"1\" text=\"\" class=\"android.view.View\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[166,1204][374,1412]\" resource-id=\"\" instance=\"1\"/></android.widget.RelativeLayout><android.widget.RelativeLayout index=\"1\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[540,1186][1080,1430]\" resource-id=\"com.apps.go.clean.boost.master:id/aeh\" instance=\"6\"><android.widget.LinearLayout index=\"0\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[679,1202][941,1413]\" resource-id=\"\" instance=\"6\"><android.widget.FrameLayout index=\"0\" text=\"\" class=\"android.widget.FrameLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[756,1232][864,1340]\" resource-id=\"\" instance=\"4\"><android.widget.ImageView index=\"0\" text=\"\" class=\"android.widget.ImageView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[756,1232][864,1340]\" resource-id=\"com.apps.go.clean.boost.master:id/ag3\" instance=\"3\"/></android.widget.FrameLayout><android.widget.TextView index=\"1\" text=\"Phone Boost\" class=\"android.widget.TextView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"true\" bounds=\"[679,1352][932,1413]\" resource-id=\"com.apps.go.clean.boost.master:id/ag5\" instance=\"3\"/></android.widget.LinearLayout><android.view.View index=\"1\" text=\"\" class=\"android.view.View\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[706,1204][914,1412]\" resource-id=\"\" instance=\"2\"/></android.widget.RelativeLayout></android.widget.LinearLayout><android.widget.LinearLayout index=\"1\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,1430][1080,1674]\" resource-id=\"\" instance=\"7\"><android.widget.RelativeLayout index=\"0\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,1430][540,1674]\" resource-id=\"com.apps.go.clean.boost.master:id/aei\" instance=\"7\"><android.view.View index=\"0\" text=\"\" class=\"android.view.View\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[166,1448][374,1656]\" resource-id=\"\" instance=\"3\"/><android.widget.LinearLayout index=\"1\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[180,1446][360,1657]\" resource-id=\"\" instance=\"8\"><android.widget.ImageView index=\"0\" text=\"\" class=\"android.widget.ImageView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[216,1446][324,1554]\" resource-id=\"com.apps.go.clean.boost.master:id/ag3\" instance=\"4\"/><android.widget.TextView index=\"1\" text=\"Antivirus\" class=\"android.widget.TextView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"true\" bounds=\"[180,1566][360,1627]\" resource-id=\"com.apps.go.clean.boost.master:id/ag5\" instance=\"4\"/></android.widget.LinearLayout></android.widget.RelativeLayout><android.widget.RelativeLayout index=\"1\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[540,1430][1080,1674]\" resource-id=\"com.apps.go.clean.boost.master:id/aej\" instance=\"8\"><android.widget.LinearLayout index=\"0\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[674,1446][946,1657]\" resource-id=\"\" instance=\"9\"><android.widget.FrameLayout index=\"0\" text=\"\" class=\"android.widget.FrameLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[756,1446][864,1554]\" resource-id=\"\" instance=\"5\"><android.widget.ImageView index=\"0\" text=\"\" class=\"android.widget.ImageView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[756,1446][864,1554]\" resource-id=\"com.apps.go.clean.boost.master:id/ag3\" instance=\"5\"/><android.widget.ImageView index=\"1\" text=\"\" class=\"android.widget.ImageView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[837,1446][864,1473]\" resource-id=\"com.apps.go.clean.boost.master:id/ag6\" instance=\"6\"/></android.widget.FrameLayout><android.widget.TextView index=\"1\" text=\"Battery Saver\" class=\"android.widget.TextView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"true\" bounds=\"[674,1566][946,1627]\" resource-id=\"com.apps.go.clean.boost.master:id/ag5\" instance=\"5\"/></android.widget.LinearLayout><android.view.View index=\"1\" text=\"\" class=\"android.view.View\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[706,1448][914,1656]\" resource-id=\"\" instance=\"4\"/></android.widget.RelativeLayout></android.widget.LinearLayout></android.widget.LinearLayout></android.widget.LinearLayout></android.widget.RelativeLayout></android.support.v4.view.ViewPager><android.widget.LinearLayout index=\"1\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,1674][1080,1812]\" resource-id=\"com.apps.go.clean.boost.master:id/abw\" instance=\"10\"><android.view.View index=\"0\" text=\"\" class=\"android.view.View\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,1674][1080,1676]\" resource-id=\"\" instance=\"5\"/><android.widget.HorizontalScrollView index=\"1\" text=\"\" class=\"android.widget.HorizontalScrollView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,1676][1080,1812]\" resource-id=\"com.apps.go.clean.boost.master:id/s9\" instance=\"0\"><android.widget.LinearLayout index=\"0\" text=\"\" class=\"android.widget.LinearLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,1676][1080,1812]\" resource-id=\"\" instance=\"11\"><android.widget.RelativeLayout index=\"0\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"true\" bounds=\"[0,1676][360,1812]\" resource-id=\"\" instance=\"9\"><android.widget.RelativeLayout index=\"0\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"true\" bounds=\"[141,1682][219,1797]\" resource-id=\"com.apps.go.clean.boost.master:id/af9\" instance=\"10\"><android.widget.ImageView index=\"0\" text=\"\" class=\"android.widget.ImageView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"true\" bounds=\"[141,1682][219,1760]\" resource-id=\"com.apps.go.clean.boost.master:id/af_\" instance=\"7\"/><android.widget.TextView index=\"1\" text=\"Home\" class=\"android.widget.TextView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"true\" bounds=\"[144,1760][216,1797]\" resource-id=\"com.apps.go.clean.boost.master:id/afa\" instance=\"6\"/></android.widget.RelativeLayout></android.widget.RelativeLayout><android.widget.RelativeLayout index=\"1\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[360,1676][720,1812]\" resource-id=\"\" instance=\"11\"><android.widget.RelativeLayout index=\"0\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[501,1682][579,1797]\" resource-id=\"com.apps.go.clean.boost.master:id/af9\" instance=\"12\"><android.widget.ImageView index=\"0\" text=\"\" class=\"android.widget.ImageView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[501,1682][579,1760]\" resource-id=\"com.apps.go.clean.boost.master:id/af_\" instance=\"8\"/><android.widget.TextView index=\"1\" text=\"Tools\" class=\"android.widget.TextView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[507,1760][573,1797]\" resource-id=\"com.apps.go.clean.boost.master:id/afa\" instance=\"7\"/></android.widget.RelativeLayout></android.widget.RelativeLayout><android.widget.RelativeLayout index=\"2\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[720,1676][1080,1812]\" resource-id=\"\" instance=\"13\"><android.widget.RelativeLayout index=\"0\" text=\"\" class=\"android.widget.RelativeLayout\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[861,1682][939,1797]\" resource-id=\"com.apps.go.clean.boost.master:id/af9\" instance=\"14\"><android.widget.ImageView index=\"0\" text=\"\" class=\"android.widget.ImageView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[861,1682][939,1760]\" resource-id=\"com.apps.go.clean.boost.master:id/af_\" instance=\"9\"/><android.widget.TextView index=\"1\" text=\"Me\" class=\"android.widget.TextView\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[881,1760][919,1797]\" resource-id=\"com.apps.go.clean.boost.master:id/afa\" instance=\"8\"/></android.widget.RelativeLayout></android.widget.RelativeLayout></android.widget.LinearLayout></android.widget.HorizontalScrollView></android.widget.LinearLayout></android.widget.RelativeLayout></android.widget.FrameLayout></android.widget.LinearLayout></android.widget.FrameLayout></android.widget.LinearLayout><android.view.View index=\"1\" text=\"\" class=\"android.view.View\" package=\"com.apps.go.clean.boost.master\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,1812][1080,1920]\" resource-id=\"android:id/navigationBarBackground\" instance=\"6\"/></android.widget.FrameLayout></hierarchy>";
		
		
		System.out.println(clickableCount(s));
		
		
		
	}
}
