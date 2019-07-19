package com.example.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @ClassName: FindDirsFiles
 * @Description: 遍历指定目录（含子目录）找寻指定类型文件
 * @Author xieyufeng
 * @Date 2019/7/9 09:19
 */
public class FindDirsFiles extends RecursiveAction {

    // 当前任务需要搜寻的目录
    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
//        List<FindDirsFiles> subTasks = new ArrayList<>();
//        File[] files = path.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                if (file.isDirectory()) {
//                    subTasks.add(new FindDirsFiles(file));
//                } else {
//                    if (file.getAbsolutePath().endsWith("txt")) {
//                        System.out.println("文件：" + file.getAbsolutePath());
//                    }
//                }
//            }
//            if (!subTasks.isEmpty()) {
//                for (FindDirsFiles subTask : invokeAll(subTasks)) {
//                    // 等待子任务执行完成
//                    subTask.join();
//                }
//            }
//        }
    }
}
