package com.zheng.client;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by 郑俊铭 on 2017/7/14.
 */
public class ClientThread extends Thread {
    BufferedReader br = null;

    public ClientThread(BufferedReader br) {
        this.br = br;
    }

    public void run() {
        try {
            String line = null;
            // 不断读取数据
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
