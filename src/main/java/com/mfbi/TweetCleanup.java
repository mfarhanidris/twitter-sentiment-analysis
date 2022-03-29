package com.mfbi;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetCleanup {

    public static void main(String[] args) {
        BufferedReader objReader = null;
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader("D:/datasets/malay-dataset/sentiment/supervised-twitter/text.csv"));

            while ((strCurrentLine = objReader.readLine()) != null) {
                System.out.println(cleanStrings(strCurrentLine.toLowerCase()));
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static String cleanStrings(String str) throws IOException {
        String result = str, rmvStr = "http", str2, str3;
        int strIndex, strIndex2;

        if (result.contains(rmvStr)){
            int i = result.lastIndexOf(rmvStr), j;
            if (result.indexOf(" ", i+1) == -1) {
                if (result.charAt(i-1) == ' ')
                    result = result.substring(0, i - 1);
                else
                    result = result.substring(0, i);
            }
        }

        while (result.contains(rmvStr)){
            strIndex = result.indexOf(rmvStr);
            strIndex2 = result.indexOf(" ", strIndex);
            str2 = result.substring(0, strIndex);
            if (strIndex2 == -1)
                str3 = result.substring(strIndex+rmvStr.length(), result.length());
            else
                str3 = result.substring(strIndex2+1, result.length());
            result = str2.concat(str3);
        }

        rmvStr = "@";
        if (result.contains(rmvStr)){
            int i = result.lastIndexOf(rmvStr), j;
            if (result.indexOf(" ", i+1) == -1) {
                if (result.charAt(i-1) == ' ')
                    result = result.substring(0, i-1);
                else
                result = result.substring(0, i);
            }
        }

        while (result.contains(rmvStr)){
            strIndex = result.indexOf(rmvStr);
            strIndex2 = result.indexOf(" ", strIndex);
            str2 = result.substring(0, strIndex);
            if (strIndex2 == -1)
                str3 = result.substring(strIndex+rmvStr.length(), result.length());
            else
            str3 = result.substring(strIndex2+1, result.length());
            result = str2.concat(str3);
        }

        rmvStr = "#";
        if (result.contains(rmvStr)){
            int i = result.lastIndexOf(rmvStr), j;
            if (result.indexOf(" ", i+1) == -1) {
                if (result.charAt(i-1) == ' ')
                    result = result.substring(0, i - 1);
                else
                result = result.substring(0, i);
            }
        }

        while (result.contains(rmvStr)){
            strIndex = result.indexOf(rmvStr);
            strIndex2 = result.indexOf(" ", strIndex);
            str2 = result.substring(0, strIndex);
            if (strIndex2 == -1)
                str3 = result.substring(strIndex+rmvStr.length(), result.length());
            else
            str3 = result.substring(strIndex2+1, result.length());
            result = str2.concat(str3);
        }

        result = result.replaceAll("\\d", "");
        result = result.replaceAll("[^a-zA-Z0-9\\s]", "");
        result = result.replaceAll("(\\s+[a-z](?=\\s))","");
        result = result.replaceAll("[-+^]*", "");

        File file=new ClassPathResource("malay-stopwords.txt").getFile();    //creates a new file instance
        FileReader fr=new FileReader(file);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        List<String> rmvStrList = new ArrayList<>();
            List<String> myList = new ArrayList<String>(Arrays.asList(result.split(" ")));
        while ((rmvStr = br.readLine()) != null) {
            rmvStrList.add(rmvStr);
            rmvStr = br.readLine();
            myList.remove(rmvStr);
        }
            myList.removeAll(rmvStrList);

        result = String.join(" ", myList);


//        while((rmvStr=br.readLine())!=null)
//        {
//            int i = 0;
//            String temp = result;
//            while (temp.contains(rmvStr)){
//                strIndex = result.indexOf(rmvStr, i);
//                strIndex2 = result.indexOf(" ", strIndex);
//                System.out.println(temp);
//                System.out.println(rmvStr);
//                System.out.println(strIndex);
//                System.out.println(strIndex+rmvStr.length());
//                if(strIndex != 0){
//                    if((strIndex + rmvStr.length()) != result.length()){
//                        if (result.charAt(strIndex - 1) == ' ' && result.charAt(strIndex + rmvStr.length()) == ' ') {
//                            str2 = result.substring(0, strIndex);
//                            str3 = result.substring(strIndex2, result.length());
//                            result = str2.concat(str3);
//                        }
//                    }
//                }
//                if (strIndex == -1 || strIndex2 == -1){
//                    strIndex2 = temp.length()-1;
//                } else {
//                    i = strIndex2;
//                }
//                    System.out.println(strIndex2);
//                    System.out.println(i);
//                temp = result.substring(strIndex2);
//            }
//        }
//        fr.close();    //closes the stream and release the resources


        return result;
    }

}
