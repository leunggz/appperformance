package common;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class AppCommon {

//    public void coldStartApp(){
//        String cmd = "adb shell am start -W -n " + Config.getActivityName();
//    }
//
//    public void exitApp(){
//        String cmd = "adb shell am force-stop " + Config.getAppName()
//    }

    public Process executeAdbCommand(String  command) throws Exception{
        System.out.println("执行adb命令:"+ command);
        Process process = null;
        process = Runtime.getRuntime().exec(command);

        return process;
    }

    /**
     * 获取结果
     * @param process
     * @param key
     * @return
     * @throws Exception
     */
    public String getResultFromConsole(Process process, String key) throws Exception{
        if ( process == null){
            System.out.println("error");
        }
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        String result = null;
        StringBuffer buffer = new StringBuffer();;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
            buffer.append(line+"\n");
            if (line.contains(key)){
                result = line.split(":")[1].trim();
            }
        }
        return  result;
    }

    public String getCurrentTime(){
        String format = "HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public void saveResult(long startTime, String filepath){
        try {
            File csvFile = new File(filepath);
            FileOutputStream outputStream = new FileOutputStream(csvFile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            String result = getCurrentTime() + "," + String.valueOf(startTime);
            bufferedWriter.write(result);
            System.out.println("报存结果成功");
            bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void saveResultList(List<String> resultList, String filePath ,String title) throws Exception{
        File csvFile = new File(filePath);
        FileOutputStream outputStream = new FileOutputStream(csvFile);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(title + "\n");
        if(resultList.isEmpty()){
            System.out.println("结果集为空");
        }
        for(String result : resultList){
            bufferedWriter.write(result + "\n");
        }
        bufferedWriter.close();
        System.out.println("done");
    }

}
