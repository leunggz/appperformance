package specializeItem;

import common.AppCommon;
import common.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StartTime extends AppCommon{

    private String startTime;

    public Process coldStartApp() throws Exception{
        String cmd = "adb shell am start -W -n " + Config.getActivityName();
        return executeAdbCommand(cmd);
    }

    public Process exitApp() throws Exception{
        String cmd = "adb shell am force-stop " + Config.getAppName();
        return executeAdbCommand(cmd);
    }

    public long getStartTime() throws Exception{
//        String startCmd = "adb shell am start -W -n " + Config.getActivityName();
        long start = System.currentTimeMillis();
        System.out.println(start);
        Process process = coldStartApp();
        long end = System.currentTimeMillis();
        long time = (end - start);
        TimeUnit.SECONDS.sleep(5);
        exitApp();
        System.out.println(end);
        System.out.println(time);
        return time;
    }

    public List<String> getReultList(int interval, int times) throws Exception{
        int count = 0;
        List<String> resultList = new ArrayList<String>();
        while (count < times){
            System.out.println("第" + (count+1) + "次执行");
            resultList.add(getCurrentTime() + "," + String.valueOf(getStartTime()));
            count++;
            TimeUnit.SECONDS.sleep(interval);
        }
        return  resultList;
    }

    public static void main(String[] args) throws Exception{
        String path = "H:\\project\\test.csv";
        StartTime st = new StartTime();
//        System.out.println("启动时间："+ st.getStartTime());
//        System.out.println(st.getCurrentTime());
//        st.saveResult(st.getStartTime(), path);
        st.saveResultList(st.getReultList(1, 10), path, "date,time");
    }
}
