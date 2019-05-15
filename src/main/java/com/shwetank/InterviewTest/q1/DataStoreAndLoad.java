package com.shwetank.InterviewTest.q1;

import java.util.HashMap;
/* @Author : Shwetank

to run this simply run main available at bottom

 */

public class DataStoreAndLoad {

    String data = "key1=value1;key2=value2\nkeyA=valueA\nkey3=value3;key4=value4";
    HashMap<String, HashMap<String, HashMap<String, String>>> dataMap = new HashMap<>();

    public void storeData() {
        // splitting by next line
        String[] dataSplitByNextLine = data.split("\n");
        for (int iterOnSplitDataByNextLine = 0; iterOnSplitDataByNextLine < dataSplitByNextLine.length; iterOnSplitDataByNextLine++) {

            String eachLine = dataSplitByNextLine[iterOnSplitDataByNextLine].trim();

            //check line exists
            if (eachLine.length() > 0) {
                HashMap<String, HashMap<String, String>> tempHashMap;
                // check if semi colon exists
                if (eachLine.contains(";")) {
                    String[] splitBySemiColon = eachLine.split(";");
                    //check if two part available of splitted line
                    if (splitBySemiColon.length == 2) {
                        for (int iterOnSplitBySemiColon = 0; iterOnSplitBySemiColon < splitBySemiColon.length; iterOnSplitBySemiColon++) {
                            tempHashMap = new HashMap<>();
                            String[] splitWithEqualAtFirstPos = splitBySemiColon[0].split("=");
                            String[] splitWithEqualAtSecondPos = splitBySemiColon[1].split("=");

                            //populate the internal hash map
                            tempHashMap.put(splitWithEqualAtFirstPos[1], new HashMap<String, String>() {{
                                put(splitWithEqualAtSecondPos[0], splitWithEqualAtSecondPos[1]);
                            }});
                            //populate the external hash map
                            dataMap.put(splitWithEqualAtFirstPos[0], tempHashMap);
                        }
                    } else {
                        System.out.println("--> Invalid String :: " + eachLine);
                    }
                } else {
                    // if there is no semi colon available in text
                    if (eachLine.contains("=")) {
                        tempHashMap = new HashMap<>();
                        tempHashMap.put(eachLine.split("=")[1], new HashMap<String, String>() {{
                            put(null, null);
                        }});
                        dataMap.put(eachLine.split("=")[0], tempHashMap);
                    } else {
                        System.out.println("--> Invalid String :: " + eachLine);
                    }
                }
            }
        }
    }

    public void displayData() {
        dataMap.forEach((parentKey, stringHashMapHashMap) -> stringHashMapHashMap.forEach((childKey, stringStringHashMap) -> stringStringHashMap.entrySet().forEach(stringStringEntry -> {
            System.out.println(parentKey + " -> " + childKey + " -> " + stringStringEntry.getKey() + " -> " + stringStringEntry.getValue());
        })));
    }

    public static void main(String[] args) {
        DataStoreAndLoad dataStoreAndLoad = new DataStoreAndLoad();
        dataStoreAndLoad.storeData();
        dataStoreAndLoad.displayData();
    }

}
