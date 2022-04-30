import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ShowMoto {
    HashMap<String,Integer> b = new HashMap<>();
    private static JFrame frame;
    private JPanel panel;
    private JList list1;

    private JButton backButton;

    private JScrollPane scrollPane;
    public ShowMoto(LinkedHashMap<Integer, Float> bikes, MotorBikeSet mbs) {
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

        for(Integer key : bikes.keySet()) {
            String l = "";
            l += mbs.getMotorBike(key).getName();
            demoList.addElement(l);
            b.put(l,key);
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

                    ShowMoto sh = new ShowMoto(k.getSortedMap(),mbs);
                    sh.showWindow(x,y);

                    frame.dispose();
                }
            }
        };
        list1.addMouseListener(mouseListener);


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
