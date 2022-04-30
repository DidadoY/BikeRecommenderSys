import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class suggestionView {
    HashMap<String,Integer> b = new HashMap<>();
    private static JFrame frame;
    private JPanel panel;
    private JList list1;

    private JButton menuButton;

    MotorBikeSet mbs;
    int id;
    private JScrollPane scrollPane;
    public suggestionView(Set<MotorBike> bikes, MotorBikeSet mbs,int  id) {
        this.mbs = mbs;
        this.id = id;
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.trackColor = new Color(118,119,187);
                this.thumbColor  = new Color(58,80,194);
            }
        });
        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.trackColor = new Color(118,119,187);
                this.thumbColor  = new Color(58,80,194);
            }
        });
        scrollPane.getVerticalScrollBar().getComponent(0).setBackground(new Color(58,80,194));
        scrollPane.getVerticalScrollBar().getComponent(1).setBackground(new Color(58,80,194));

        scrollPane.getHorizontalScrollBar().getComponent(0).setBackground(new Color(58,80,194));
        scrollPane.getHorizontalScrollBar().getComponent(1).setBackground(new Color(58,80,194));

        DefaultListModel demoList = new DefaultListModel();
        for(MotorBike m : bikes) {
            String l = "";
            l += m.getName();
            demoList.addElement(l);
            b.put(l,m.getId());
        }
        list1.setModel(demoList);
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedValue = String.valueOf(list1.getSelectedValue());
                    String selectedItem = "";
                    for(char c : selectedValue.toCharArray()) {
                        selectedItem += c;
                    }
                    int x = frame.getX();
                    int y = frame.getY();
                    KNN k = new KNN();

                    k.runKNN(b.get(selectedItem));

                    ShowMoto sh = null;
                    try {
                        sh = new ShowMoto(k.getSortedMap(),mbs, id);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    sh.showWindow(x,y);

                    frame.dispose();
                }
            }
        };
        list1.addMouseListener(mouseListener);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    usersSet us = usersSet.getInstance();

                    int x = frame.getX();
                    int y = frame.getY();
                    mainWindow mW = new mainWindow(mbs.getMBS(), mbs);
                    mW.showWindow(x,y);
                    frame.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void showWindow(int x, int y) {
        frame = new JFrame("Sistema Recomanador");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(x, y, 600, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}