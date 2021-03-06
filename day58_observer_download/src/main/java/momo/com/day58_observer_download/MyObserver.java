package momo.com.day58_observer_download;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/27 0027.
 */

public class MyObserver {

    private static MyObserver observer;
    private List<Watcher> list;

    //单例模式
    private MyObserver() {
        list = new ArrayList<>();
    }

    public static MyObserver getInstance() {
        if (observer == null) {
            //防止多个线程并发创建实例
            synchronized (MyObserver.class) {
                if (observer == null) {
                    observer = new MyObserver();
                }
            }
        }
        return observer;
    }


    public void addWatcher(Watcher watcher) {
        list.add(watcher);
    }

    public void removeWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    public void notifyUpdate(FileInfo fileInfo) {
        for (int i = 0; i < list.size(); i++) {
            Watcher w = list.get(i);
            w.onProgress(fileInfo);
        }
    }


    public interface Watcher{

        public void onProgress(FileInfo fileInfo);
    }

}
