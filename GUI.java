package CourseProject;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI {

    public static void main(String[] args) {
        GUI ui = new GUI();
        ui.prepareGUI();
    }

    private void prepareGUI() {
        JFrame mainFrame = new JFrame("叮咚");
        mainFrame.setSize(400, 420);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setResizable(false);
        JMenuBar jMenuBar = new JMenuBar();
        mainFrame.setJMenuBar(jMenuBar);
        JMenu help = new JMenu("帮助");
        jMenuBar.add(help);
        JMenuItem about = new JMenuItem("关于作者");
        help.add(about);

        Container contentPane = mainFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(1, 2));
        JTextField text = new JTextField();
        text.setLayout(new GridLayout(1, 1));
        JButton send = new JButton("发送");
        send.setLayout(new GridLayout(1, 2));

        jPanel1.add(text);
        jPanel1.add(send);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new GridLayout(1, 1));

        JTextArea displayContent = new JTextArea(21, 2);
        JScrollPane jsp = new JScrollPane(displayContent);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jPanel2.add(jsp);
        displayContent.setWrapStyleWord(true);
        displayContent.setLineWrap(true);
        displayContent.setEnabled(false);

        /* lambda */
        Icon aboutIcon = new ImageIcon("/Users/cattree/IdeaProjects/Course Project/src/CourseProject/pic/cat.png");

        about.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "Auther: CTL\nLincese: MIT\n" +
                        "Github Link: https://github.com/morestart\n" +
                        "Blog Link: https://morestart.github.io/",
                "关于我", JOptionPane.INFORMATION_MESSAGE, aboutIcon));

        text.addActionListener(e -> {
            Runnable r = () -> {
                Message msg = new Message();
                try {
                    displayContent.append("你:" + text.getText() + "\n");
                    String t = text.getText();
                    text.setText("");
                    msg.Run(t);
                    FileInputStream fileau = null;
                    try {
                        fileau = new FileInputStream("/Users/cattree/IdeaProjects/Course Project/src/CourseProject/audio/send.au");
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        assert fileau != null;
                        AudioStream as = new AudioStream(fileau);
                        AudioPlayer.player.start(as);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }

                    displayContent.append("叮咚:" + msg.getContent() + "\n");
                    //                    System.out.println(msg.getContent());
                } catch (Exception e1) {
//                    e1.printStackTrace();
                    Icon errorIcon = new ImageIcon("/Users/cattree/IdeaProjects/Course Project/src/CourseProject/pic/error.pngng");
                    JOptionPane.showMessageDialog(null, "当前无网络,请稍后重试!",
                            "ERROR", JOptionPane.ERROR_MESSAGE, errorIcon);
                }
            };
            Thread t = new Thread(r);
            t.start();
        });

        send.addActionListener(e -> {
            Runnable r1 = () -> {
                Message msg = new Message();
                try {
                    displayContent.append("你:" + text.getText() + "\n");
                    String t = text.getText();
                    text.setText("");
                    msg.Run(t);

                    FileInputStream fileau = null;
                    try {
                        fileau = new FileInputStream("/Users/cattree/IdeaProjects/Course Project/src/CourseProject/audio/send.au");
                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        assert fileau != null;
                        AudioStream as = new AudioStream(fileau);
                        AudioPlayer.player.start(as);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    displayContent.append("叮咚:" + msg.getContent() + "\n");
                    //                    System.out.println(msg.getContent());
                } catch (Exception e1) {
                    Icon errorIcon = new ImageIcon("CourseProject/pic/text.png");
                    JOptionPane.showMessageDialog(null, "当前无网络,请稍后重试!",
                            "ERROR", JOptionPane.ERROR_MESSAGE, errorIcon);
                    displayContent.append(" ");
                }
            };
            Thread t1 = new Thread(r1);
            t1.start();
        });

        mainFrame.add(jPanel2, BorderLayout.NORTH);
        mainFrame.add(jPanel1, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
