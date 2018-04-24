package com.gdutjunming.www.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author 郑俊铭
 * Date: 2017/11/21
 * Time: 22:25
 * No struggle, talent how to match the willfulness.
 * Description: 帮助菜单页面
 */
class HelpMenu extends MainMenu {
    HelpMenu() {
        remove(panel);
        remove(authorPanel);
        JLabel stringLabel = new JLabel("关于本系统的说明");
        JPanel stringPanel = new JPanel();
        JPanel explainPanel = new JPanel();
        JLabel explainLabel = new JLabel("<html>本系统是1.0版本，在这个系统里可以进行简单的数据操作，数据查询。<br>" +
                "如有关于程序的问题，请联系制作人郑俊铭：13143371031。</html>");
        // 主页信息
        getContentPane().add(stringPanel, BorderLayout.NORTH);
        stringLabel.setFont(new Font("微软雅黑", Font.PLAIN, 40));
        stringPanel.add(stringLabel);
        getContentPane().add(explainPanel, BorderLayout.CENTER);
        explainLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        explainPanel.add(explainLabel);
    }
}
